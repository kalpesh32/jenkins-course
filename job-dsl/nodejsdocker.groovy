job('NodeJS Docker kalpesh-test') {
    scm {
        git('git://github.com/kalpesh32/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('kalpesh32/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('8d052f15-d987-4b0b-a7c5-c374b21e8e61')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
