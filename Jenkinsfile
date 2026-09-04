pipeline {
    agent any

    environment {
        IMAGE_NAME = "santoshsrit/demo"
        CONTAINER_NAME = "demo"
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

        stage('Docker Push') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-santosh',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_PASSWORD" | docker login \
                            -u "$DOCKER_USERNAME" \
                            --password-stdin

                        docker push ${IMAGE_NAME}:latest

                        docker logout
                    '''
                }
            }
        }

        stage('Stop Old Container') {
            steps {
                sh 'docker rm -f ${CONTAINER_NAME} || true'
            }
        }

        stage('Run Container') {
            steps {
        sh 'docker run -d --name ${CONTAINER_NAME} -p 9090:8080 ${IMAGE_NAME}:latest'
      }
        }
    }

    post {
        success {
        echo 'CI/CD pipeline completed successfully!'
        emailext (
            subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """<p>Good news! The build succeeded.</p>
                     <p>Job: <b>${env.JOB_NAME}</b></p>
                     <p>Build Number: <b>${env.BUILD_NUMBER}</b></p>
                     <p>Check console output at: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
            to: "santoshjavasrit@gmail.com",
            mimeType: 'text/html'
        )
    }

        failure {
        echo 'CI/CD pipeline failed!'
        emailext (
            subject: "FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            body: """<p>The build failed. Please check.</p>
                     <p>Job: <b>${env.JOB_NAME}</b></p>
                     <p>Build Number: <b>${env.BUILD_NUMBER}</b></p>
                     <p>Check console output at: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
            to: "santoshjavasrit@gmail.com",
            mimeType: 'text/html'
        )
    }
    }
}
