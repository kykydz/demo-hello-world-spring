appName = "demo-hello-world"

pipeline {
    stages {
        stage("Checkout") {
            steps {
                checkout scm
            }
        }
        stage("Docker Build") {
            sh("docker build -t demo-hello-world")
        }
    }
}