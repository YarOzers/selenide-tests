pipeline {
    agent any

    tools {
        maven 'Maven' // Имя настройки Maven, указанное в Global Tool Configuration Jenkins
        allure 'Allure'  // Имя настройки Allure
    }

    parameters {
        string(name: 'TEST_IDS', defaultValue: '', description: 'Comma-separated list of test IDs to run')
    }

    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
        ALLURE_REPORT_DIR = 'target/allure-report'
        GITHUB_REPO_URL = 'https://github.com/YarOzers/selenide-tests' // HTTPS URL репозитория
        GIT_CREDENTIALS_ID = 'jenkins-git-token' // ID, который вы назначили в Jenkins для токена
        SELENOID_URL = 'http://188.235.130.37:4444/wd/hub'
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
        } // <-- Закрывающая скобка для блока Run Tests

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
            script {
                if (fileExists('target/surefire-reports/TEST-TestSuite.xml')) {
                    def result = sh(script: 'cat target/surefire-reports/TEST-TestSuite.xml', returnStdout: true)
                    httpRequest httpMode: 'POST',
                                url: 'http://188.235.130.37:9111/api/test-results',
                                requestBody: result,
                                contentType: 'APPLICATION_XML'
                } else {
                    echo "Test report not found, skipping HTTP request."
                }
            }
        }

        failure {
            echo "Build failed!"
        }
    }
}
