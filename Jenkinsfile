pipeline {
    agent any

    tools {
            maven
            jdk
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