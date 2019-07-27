pipeline {
    environment{
        stageCompile = ':x:'
        stageDependencies = ':x:'
        stageTest = ':x:'
        stageClient = ':x:'
        stageArchive = ':x:'
    }
    agent any
    stages {
        stage('Dependencies') {
            steps{
                script
                {
                    stageDependencies = "Dependencies OK"
                }
            }
        }
        stage('Compile') {
            steps{
                script
                {
                    stageCompile = "Compile OK"
                }
            }
        }
        stage('Test') {
            steps{
                script
                {
                    stageTest = "Test OK"
                }
            }
        }
        stage('Client') {
            steps{
                script
                {
                    stageClient = "Client OK"
                }
            }
        }
        stage('Archive') {
            steps{
                script
                {
                    stageArchive = "Archive OK"
                }
            }
        }
    }
    post {
        always {
            slackNotify()
        }
    }
}

void slackNotify() {
    echo stageDependencies
    echo stageCompile
    echo stageTest
    echo stageClient
    echo stageArchive
}