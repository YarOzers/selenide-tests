pipeline {
    agent any

    tools {
        maven 'Maven' // Имя настройки Maven, указанное в Global Tool Configuration Jenkins
        allure 'Allure'  // Имя настройки Allure
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
        GITHUB_REPO_URL = 'https://github.com/YarOzers/selenide-tests' // HTTPS URL репозитория
        GIT_CREDENTIALS_ID = 'jenkins-git-token' // ID, который вы назначили в Jenkins для токена
        SELENOID_URL = 'http://188.235.130.37:4444/wd/hub'
        USER_ID = "${params.USER_ID}"
        TEST_PLAN_ID = "${params.TEST_PLAN_ID}"
        TEST_RUN_ID = "${params.TEST_RUN_ID}"
    }

    stages {
        stage('Checkout') {
            steps {
                // Используем токен для аутентификации
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
                    // Запуск Selenide тестов через Selenoid
                    sh "mvn clean test ${testIds} -Dselenide.remote=${SELENOID_URL} -Dselenide.browser=chrome -Dselenide.browserCapabilities.enableVNC=true -Dallure.results.directory=target/allure-results"
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    // Проверка существования директории с результатами тестов перед генерацией отчета
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
                    sh 'ls -la target/allure-results'
                }
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'target/surefire-reports/TEST-*.xml', allowEmptyArchive: true
            script {
                // Инициализация файла results.json перед добавлением данных
                def resultsFile = "${ALLURE_RESULTS_DIR}/results.json"
                writeFile file: resultsFile, text: '[]' // Создание пустого JSON-массива в файле

                // Сбор данных из всех *-result.json файлов в массив
                def jsonFiles = sh(script: "find ${ALLURE_RESULTS_DIR} -name '*-result.json'", returnStdout: true).trim().split('\n')
                def results = []

                jsonFiles.each { file ->
                    def content = readJSON file: file
                    def result = [
                        AS_ID: content.labels.find { it.name == 'AS_ID' }?.value,
                        status: content.status,
                        finishTime: content.stop, // Или другой ключ, содержащий время окончания выполнения
                        userId: environment.USER_ID,
                        testPlanId: environment.TEST_PLAN_ID,
                        testRunID: environment.TEST_RUN_ID
                    ]
                    results << result
                }

                // Запись массива в results.json
                writeJSON file: resultsFile, json: results

                // Отправка файла на сервер
                def resultsJson = readFile file: resultsFile
                httpRequest httpMode: 'POST',
                            url: 'http://188.235.130.37:9111/api/test-results',
                            requestBody: resultsJson,
                            contentType: 'APPLICATION_JSON'
            }
        }
        failure {
            echo "Build failed!"
        }
    }
}
