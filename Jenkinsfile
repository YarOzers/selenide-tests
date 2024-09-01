pipeline {
    agent any

    parameters {
        string(name: 'TEST_IDS', defaultValue: '', description: 'Comma-separated list of test IDs to run')
    }

    environment {
        ALLURE_RESULTS_DIR = 'target/allure-results'
        ALLURE_REPORT_DIR = 'target/allure-report'
        GITHUB_REPO_URL = 'https://github.com/YarOzers/selenide-tests'
        GIT_CREDENTIALS_ID = 'jenkins-git-token' // Replace with your Jenkins credentials ID
    }

    tools {
        maven '3.9.9' // Ensure Maven is configured in Jenkins Global Tool Configuration
        git 'Default' // Ensure Git is installed and configured
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/main']], // Update as per your branch
                          userRemoteConfigs: [[url: "${GITHUB_REPO_URL}", credentialsId: "${GIT_CREDENTIALS_ID}"]]
                ])
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def testIds = params.TEST_IDS
                    echo "Running tests with IDs: ${testIds}"
                    if (testIds?.trim()) {
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
                    echo 'Generating Allure report...'
                    sh 'mvn allure:report'
                }
            }
        }

        stage('Publish Allure Report') {
            steps {
                script {
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
            junit '**/target/surefire-reports/*.xml'
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