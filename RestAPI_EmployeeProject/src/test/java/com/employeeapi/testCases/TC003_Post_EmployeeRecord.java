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

public class TC003_Post_EmployeeRecord extends TestBase {
	

	 String empName = RestUtils.empName();
	 String empSalary = RestUtils.empSalary();
	 String empAge=RestUtils.empAge();

	@BeforeClass
	void getAllEmployees()
	{
		logger.info("********* Started TC003_Post_EmployeeRecord ******");	
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		System.out.println("empName >> "+empName);
		
		requestParams.put("name", empName);		
		requestParams.put("salary", empSalary);	
		requestParams.put("age", empAge);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		response=httpRequest.request(Method.POST, "/create");	
			
	}
	
	@Test
	void checkResponBody()
	{	logger.info("*****Checking response Body *******");
		String responseBody = response.getBody().asString();	
		logger.info("responseBody ==> "+responseBody);
		Assert.assertEquals(responseBody.contains(empName),true);
		Assert.assertEquals(responseBody.contains(empSalary),true);
		Assert.assertEquals(responseBody.contains(empAge),true);
	}	
	
	@Test
	void checkStatusCode()
	{	logger.info("*****Checking Status Code *******");
		int statusCode = response.statusCode();
	
		logger.info("statusCode ==> "+statusCode);
		Assert.assertTrue(statusCode == 200);
	}
	
   	@Test
	void checkContentType()
	{	logger.info("*****Checking Content_Type *******");
		String ContentType = response.getHeader("Content-Type");	  		
		logger.info("ContentType ==> "+ContentType);
		Assert.assertEquals(ContentType, "text/html; charset=UTF-8");
	}
   	
   	@AfterClass
   	void TearDown()
	{	logger.info("***** Ends TC003_Post_EmployeeRecord *******");
		
	}
   	
}
