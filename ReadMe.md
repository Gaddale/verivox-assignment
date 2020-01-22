# Web-Automation-Test

## Tools used

* Selenium, Cucumber and Junit
* Log4J for logging
* Cucumber html for reporting

## Execution

* Default browser is `chrome`, default url is `https://www.verivox.de` and default os is `linux` 
* Run against chrome & linux  `mvn verify -Dbrowser='chrome' -Dos='linux` 
* Run against firefox & windows`mvn verify -Dbrowser='firefox' -Dos='windows'` 
* Run against specific url `mvn verify -Dbrowser='chrome' -Durl='https://google.co.in'`

## Logging

* Captured logs under file `log/logging.log`

## Reporting

* Cucumber html reports `target/cucumber-html-reports/overview-features.html`
