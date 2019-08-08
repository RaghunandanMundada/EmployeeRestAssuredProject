  package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {	
	
	public static String empName()
	{
		String radomString=RandomStringUtils.randomAlphabetic(2);
		return ("John"+radomString);		
	}

	public static String empSalary() {
		String randomString = RandomStringUtils.randomNumeric(5);
		return randomString;
	}

	public static String empAge() {
		String randomString = RandomStringUtils.randomNumeric(2);
		return randomString;
	}

	

}
