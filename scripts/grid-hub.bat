@echo off
setlocal

set "SELENIUM_SERVER_JAR=tools\selenium\selenium-server-4.39.0.jar"
java -jar "%SELENIUM_SERVER_JAR%" hub
