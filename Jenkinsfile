appName = "demo-hello-world"

pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    
    stages {
        stage("Checkout") {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/kykydz/demo-hello-world-spring.git']]])
                sh('pwd && ls')
            }
        }
        stage("Gcloud authenticate") {
            steps {
                withCredentials([file(credentialsId: 'gc-admin-key', variable: 'GC_ADMIN_KEY')]) {
                    sh("gcloud auth activate-service-account --key-file=${GC_ADMIN_KEY}")
                    sh("gcloud config set project sinuous-tuner-303004")
                    sh("gcloud container clusters get-credentials demo-cluster --zone asia-southeast1-a")
                }
            }
        }
        stage("Gcloud Build") {
            steps {
                sh("gcloud builds submit --config=cloudbuild.yaml")
            }
        }
        stage("Prepare ingress controller using helm") {
            steps {
                sh("kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.1/deploy/static/provider/cloud/deploy.yaml")
                
                sh('kubectl apply -f deployment.yaml')
                sh('kubectl apply -f ingress.yaml')
                sh('kubectl apply -f service.yaml')
                sh('export NGINX_INGRESS_IP=$(kubectl -n ingress-nginx get service ingress-nginx-controller -ojson | jq -r ".status.loadBalancer.ingress[].ip")')
            }
        }
        
        stage("Apply k8s manifest") {
            steps {
                sh("kubectl apply -f deployment.yaml")
                sh("kubectl apply -f ingress.yaml")
            }
        }
    }
}