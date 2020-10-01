package com.visionit.autotesting.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class) 
@CucumberOptions
(
		features="src/test/resources/FeatureFile",
		glue="com.visionit.autotesting.stepdefs",
		tags="",
		plugin = {"pretty",
	            "html:target/htmlreport/testreport.html",
	            "json:target/json/file.json",
		},
		publish=true,
		dryRun=false
		)


public class TestRunner {

}
