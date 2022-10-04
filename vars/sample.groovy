#!/usr/bin/env groovy

def call() {
		echo "${pwd}"
		def Hello = sh ("ls -l")
		return Hello
}
