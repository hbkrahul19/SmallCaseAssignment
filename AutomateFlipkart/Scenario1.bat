call mvn clean

call mvn test -Dsurefire.suiteXmlFiles=testngScenario1.xml
pause