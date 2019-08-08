package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Delete_EmployeeRecord extends TestBase {
	

	 String empName = RestUtils.empName();
	 String empSalary = RestUtils.empSalary();
	 String empAge=RestUtils.empAge();

	@BeforeClass
	void getAllEmployees()
	{
		logger.info("********* Started TC005_Delete_EmployeeRecord ******");	
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		response=httpRequest.request(Method.GET, "/employees");	
		JsonPath jsonPathEvaluator = response.jsonPath();
		String empID =jsonPathEvaluator.get("[0].id");
		response= httpRequest.request(Method.DELETE, "/delete/"+empID);
	}
	
	@Test
	void checkResponBody()
	{	logger.info("*****Checking response Body *******");
		String responseBody = response.getBody().asString();	
		logger.info("responseBody ==> "+responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"),true);
		
	}		
   	
   	@AfterClass
   	void TearDown()
	{	logger.info("***** Ends TC005_Delete_EmployeeRecord *******");		
	}
   	
}
