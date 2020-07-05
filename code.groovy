job("task6-job1") {
  description("This job will pull the github repo auomatically when any developer push github repo related to #GroovyCode.")
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
  description("This job create Kubernetes os  according code (If code contain html syntax than it will create html interpreter or if it has php code than it create php interpreter)")
  steps {
    shell('''sudo kubectl run os --image rahulwithdocker/httpd-server:v1
             sudo kubectl expose pods os --type=NodePort --port=80     
    ''')
  }
  
  triggers {
        		upstream('task6-job1', 'SUCCESS')
  }
}
