#!/usr/bin/env groovy

def call(String User = 'Rohith') {
		def ENV_BRANCH
		def ENV_SERVICE
		def ENV_REPO
		pipeline {
			agent { label 'jenkins-docker-image-slave' }
			options {
				buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '2', daysToKeepStr: '', numToKeepStr: '2')
			}
			parameters{
      gitParameter branchFilter: 'origin/(.*)', defaultValue: 'develop', name: 'BRANCH', type: 'PT_BRANCH'
  }
  environment {

        NEXUS_VERSION = "nexus3"

        NEXUS_URL = "http://192.168.7.3:9119/v2/repository"

        NEXUS_REPOSITORY = "Maven_Jenkins_Test"

        NEXUS_CREDENTIAL_ID = "admin"
        
        NEXUS_CREDENTIAL_PASS = "Jocata@321"
    }
    stages {
        stage('Clone_Git') {
            steps {
                script{
                // Get some code from a GitHub repository
                ENV_BRANCH = params.BRANCH
                ENV_REPO = 'DLP'
                git branch: "${params.BRANCH}", url: "ssh://devops@192.168.0.82:2218/${ENV_REPO}"
                
                }
            }
        }
        stage('Maven_Build'){
            
       steps{

                script {

        ENV_SERVICE = input(id:'userInput', message: 'Please Provide Parameters', ok: 'Next',
                        parameters: [
                        choice(name: 'Services', choices: sh ( script: ''' for d in $(find ./Services/ -type d | grep -v src | grep -v database); do if [ $(ls -l $d | wc -l) -eq 3 ];then basename $d; fi; done ''', returnStdout: true
    ).trim(), description: 'Available Services To be built')])
    
                def JAR_POM_PATH = sh ( script: "find Services/ -type f | grep ${ENV_SERVICE}/pom.xml | sed 's|/pom.xml||g' |sed 's|Services||g'", returnStdout: true
    ).trim()  
                sh "mvn clean package -pl Services/${JAR_POM_PATH}  -am"
    
                sh "find Services/ -iname ${ENV_SERVICE}*jar 2>/dev/null | xargs  -n1  -i  cp  '{}' /home/jenkins/${ENV_SERVICE}-exec.jar"

}

       }
            
          }
    }
}
}
