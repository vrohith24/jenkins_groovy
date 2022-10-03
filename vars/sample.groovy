#!/usr/bin/env groovy

def call() {
    
    def service_list = ['bash','-c',"ls -l | sed 's|Services/||g'".toString()]
    
    def result = service_list.execute().text
    def slurper = new JsonSlurper()
    def json = slurper.parseText(result)
    def tags = new ArrayList()
    tags.addAll(json.tags)
    return tags.join('\n') 
}
