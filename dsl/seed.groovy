

def createTestJob(JobName, repoUrl='https://github.com/gummadic/poc-micro.git') {
    folder('folder-test') {
    description('Folder containing all jobs for folder-test')
    }
    multibranchPipelineJob(folder-test/JobName) {
        branchSources {
            git {
                remote(repoUrl)
                includes('*')
            }
        }
        triggers {
            periodic(5)
        }
    }
}

def buildPipelineJobs() {
    def testName = JobName + "_test"
    createTestJob(testName)
}

buildPipelineJobs()
