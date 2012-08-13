package com.javacodegeeks.gwtspring.client;

import junit.framework.Assert;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.javacodegeeks.gwtspring.shared.services.EmployeeService;
import com.javacodegeeks.gwtspring.shared.services.EmployeeServiceAsync;

public class Gwt_not_runned_TestGWTSpringTest extends GWTTestCase {
	public String getModuleName() {
		return "com.javacodegeeks.gwtspring.GWTSpringJUnit";
	}

	public void testSomething() {
		// /*
		EmployeeServiceAsync service = GWT.create(EmployeeService.class);

		System.out.println("service:" + service);
		ServiceDefTarget target = (ServiceDefTarget) service;

		target.setServiceEntryPoint(GWT.getModuleBaseURL()
				+ "GWTSpring/springGwtServices/employeeService");

		delayTestFinish(10000);

		service.saveEmployee(123, "name123", "surname123", "jobDescription123",
				new AsyncCallback<Void>() {

					public void onSuccess(Void result) {
						System.out.println("ins ok!");
						finishTest();

					}

					public void onFailure(Throwable caught) {
						fail("Request failure: " + caught.getMessage());

					}
				});

		// */
		// Not much to actually test in this sample app
		// Ideally you would test your Controller here (NOT YOUR UI)
		// (Make calls to RPC services, test client side model objects, test
		// client side logic, etc)
		Assert.assertTrue(true);
	}

}
