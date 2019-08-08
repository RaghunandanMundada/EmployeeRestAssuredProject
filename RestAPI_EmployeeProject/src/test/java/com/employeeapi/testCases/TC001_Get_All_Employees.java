package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employees extends TestBase {
	
	@BeforeClass
	void getAllEmployees()
	{
		logger.info("*********Started TC001_Get_All_Employees******");	
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		 httpRequest=RestAssured.given();
		 response=httpRequest.request(Method.GET, "/employees");			
	}
	
	@Test
	void checkResponBody()
	{	logger.info("*****Checking response Body *******");
		String responseBody = response.getBody().asString();
		
		logger.info("responseBody ==> "+responseBody);
		Assert.assertTrue(responseBody != null);
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
}
