#!/usr/bin/env groovy

def call() {
		echo "Welcome, ${User}"
		echo "${pwd}"
		def Hello = sh ("ls -l")
		return Hello
}
