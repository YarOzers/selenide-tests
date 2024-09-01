pipeline {
    agent any

    parameters {
        string(name: 'TEST_IDS', defaultValue: '', description: 'Comma-separated list of test IDs to run')
    }

    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
        ALLURE_REPORT_DIR = 'target/allure-report'
        GITHUB_REPO_URL = 'https://github.com/your-username/your-repo.git' // HTTPS URL репозитория
        GIT_CREDENTIALS_ID = 'jenkins-git-token' // ID, который вы назначили в Jenkins для токена
        SELENOID_URL = 'http://localhost:4444/wd/hub'
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
                    sh "mvn clean test ${testIds} -Dselenide.remote=${SELENOID_URL} -Dselenide.browser=chrome -Dselenide.browserCapabilities.enableVNC=true"
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    // Генерация отчета Allure
                    sh "allure generate ${ALLURE_RESULTS_DIR} -o ${ALLURE_REPORT_DIR} || true"
                }
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: "${ALLURE_RESULTS_DIR}"]]
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', allowEmptyArchive: true
        }
        failure {
            echo "Build failed!"
        }
    }
}