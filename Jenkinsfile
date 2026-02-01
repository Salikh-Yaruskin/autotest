pipeline {
  agent any

  stage('UI tests') {
    steps {
      sh '''
        docker-compose down -v
        docker-compose up --build --abort-on-container-exit
      '''
    }
  }

  post {
    always {
      archiveArtifacts 'target/**'
      junit 'target/surefire-reports/*.xml'
    }
  }
}
