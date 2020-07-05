job("task6-job1") {
  description("This job will pull the github repo on every push, update the container using given Dockerfile and push image to DockerHub")
  
  scm {
    github('Rahulgithub-code/JenkinsWithGroovyScript','master')
  }
  
  triggers {
    githubPush()
  }
  
  wrappers {
    preBuildCleanup()
}
}
