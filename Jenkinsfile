pipeline {
    agent any

    tools {
        maven 'Maven' // Проверьте, что это правильное имя Maven в Jenkins
        allure 'Allure' // Убедитесь, что это правильное имя инструмента Allure в Jenkins
    }

    parameters {
        string(name: 'TEST_IDS', defaultValue: '', description: 'Comma-separated list of test IDs to run')
        string(name: 'USER_ID', defaultValue: '111', description: 'User id')
        string(name: 'TEST_PLAN_ID', defaultValue: '222', description: 'Test plan id')
        string(name: 'TEST_RUN_ID', defaultValue: '333', description: 'Test run uuid')
        string(name: 'PROJECT_ID', defaultValue: '1', description: 'Project id')
    }

    environment {
        ALLURE_RESULTS_DIR = 'target\\allure-results'
        ALLURE_REPORT_DIR = 'target\\allure-report'
        GITHUB_REPO_URL = 'https://github.com/YarOzers/selenide-tests'
        GIT_CREDENTIALS_ID = 'jenkins-git-token'
        SELENOID_URL = 'http://188.235.130.37:4444/wd/hub'
        USER_ID = "${params.USER_ID}"
        TEST_PLAN_ID = "${params.TEST_PLAN_ID}"
        TEST_RUN_ID = "${params.TEST_RUN_ID}"
        PROJECT_ID = "${params.PROJECT_ID}"
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
                    def testIdsOption = params.TEST_IDS ? "-Dgroups=${params.TEST_IDS}" : ""
                    echo "Running tests with options: ${testIdsOption}"
                    bat "mvn clean test ${testIdsOption} -Dselenide.remote=${SELENOID_URL} -Dselenide.browser=chrome -Dselenide.browserCapabilities.enableVNC=true -Dallure.results.directory=${ALLURE_RESULTS_DIR} || exit 0"
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    if (fileExists("${ALLURE_RESULTS_DIR}")) {
                        bat "allure generate ${ALLURE_RESULTS_DIR} -o ${ALLURE_REPORT_DIR} || exit 0"
                    } else {
                        echo "No Allure results directory found, skipping report generation."
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
                        echo "Allure report directory not found, skipping report publication."
                    }
                }
            }
        }

        stage('Debug Directories') {
            steps {
                script {
                    echo "Listing target directory contents:"
                    bat 'dir target'
                    echo "Listing allure-results directory contents:"
                    bat 'dir target\\allure-results || echo "No allure-results directory found."'
                }
            }
        }
    }

    post {
        always {
            script {
                // Поиск всех JSON файлов в каталоге allure-results
                def jsonFilesOutput = bat(script: 'dir /b "target\\allure-results\\*-result.json"', returnStdout: true).trim()

                // Разделяем результат на строки и фильтруем нужные файлы
                def jsonFiles = jsonFilesOutput.split('\r\n').findAll { it.endsWith('-result.json') }

                if (jsonFiles.size() > 0) {
                    def results = []

                    jsonFiles.each { file ->
                        echo "Processing file: ${file}"
                        def content = readJSON file: "target\\allure-results\\${file}"
                        def buildUrl = env.BUILD_URL
                        def allureReportUrl = "${buildUrl}allure/#suites/${content.uuid ?: 'unknownUUID'}"

                        def result = [
                            AS_ID: content.labels?.find { it.name == 'AS_ID' }?.value ?: 'unknownASID',
                            status: content.status ?: 'unknownStatus',
                            finishTime: content.stop ?: 'unknownFinishTime',
                            userId: env.USER_ID ?: 'unknownUserId',
                            testPlanId: env.TEST_PLAN_ID ?: 'unknownTestPlanId',
                            testRunID: env.TEST_RUN_ID ?: 'unknownTestRunId',
                            projectId: env.PROJECT_ID ?: 'unknownProjectId',
                            reportUrl: allureReportUrl
                        ]

                        echo "Result: ${result}"
                        results << result
                    }

                    def resultsFile = "${env.WORKSPACE}\\target\\allure-results\\results.json"
                    writeJSON file: resultsFile, json: results

                    def updatedResultsJson = readFile file: resultsFile
                    echo "Sending results with report URL: ${updatedResultsJson}"

                    httpRequest httpMode: 'POST',
                                url: 'https://188.235.130.37:9111/jenkins-api/test-results',
                                requestBody: updatedResultsJson,
                                contentType: 'APPLICATION_JSON'
                } else {
                    echo "No result.json files found!"
                }
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
