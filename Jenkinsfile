pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'rushali252/java-app:latest'
        SONARQUBE_SERVER = 'SonarQube'
    }
    tools {
        maven 'maven'  // The Maven tool configured in Jenkins
    }
    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/RushaliShekhada/Pipeline-with-Docker.git'
            }
        }

        stage('Build with Java 17') {
            agent {
                docker {
                    image 'maven:3.9.4-eclipse-temurin-17'
                    args '--network ci_network -v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'java -version'
                sh 'mvn -v'
                sh 'mvn clean package'
                stash name: 'built-artifacts', includes: 'target/**/*'
            }
        }

        stage('Test with Java 11') {
            agent {
                docker {
                    image 'maven:3.8.8-eclipse-temurin-11'
                    args '--network ci_network -v /root/.m2:/root/.m2'
                }
            }
            steps {
                unstash 'built-artifacts'
                sh 'java -version'
                sh 'mvn -v'
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis Using Java 8') {
            agent {
                docker {
                    image 'maven:3.8.6-jdk-8'
                    args '--network ci_network -v /root/.m2:/root/.m2'
                }
            }
            steps {
                unstash 'built-artifacts'
                sh 'java -version'
                sh 'mvn -v'
                withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_TOKEN')]) {
                    withSonarQubeEnv("${SONARQUBE_SERVER}") {
                        sh """
                        mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=java-app \
                        -Dsonar.host.url=http://sonar:9000 \
                        -Dsonar.login=${SONAR_TOKEN} \
                        -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                        """
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker push $DOCKER_IMAGE
                    """
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f deployment.yaml'
            }
        }
    }
}
