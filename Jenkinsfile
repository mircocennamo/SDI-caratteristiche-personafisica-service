def BUILD_ID = "${env.BUILD_ID}".toInteger()
def WEBLOGIC_HOME = "${env.WEBLOGIC_HOME}"
def lastSuccessfulBuildId = 0
def jenkinsWorkspace = "${env.JENKINS_WORKSPACE}"



node {


    script {
        def build = currentBuild.previousBuild
        while(build != null){
            if(build.result == 'SUCCESS'){
                lastSuccessfulBuildId = build.id
                break
            }

            build = build.previousBuild
        }

        print "LAST_SUCCESSFUL_BUILD: " + lastSuccessfulBuildId
    }

    withMaven( mavenSettings: 'settings.xml') {

        stage('Checkout') {
            git url: 'http://192.168.15.174/sdi/caratteristiche-personafisica-ms.git', branch: 'develop', credentialsId: 'gitlabid'
            //######################## CHIAMATA DA JENKINS LOCALE ##############################
            //git url: 'http://svoragit.ced.local/polizie_locali/caratteristiche-personafisica-ms-ms.git', branch: 'main', credentialsId: '3051c36c-6722-45cb-966c-cf1cb1f918d7'
            //##################################################################################
        }

        stage('Build') {
            sh 'mvn clean install -DskipTests'
            print 'Build: '+BUILD_ID
            print 'Workspace: ' + jenkinsWorkspace
            print 'Build successfully'
        }

        stage('Release') {
            print "Next Build: " + (BUILD_ID+1)

            withCredentials([usernamePassword(credentialsId: 'gitlabid', usernameVariable: 'GIT_AUTHOR_NAME', passwordVariable: 'GIT_PASSWORD')]) {
                print  "${env.GIT_AUTHOR_NAME}"
                sh 'git config --global user.email "sdi@interno.it"'
                sh 'git config --global user.name '+ "${env.GIT_AUTHOR_NAME}"
                sh 'mvn -B release:clean build-helper:parse-version versions:set -DdevelopmentVersion=1.'+(BUILD_ID+1)+'.0-SNAPSHOT -DnewVersion=1.'+(BUILD_ID+1)+'.0-SNAPSHOT versions:commit release:prepare '
            }
        }

        stage('Undeploy on weblogic') {
            print "lastSuccessfulBuildId " + lastSuccessfulBuildId
            //if(lastSuccessfulBuildId > 0)
            sh 'java -cp ' +WEBLOGIC_HOME+ '/lib/weblogic.jar weblogic.Deployer -verbose -name caratteristiche-personafisica-ms-1.'+lastSuccessfulBuildId+'.0 -noexit -adminurl http://192.168.15.180:7001 -user weblogic -password Plocali#2022! -targets wls_server1 -undeploy'
        }

        stage('Deploy to weblogic') {
            script{
                folder = readMavenPom().getArtifactId() + '/target/'
                files = sh(returnStdout: true, script: "basename " + jenkinsWorkspace+folder+"*.war") //bat(returnStdout: true, script: "dir /B "+jenkinsWorkspace+folder+"*.war")
                print 'File to deploy: ' + files
            }
           // filesToDeploy =jenkinsWorkspace + folder+env.files.readLines().drop(1)[1]
            filesToDeploy = jenkinsWorkspace + folder + files
            print 'Full Path File : '+filesToDeploy

            sh 'java -cp ' +WEBLOGIC_HOME+ '/lib/weblogic.jar weblogic.Deployer -verbose -adminurl http://192.168.15.180:7001 -name '+readMavenPom().getArtifactId()+'-1.'+BUILD_ID+'.0 -username weblogic -password Plocali#2022! -targets wls_server1 -stage -upload -deploy -source '+filesToDeploy
        }
    }

    stage('Clean Workspace') {
        print 'Clean workspace'
        cleanWs deleteDirs: true, disableDeferredWipeout: true, notFailBuild: true
    }

}
