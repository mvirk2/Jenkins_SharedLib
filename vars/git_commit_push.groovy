def call(String credentialsId, String commitMessage = "Updated environment variables") {
    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
        sh """
            git config user.name "Jenkins"
            git config user.email "jenkins@example.com"
            
            echo "Checking repo status:"
            git status

            echo "Adding and committing changes:"
            git add .
            git commit -m "${commitMessage}" || echo "Nothing to commit"

            echo "Pushing to GitHub"
            git push https://\${GIT_USERNAME}:\${GIT_PASSWORD}@github.com/mvirk2/Wanderlust-Mega-Project.git main
        """
    }
}
