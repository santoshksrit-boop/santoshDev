pipeline {
    agent any

    environment {
        IMAGE_NAME = "santoshsrit/demo"
    CONTAINER_NAME = "demo"
    }

  stage('Docker Build') {
    steps {
        sh 'docker build -t ${IMAGE_NAME}:latest .'
    }
}

stage('Docker Push') {
    steps {
        sh 'docker push ${IMAGE_NAME}:latest'
    }
}

stage('Stop Old Container') {
    steps {
        sh 'docker rm -f ${CONTAINER_NAME} || true'
    }
}

stage('Run Container') {
    steps {
        sh 'docker run -d --name ${CONTAINER_NAME} -p 8080:8080 ${IMAGE_NAME}:latest'
    }
}
