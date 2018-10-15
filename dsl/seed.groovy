def createDeploymentJob(JobName, repoUrl) {
    pipelineJob(JobName) {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(repoUrl)
                        }
                        branches('master')
                        extensions {
                            cleanBeforeCheckout()
                        }
                    }
                }
                scriptPath("Jenkinsfile")
            }
        }
    }
}

def createTestJob(JobName, repoUrl) {
    multibranchPipelineJob(JobName) {
        branchSources {
            git {
                remote(repoUrl)
                includes('*')
            }
        }
        triggers {
            cron("H/5 * * * *")
        }
    }
}

def buildPipelineJobs() {
    def repo = "https://github.com/kcrane3576/"
    def repoUrl = repo + JobName + ".git"
    def deployName = JobName + "_deploy"
    def testName = JobName + "_test"

    createDeploymentJob(deployName, repoUrl)
    createTestJob(testName, repoUrl)
}

buildPipelineJobs()
