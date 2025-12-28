@echo off
setlocal

set "SELENIUM_SERVER_JAR=D:\JavaPr\selenium-server\selenium-server-4.39.0.jar"
set "HUB_URL=http://localhost:4444"
java -jar "%SELENIUM_SERVER_JAR%" node --hub "%HUB_URL%" --max-sessions 3