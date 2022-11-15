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
      sh '''docker login https://hub.docker.com/repository/docker/sathish10218/cicd_project --username sat10218 --password Deepika@#1996
            docker build . -t sathish10218/cicd_project
            docker tag sathish10218/cicd_project sathish10218/cicd_project:latest
            docker push sathish10218/cicd_project:latest'''
       }
     }
       }
     }
