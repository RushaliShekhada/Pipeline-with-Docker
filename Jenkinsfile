pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'rushali252/java-app:latest'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/RushaliShekhada/Pipeline-with-Docker.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    docker.image('openjdk:17-jdk').inside {
                        sh 'mvn clean package'
                    }
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    docker.image('openjdk:11-jdk').inside {
                        sh 'mvn test'
                    }
                }
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}")
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                        docker.image("${DOCKER_IMAGE}").push()
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sh 'docker run -d -p 8080:8080 --network ci_network ${DOCKER_IMAGE}'
                }
            }
        }
    }
}
