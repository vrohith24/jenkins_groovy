#!/usr/bin/env groovy



def getDockerImages() {
    def cmd = [ 'bash', '-c', "ls -l".toString()]
    def result = cmd.execute().text

    return result
}
