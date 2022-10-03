#!/usr/bin/env groovy

def call(String Name = 'User') {
		echo "Welcome, ${User}"
		echo "${pwd}"
		def Hello = sh ("ls -l")
		return Hello
}
