pipeline {
    agent any

    tools {
            maven 'maven3'
            jdk 'jdk1.8'
    }
    stages {
        stage('Build') {
              agent { docker 'mysql' }
              steps {
                 echo 'Hello, mysql'
              }
        }
        stage('Build Test') {
            steps {
                echo 'Testing..'

                sh "docker run mysql -xxxxxx "

                sh "mvn clean clover:setup test clover:aggregate clover:clover"

                  step([
                    $class: 'CloverPublisher',
                    cloverReportDir: './target/site/clover',
                    cloverReportFileName: 'clover.xml',
                    healthyTarget: [methodCoverage: 70, conditionalCoverage: 80, statementCoverage: 80], // optional, default is: method=70, conditional=80, statement=80
                    unhealthyTarget: [methodCoverage: 50, conditionalCoverage: 50, statementCoverage: 50], // optional, default is none
                    failingTarget: [methodCoverage: 0, conditionalCoverage: 0, statementCoverage: 0]     // optional, default is none
                  ])

                  sh "docker rm mysql"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }

}

//Manage Jenkins -> Script Console=>  System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "default-src 'self' 'unsafe-inline' 'unsafe-eval'; img-src 'self' 'unsafe-inline' data:;")