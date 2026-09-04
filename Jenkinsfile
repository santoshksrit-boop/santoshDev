pipeline {
    agent any

    environment {
        IMAGE_NAME = "santoshdev"
        CONTAINER_NAME = "santoshdev-container"
    }

    stages {

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:latest .'
            }
        }

        stage('Stop Old Container') {
            steps {
                sh 'docker rm -f ${CONTAINER_NAME} || true'
            }
        }

        stage('Run Container') {
            steps {
                sh 'docker run -d --name ${CONTAINER_NAME} -p 8081:8080 ${IMAGE_NAME}:latest'
            }
        }
    }

    post {
        success {
            echo 'CI/CD pipeline completed successfully!'
        }

        failure {
            echo 'CI/CD pipeline failed!'
        }
    }
}
