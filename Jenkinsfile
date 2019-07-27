Jenkinsfile (Declarative Pipeline)
pipeline {
    agent any

    tools {
            maven 'Maven 3.3.9'
            jdk 'jdk8'
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