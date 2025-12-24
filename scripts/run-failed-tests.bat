@echo off
setlocal

set "FAILED_SUITE=D:\JavaPr\auto-test\target\surefire-reports\testng-failed.xml"
cd /d ..
mvn test "-Dsurefire.suiteXmlFiles=%FAILED_SUITE%"
