pipeline {
    agent any
    tools {
        maven "Maven_3.9.9"
    }
    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Setup settings.xml') {
            steps {
                sh """
                    echo '<settings>' > settings.xml
                    echo '    <servers>' >> settings.xml
                    echo '        <server>' >> settings.xml
                    echo '            <id>github</id>' >> settings.xml
                    echo '            <username>${GITHUB_USERNAME}</username>' >> settings.xml
                    echo '            <password>${GITHUB_TOKEN}</password>' >> settings.xml
                    echo '        </server>' >> settings.xml
                    echo '    </servers>' >> settings.xml
                    echo '</settings>' >> settings.xml
                """
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean deploy -s settings.xml -DskipTests'
            }
        }

        stage('Cleanup') {
            steps {
                sh 'rm -f settings.xml'
            }
        }
    }
}