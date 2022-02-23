call mvn clean

call mvn test -Dsurefire.suiteXmlFiles=testngScenario2.xml
pause