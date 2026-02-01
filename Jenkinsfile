pipeline {
  agent any

  stages {
    stage('UI tests') {
      steps {
        sh '''
          docker-compose down -v
          docker-compose up --build --abort-on-container-exit
        '''
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: 'target/**', allowEmptyArchive: true
      junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
    }
  }
}