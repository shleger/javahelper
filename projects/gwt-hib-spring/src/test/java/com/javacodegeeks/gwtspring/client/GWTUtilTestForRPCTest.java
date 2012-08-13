package com.javacodegeeks.gwtspring.client;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.javacodegeeks.gwtspring.shared.services.EmployeeServiceAsync;
import com.octo.gwt.test.GwtTestWithEasyMock;
import com.octo.gwt.test.Mock;
import com.octo.gwt.test.spring.GwtTestContextLoader;
import com.octo.gwt.test.utils.GwtReflectionUtils;
import com.octo.gwt.test.utils.events.Browser;

@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"}, loader = GwtTestContextLoader.class)
public class GWTUtilTestForRPCTest extends GwtTestWithEasyMock {

	@Mock
	private EmployeeServiceAsync service;

	private GWTComposite composite;

	@Before
	public void init() {
		composite = new GWTComposite();
	}

	@Override
	public String getModuleName() {
		return "com.javacodegeeks.gwtspring.GWTSpring";
	}

	@Test
	public void checkRPCCallSuccess() {
		// Setup
		Button button = GwtReflectionUtils.getPrivateFieldValue(composite,
				"button");
		Label label = GwtReflectionUtils.getPrivateFieldValue(composite,
				"label");

		service.sayHello(EasyMock.eq("OCTO"), EasyMock.isA(AsyncCallback.class));

		expectServiceAndCallbackOnSuccess("mocked call");
		replay();
		// Test
		Assert.assertEquals(null, label.getText());

		// click to call server back
		Browser.click(button);

		// Assert
		verify();
		Assert.assertEquals("mocked call", label.getText());
	}

	@Test
	public void checkRPCCallFailure() {
		// Setup
		Button button = GwtReflectionUtils.getPrivateFieldValue(composite,
				"button");
		Label label = GwtReflectionUtils.getPrivateFieldValue(composite,
				"label");

		service.sayHello(EasyMock.eq("OCTO"), EasyMock.isA(AsyncCallback.class));

		expectServiceAndCallbackOnFailure(new Exception("Mocked exception"));
		replay();

		Assert.assertEquals(null, label.getText());

		// Test
		// click to call server back
		Browser.click(button);

		// Assert
		verify();
		Assert.assertEquals("Failure : Mocked exception", label.getText());
	}

}
