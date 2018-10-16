#!/usr/bin/env groovy

import jenkins.model.*
    
def createTestJob(JobName, repoUrl) {
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
    createTestJob(testName, repoUrl)
}

buildPipelineJobs()
