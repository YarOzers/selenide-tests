pipeline {
    agent any

    parameters {
        string(name: 'TEST_IDS', defaultValue: '', description: 'Comma-separated list of test IDs to run')
    }

    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
        ALLURE_REPORT_DIR = 'target/allure-report'
    }

    stages {
        stage('Checkout') {
            steps {
                // Клонирование репозитория из SCM
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Извлекаем переданные TEST_IDS и обрабатываем их
                    def testIds = params.TEST_IDS
                    echo "Running tests with IDs: ${testIds}"

                    // Проверка, если тестовые ID указаны
                    if (testIds?.trim()) {
                        // Запуск Maven с параметром -Dgroups для выполнения тестов с указанными ID
                        sh "mvn clean test -Dgroups=${testIds}"
                    } else {
                        echo "No test IDs provided, running all tests."
                        sh "mvn clean test"
                    }
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    // Генерация Allure отчета
                    echo 'Generating Allure report...'
                    sh 'mvn allure:report'
                }
            }
        }

        stage('Publish Allure Report') {
            steps {
                script {
                    // Публикация Allure отчета
                    allure([
                        includeProperties: false,
                        jdk: '',
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: "${ALLURE_RESULTS_DIR}"]]
                    ])
                }
            }
        }
    }

    post {
        always {
            // Архивация JUnit отчетов
            junit '**/target/surefire-reports/*.xml'
            // Архивация Allure отчетов
            archiveArtifacts artifacts: "${ALLURE_RESULTS_DIR}/**", allowEmptyArchive: true
        }

        success {
            echo 'Build completed successfully!'
        }

        failure {
            echo 'Build failed!'
        }
    }
}
