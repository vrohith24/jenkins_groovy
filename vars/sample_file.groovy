#!/usr/bin/env groovy
import groovy.json.JsonSlurper

def call() {
    
    def service_list = ['bash','-c',"ls Services/ | sed 's|Services/||g'".toString()]
    
    def result = service_list.execute().text
    def slurper = new JsonSlurper()
    def json = slurper.parseText(result)
    def tags = new ArrayList()
    tags.addAll(json.tags)
    return tags.join('\n') 
}
