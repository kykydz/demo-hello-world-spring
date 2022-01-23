appName = "demo-hello-world"

pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    
    stages {
        stage("Checkout") {
            steps {
                checkout scm
            }
        }

        stage("Docker Build") {
            steps {
                sh("docker build -t demo-hello-world .")
            }
        }
    }
}