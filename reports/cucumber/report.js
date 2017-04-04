$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/Test01.feature");
formatter.feature({
  "line": 2,
  "name": "Contact V1",
  "description": "\r\nAs a customer,\r\nI want to be able to contact V1",
  "id": "contact-v1",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@login"
    }
  ]
});
formatter.before({
  "duration": 2069700824,
  "status": "passed"
});
formatter.before({
  "duration": 53532508,
  "status": "passed"
});
formatter.scenario({
  "line": 7,
  "name": "Navigate Page",
  "description": "",
  "id": "contact-v1;navigate-page",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "the user is on the home page",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "the user clicks contact",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "the user is brought to the contact page",
  "keyword": "Then "
});
formatter.match({
  "location": "HomePageTestSteps.the_user_is_on_the_home_page()"
});
formatter.result({
  "duration": 2385754926,
  "status": "passed"
});
formatter.match({
  "location": "HomePageTestSteps.the_user_clicks_contact()"
});
formatter.result({
  "duration": 663702910,
  "status": "passed"
});
formatter.match({
  "location": "HomePageTestSteps.the_user_is_brought_to_the_contact_page()"
});
formatter.result({
  "duration": 50173,
  "status": "passed"
});
formatter.after({
  "duration": 24494,
  "status": "passed"
});
});