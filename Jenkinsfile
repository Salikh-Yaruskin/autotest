pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Test (Docker Maven)') {
      steps {
        script {
          docker.image('maven:3.9-eclipse-temurin-17').inside {
            sh 'mvn -U -q test'
          }
        }
      }
    }
  }

  post {
    always {
      junit 'target/surefire-reports/*.xml'
    }
  }
}
