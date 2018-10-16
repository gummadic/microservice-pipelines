#!/usr/bin/env groovy

import jenkins.model.*
    
folder("foo") {
  displayName('foo')
  description('A folder with all my foo')
}

folder("baar") {
  displayName('baar')
  description('A folder with all my baar')
}

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
    def testName = JobName + "_test"
    createTestJob(testName, repoUrl)
}

buildPipelineJobs()
