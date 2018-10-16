#!/usr/bin/env groovy

import jenkins.model.*
    
def createTestJob(JobName, repoUrl) {
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
    folder('project-a') {
        displayName('Project A')
        description('Folder for project A')
    }   
    def testName = JobName + "_test"
    createTestJob(testName, repoUrl)
}

buildPipelineJobs()
