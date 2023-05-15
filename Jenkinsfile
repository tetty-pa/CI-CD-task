node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'Maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=Ci-CD-task -Dsonar.projectName='Ci-CD-task'  -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_b7dbe6c2925936c713532579f149c16363336bf1"
    }
  }
}