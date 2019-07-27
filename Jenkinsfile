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
                sh 'mvn clean clover:setup test clover:aggregate clover:clover'
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