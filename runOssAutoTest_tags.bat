cd target
mkdir logs
mkdir reports
mkdir reports\screenshots
mkdir drivers
copy  ..\drivers\*.exe drivers
copy ..\autotest.properties .
copy 
java -cp dependency-jars\*;AutoTest-tests.jar cucumber.cli.Main -f pretty -f html:reports\cucumber -f json-pretty:reports\cucumber-report.json classpath:features -g com/airtricity/autotest/tests --tags @ResetLoggedInPassword
cd ..