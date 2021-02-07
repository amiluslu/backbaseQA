@Library('github.com/docker/jenkins-pipeline-scripts')

pipeline {
    agent none
    options {
        disableConcurrentBuilds()
    }
    environment {
            gitUrl = "https://github.com/amiluslu/backbaseQA.git"
            gitBranch = "master"
    }

    stages {
        stage('Test Automation') {
            agent { label nodeName }

            stages{
                stage ('Checkout') {

                    steps {
                        script {
                            println("Test started")

                            git branch: gitBranch, credentialsId:'gitUser', url: gitUrl

                        }
                    }
                }
                stage ('Running Web Tests') {

                    steps {
                        script {

                            println("Web Tests started")
                            sh "mvn clean test -Pweb"
                        }
                    }
                }

                stage ('Running Api Tests') {

                    steps {
                        script {
                            println("Api Tests started")
                            sh "mvn clean test -Papi"
                        }
                    }
                }
            }
        }
    }
}

