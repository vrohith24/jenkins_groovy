#!/usr/bin/env groovy



def call() {
    def cmd = [ 'bash', '-c', "ls -l".toString()]
    def result = cmd.execute().text

    return result
}
