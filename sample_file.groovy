#!/usr/bin/env groovy



def getDockerImages() {
    def cmd = [ 'bash', '-c', "ls".toString()]
    def result = cmd.execute().text

    return result
}
