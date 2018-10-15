def call(){
    node {
        stage('Checkout') {
            checkout scm
        }

        // Execute different stages depending on the job
        if(env.JOB_NAME.contains("deploy")){
            packageArtifact()
        } else if(env.JOB_NAME.contains("test")) {
            buildAndTest()
        }
    }
}

def packageArtifact(){
    stage("Package artifact") {
        sh "mvn package"
    }
}

/**
  * Generates a path to a temporary file location, ending with {@code path} parameter.
  * 
  * @param path path suffix
  * @return path to file inside a temp directory
  */
@NonCPS
String createTempLocation(String path) {
  String tmpDir = pwd tmp: true
  def workspace = env.WORKSPACE
  return workspace + File.separator + new File(path).getName()
}

/**
  * Returns the path to a temp location of a script from the global library (resources/ subdirectory)
  *
  * @param srcPath path within the resources/ subdirectory of this repo
  * @param destPath destination path (optional)
  * @return path to local file
  */
String copyGlobalLibraryScript(String srcPath, String destPath = null) {
  destPath = destPath ?: createTempLocation(srcPath)
  writeFile file: destPath, text: libraryResource(srcPath)
  echo "copyGlobalLibraryScript: copied ${srcPath} to ${destPath}"
  return destPath
}



def buildAndTest(){
    stage("Backend tests"){
        copyGlobalLibraryScript('test.sh')
        sh "ls -lrt"
    }
}
