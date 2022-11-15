pipeline {
  agent any
    stages {
     stage('git clone') {
       steps {
         echo 'clone the repo'
          }
        }
   stage('build') {
    steps {
      sh '''echo PATH = ${PATH}
mvn -N io.takari:maven:wrapper
chmod +x mvnw
./mvnw clean install'''
      }
    }
   stage('Docker image') {
    steps {
      sh '''docker login docker-registry2dc.platform3solutions.com/a360/a360-gateway-service --username akhilkottedi --password @kH!1@142
            docker build . -t a360/a360-gateway-service
            NOW=$(date '+%s')
            echo $NOW
            docker tag a360/a360-gateway-service docker-registry2dc.platform3solutions.com/a360/a360-gateway-service:latest
            docker push docker-registry2dc.platform3solutions.com/a360/a360-gateway-service:latest'''
       }
     }
      stage('Build Status') {
      steps {
        office365ConnectorSend 'https://platform3solutionsllc.webhook.office.com/webhookb2/9efe2495-8f3b-43ab-8700-670af1dd32fb@950af411-a869-4fdb-be85-926dbabe3c4f/JenkinsCI/0f106893c83c409cb6e1f717b02ee077/d68cfc5f-46a0-40ab-ba76-7856734fa8ff'
        }
      }
       }
     }
