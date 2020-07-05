job("task6-job1") {
  description("This job will pull the github repo on every push, update the container using given Dockerfile and push image to DockerHub")
  scm {
    github('Rahulgithub-code/JenkinsWithGroovyScript','master')
      }
  
  steps {
    shell('sudo cp -vrf * /root/task6')
  }
  triggers {
        		upstream('Groovy_Seed_GitHub', 'SUCCESS')
}

  
  triggers {
    scm('* * * * *')
  }
  
  wrappers {
    preBuildCleanup()
}
}

job("task6-job2"){
  description("This job will pull the github repo on every push, update the container using given Dockerfile and push image to DockerHub")
  steps {
    shell('''sudo kubectl run os --image httpd
             sudo kubectl expose pods os --type=NodePort --port=80     
    ''')
  }
  
  triggers {
        		upstream('task6-job1', 'SUCCESS')
  }
}
