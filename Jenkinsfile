pipeline {
  agent {
    docker {
      image 'docker:latest'
      args '-v /var/run/docker.sock:/var/run/docker.sock'
    }
  }

  stages {
    stage('UI tests') {
      steps {
        sh '''
          apk add --no-cache docker-compose
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