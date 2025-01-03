pipeline {
    agent any
    tools {
        maven 'sonarmaven' // Ensure 'sonarmaven' is configured in Jenkins
    }

    environment {
        SONARQUBE_SERVER = 'sonarqube'  // Ensure SonarQube server is configured correctly in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                bat '''
                mvn clean test
                '''
            }
        }
        stage('Coverage Analysis') {
            steps {
                bat '''
                mvn jacoco:report
                '''
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONAR_TOKEN = credentials('sonar-token') // Accessing the SonarQube token
            }
            steps {
                bat '''
                mvn sonar:sonar ^
                -Dsonar.projectKey=Maven_Coverage ^
                -Dsonar.sources=src/main/java ^
                -Dsonar.tests=src/test/java ^
                -Dsonar.projectName="Maven_Coverage" ^
                -Dsonar.host.url=http://localhost:9000 ^
                -Dsonar.token=%SONAR_TOKEN% ^
                '''
                 }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully'
        }
        failure {
            echo 'Pipeline failed'
        }
        always {
            echo 'This runs regardless of the result.'
        }
    }
}
