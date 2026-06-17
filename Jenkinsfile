pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {

        stage('Git Pull') {
            steps {
                git branch: 'main',
                url: 'https://github.com/hongdyhengmesa/final_devop.git'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Deploy') {
            steps {
                sh 'ansible-playbook -i inventory.ini playbook.yml'
            }
        }
    }

    post {

        failure {
            emailext(
                subject: 'Build Failed',
                body: 'Jenkins build failed.',
                to: 'srengty@gmail.com'
            )
        }

        success {
            echo 'Deployment successful'
        }
    }
}