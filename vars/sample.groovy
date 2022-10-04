#!/usr/bin/env groovy

def call(String User = 'Rohith') {
		echo "${pwd}"
		echo "Welcome, ${User}"
		def Hello = sh ("ls -l")
		return Hello
}
