pipeline {
    agent any

    tools {
            maven 'maven3'
            jdk 'jdk1.8'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'

                 sh "mvn clean clover:setup test clover:aggregate clover:clover"

                  step([
                    $class: 'CloverPublisher',
                    cloverReportDir: './target/site/clover',
                    cloverReportFileName: 'clover.xml',
                    healthyTarget: [methodCoverage: 70, conditionalCoverage: 80, statementCoverage: 80], // optional, default is: method=70, conditional=80, statement=80
                    unhealthyTarget: [methodCoverage: 50, conditionalCoverage: 50, statementCoverage: 50], // optional, default is none
                    failingTarget: [methodCoverage: 0, conditionalCoverage: 0, statementCoverage: 0]     // optional, default is none
                  ])

                  sh "sandbox; default-src 'none'; img-src 'self'; style-src 'self';"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}