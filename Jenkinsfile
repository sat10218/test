pipeline {
  agent any
    stages {
      stage('PreBuild-Email') {
           steps {
               script {
                   def mailRecipients = 'msathishkumar027@gmail.com'
                   def jobName = currentBuild.fullDisplayName
                   //emailext body: '''${SCRIPT, template="groovy-html.template"}''',
                   emailext body: '''Hello''',
                       mimeTye: 'text/html',
                       subject: "[Jenkins] Started ${jobName}",
                       to: "${mailRecipients}",
                       replyTo: "${mailRecipients}",
                       recipientProviders: [[$class: 'CulpritsRecipientProvider']]
        }
    }
}
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
      sh '''docker login --username sathish10218 --password Deepika@#1996
            docker build . -t sathish10218/cicd_project
            docker tag sathish10218/cicd_project sathish10218/cicd_project:latest
            docker push sathish10218/cicd_project:latest'''
       }
     }
       }
  environment {
        EMAIL_TO = 'msathishkumar027@gmail.com'
    }
  post {
        
        success {
            emailext body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, maxLines=100, escapeHtml=false}', 
                    to: "${EMAIL_TO}", 
                    subject: 'Build Success in Jenkins: $PROJECT_NAME - #$BUILD_NUMBER'
        }
        
        failure {
            emailext body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, maxLines=100, escapeHtml=false}', 
                    to: "${EMAIL_TO}", 
                    subject: 'Build failed in Jenkins: $PROJECT_NAME - #$BUILD_NUMBER'
        }
        unstable {
            emailext body: 'Check console output at $BUILD_URL to view the results. \n\n ${CHANGES} \n\n -------------------------------------------------- \n${BUILD_LOG, maxLines=100, escapeHtml=false}', 
                    to: "${EMAIL_TO}", 
                    subject: 'Unstable build in Jenkins: $PROJECT_NAME - #$BUILD_NUMBER'
        }
        changed {
            emailext body: 'Check console output at $BUILD_URL to view the results.', 
                    to: "${EMAIL_TO}", 
                    subject: 'Jenkins build is back to normal: $PROJECT_NAME - #$BUILD_NUMBER'
        }
    }
     }
