pipeline {
    agent any  // 모든 환경에서 실행 가능하게 설정

    tools {
        maven "Maven_3.9.9"  // Maven 3.9.9 버전 사용
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm  // 소스 코드 저장소에서 최신 코드 가져오기
            }
        }

        stage('Setup settings.xml') {
            steps {
                // Maven의 설정 파일(settings.xml) 생성
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
                // 프로젝트 빌드 및 패키징 수행 (테스트는 아직 실행하지 않음)
                sh 'mvn clean package -s settings.xml'
            }
        }

        stage('Run Tests') {
            steps {
                // 테스트 실행하여 코드 검증
                sh 'mvn test -s settings.xml'
            }
        }

        stage('Deploy') {
            when {
                expression {
                    // 이전 단계가 성공했을 경우에만 배포 진행
                    return currentBuild.result == null || currentBuild.result == 'SUCCESS'
                }
            }
            steps {
                // Maven을 이용해 빌드된 패키지를 GitHub 패키지 저장소에 배포
                sh 'mvn deploy -s settings.xml'
            }
        }

        stage('Cleanup') {
            steps {
                // 생성한 설정 파일 삭제하여 불필요한 파일 제거
                sh 'rm -f settings.xml'
            }
        }
    }
}
