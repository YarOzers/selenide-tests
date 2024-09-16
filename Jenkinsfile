pipeline {
    agent any

    tools {
        maven 'Maven' // Убедитесь, что это имя настройки Maven, указанное в Jenkins
        allure 'Allure'  // Убедитесь, что это имя настройки Allure
    }

    parameters {
        string(name: 'TEST_IDS', defaultValue: '', description: 'Comma-separated list of test IDs to run')
        string(name: 'USER_ID', defaultValue: '111', description: 'User id')
        string(name: 'TEST_PLAN_ID', defaultValue: '222', description: 'Test plan id')
        string(name: 'TEST_RUN_ID', defaultValue: '333', description: 'Test run uuid')
    }

    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
        ALLURE_REPORT_DIR = 'target/allure-report'
        GITHUB_REPO_URL = 'https://github.com/YarOzers/selenide-tests'
        GIT_CREDENTIALS_ID = 'jenkins-git-token'
        SELENOID_URL = 'http://188.235.130.37:4444/wd/hub'
        USER_ID = "${params.USER_ID}"
        TEST_PLAN_ID = "${params.TEST_PLAN_ID}"
        TEST_RUN_ID = "${params.TEST_RUN_ID}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/main']],
                          userRemoteConfigs: [[url: "${GITHUB_REPO_URL}", credentialsId: "${GIT_CREDENTIALS_ID}"]]
                ])
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def testIds = params.TEST_IDS ? "-Dgroups=${params.TEST_IDS}" : ""
                    echo "Running tests with options: ${testIds}"
                    sh "mvn clean test ${testIds} -Dselenide.remote=${SELENOID_URL} -Dselenide.browser=chrome -Dselenide.browserCapabilities.enableVNC=true -Dallure.results.directory=${ALLURE_RESULTS_DIR}"
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    // Генерируем Allure отчет независимо от успешности или провала тестов
                    if (fileExists("${ALLURE_RESULTS_DIR}")) {
                        sh "allure generate ${ALLURE_RESULTS_DIR} -o ${ALLURE_REPORT_DIR} || true"
                    } else {
                        echo "Allure results directory does not exist. Skipping report generation."
                    }
                }
            }
        }

        stage('Publish Allure Report') {
            steps {
                script {
                    if (fileExists("${ALLURE_REPORT_DIR}")) {
                        allure includeProperties: false, jdk: '', results: [[path: "${ALLURE_RESULTS_DIR}"]]
                    } else {
                        echo "Allure report directory does not exist. Skipping report publication."
                    }
                }
            }
        }

        stage('Debug Directories') {
            steps {
                script {
                    sh 'echo "Listing target directory contents:"'
                    sh 'ls -la target'
                    sh 'echo "Listing allure-results directory contents:"'
                    sh 'ls -la target/allure-results || echo "No allure-results directory found."'
                }
            }
        }
    }

    post {
        always {
            // Выполняем действия после завершения пайплайна в любом случае
            archiveArtifacts artifacts: 'target/surefire-reports/TEST-*.xml', allowEmptyArchive: true

            script {
                def resultsFile = "${ALLURE_RESULTS_DIR}/results.json"
                writeFile file: resultsFile, text: '[]' // Создаем пустой JSON, если файл отсутствует

                def jsonFiles = sh(script: "find ${ALLURE_RESULTS_DIR} -name '*-result.json'", returnStdout: true).trim().split('\n')
                def results = []

                jsonFiles.each { file ->
                    if (file) {
                        def content = readJSON file: file
                        def buildUrl = env.BUILD_URL
                        def allureReportUrl = "${buildUrl}allure/#suites/${content.uuid}"

                        def result = [
                            AS_ID: content.labels.find { it.name == 'AS_ID' }?.value,
                            status: content.status,
                            finishTime: content.stop,
                            userId: env.USER_ID ?: 'unknownUserId',
                            testPlanId: env.TEST_PLAN_ID ?: 'unknownTestPlanId',
                            testRunID: env.TEST_RUN_ID ?: 'unknownTestRunId',
                            reportUrl: allureReportUrl
                        ]

                        echo "Processing file: ${file}"
                        echo "Result: ${result}"
                        results << result
                    }
                }

                writeJSON file: resultsFile, json: results

                def updatedResultsJson = readFile file: resultsFile
                echo "Sending results with report URL: ${updatedResultsJson}"

                httpRequest httpMode: 'POST',
                            url: 'http://188.235.130.37:9111/api/test-results',
                            requestBody: updatedResultsJson,
                            contentType: 'APPLICATION_JSON'
            }
        }

        failure {
            echo "Build failed!"
        }

        success {
            echo "Build succeeded!"
        }
    }
}
