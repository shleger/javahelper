package com.javacodegeeks.gwtspring.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.javacodegeeks.gwtspring.shared.services.EmployeeService;
import com.javacodegeeks.gwtspring.shared.services.EmployeeServiceAsync;

public class GWTComposite extends Composite {
	private Button button = new Button();
	   private Label label = new Label();
	   private EmployeeServiceAsync service = (EmployeeServiceAsync) GWT.create(EmployeeService.class);

	public GWTComposite() {

		      button.setText("say hello");

		      //the wrapper panel
		      VerticalPanel vPanel = new VerticalPanel();
		      vPanel.setWidth("100%");
		      vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		      vPanel.add(button);
		      vPanel.add(label);

		      button.addClickHandler(new ClickHandler() {

		         public void onClick(ClickEvent event) {
		            // Create an asynchronous callback to handle the result.
		            AsyncCallback<String> callback = new AsyncCallback<String>() {

		               public void onSuccess(String result) {
		                  label.setText(result);
		               }

		               public void onFailure(Throwable caught) {
		                  // Show the RPC error message to the user
		                  label.setText("Failure : " + caught.getMessage());
		               }
		            };

		            // Make the call. Control flow will continue immediately and later
		            // 'callback' will be invoked when the RPC completes.
		            service.sayHello("OCTO", callback);
		         }
		      });

		      // All composites must call initWidget() in their constructors.
		      initWidget(vPanel);
		   }

}
