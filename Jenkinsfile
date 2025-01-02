
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
                echo "Running tests with Jacoco coverage using Maven..."
                mvn clean verify
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
                -Dsonar.inclusions="src/main/java/**/*.java" ^
                -Dsonar.exclusions="src/test/java/**/*.java" ^
                -Dsonar.coverage.jacoco.xmlReportPaths=target/jacoco.xml
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
