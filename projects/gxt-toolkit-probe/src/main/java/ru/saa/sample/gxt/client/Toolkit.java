package ru.saa.sample.gxt.client;

import com.google.gwt.dom.client.Document;
import com.sencha.gxt.widget.core.client.button.TextButton;
import ru.saa.sample.gxt.client.internationalization.LangConstantsMessages;
import ru.saa.sample.gxt.client.internationalization.LangConstants;
import ru.saa.sample.gxt.client.ui.factored.UserDashboard;
import ru.saa.sample.gxt.client.ui.menu.MenuBarUiBinderExample;
import ru.saa.sample.gxt.client.ui.verysimple.BinderUi;
import ru.saa.sample.gxt.client.ui.widget.HelloWidgetWorld;
import ru.saa.sample.gxt.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Date;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * @see  <url>https://developers.google.com/web-toolkit/doc/latest/DevGuideUiBinder#Hello_World</url>
 *
 */
public class Toolkit implements EntryPoint {
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

//    private final Messages messages = GWT.create(Messages.class);
    private LangConstants constants = GWT.create(LangConstants.class);
    private LangConstantsMessages messages = GWT.create(LangConstantsMessages.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        final TextButton textButton = new TextButton("gxtSimpleButton");


        final Button sendButton = new Button(constants.sendButton());
        final TextBox nameField = new TextBox();
        nameField.setText(constants.nameField());
        final Label errorLabel = new Label();

        // We can add style names to widgets
        sendButton.addStyleName("sendButton");


        RootPanel.get("widgetEx").getElement().setInnerHTML(constants.widgetEx());
        RootPanel.get("factoryEx").getElement().setInnerText(constants.factoryEx());

        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element

        // GXT button
        RootPanel.get("gxtContainer").add(textButton);


        //simple uiBinder
        BinderUi binderUi = new BinderUi();
        Document.get().getBody().appendChild(binderUi.getElement());
        binderUi.setTitle(" GWT !!!");

        // Use Widgets
        HelloWidgetWorld helloWidgetWorld = new HelloWidgetWorld("Mary", "Julia", "Susie");
        helloWidgetWorld.getgButton().setText("gwtUiButton");
        helloWidgetWorld.getgButton().setTitle("clickFromPanel");
        helloWidgetWorld.setClickMessage(messages.lastUpdate(new Date()));
        RootPanel.get("widgetContainer").add(helloWidgetWorld);


        //Use factored widget

        UserDashboard userDashboard = new UserDashboard();
        RootPanel.get("factoredContainer").add(userDashboard);



        RootPanel.get("nameFieldContainer").add(nameField);
        RootPanel.get("sendButtonContainer").add(sendButton);
        RootPanel.get("errorLabelContainer").add(errorLabel);

        //Menu GXT
        MenuBarUiBinderExample menuBarUiBinderExample = new MenuBarUiBinderExample();
        RootPanel.get("menuContainer").add(menuBarUiBinderExample);

        // Focus the cursor on the name field when the app loads
        nameField.setFocus(true);
        nameField.selectAll();

        // Create the popup dialog box
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setText("Remote Procedure Call");
        dialogBox.setAnimationEnabled(true);
        final Button closeButton = new Button("Close");
        // We can set the id of a widget by accessing its Element
        closeButton.getElement().setId("closeButton");
        final Label textToServerLabel = new Label();
        final HTML serverResponseLabel = new HTML();
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
        dialogVPanel.add(textToServerLabel);
        dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
        dialogVPanel.add(serverResponseLabel);
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);

        // Add a handler to close the DialogBox
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
                sendButton.setEnabled(true);
                sendButton.setFocus(true);
            }
        });

        // Create a handler for the sendButton and nameField
        class MyHandler implements ClickHandler, KeyUpHandler {
            /**
             * Fired when the user clicks on the sendButton.
             */
            public void onClick(ClickEvent event) {
                sendNameToServer();
            }

            /**
             * Fired when the user types in the nameField.
             */
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    sendNameToServer();
                }
            }

            /**
             * Send the name from the nameField to the server and wait for a response.
             */
            private void sendNameToServer() {
                // First, we validate the input.
                errorLabel.setText("");
                String textToServer = nameField.getText();
                if (!FieldVerifier.isValidName(textToServer)) {
                    errorLabel.setText("Please enter at least four characters");
                    return;
                }

                // Then, we send the input to the server.
                sendButton.setEnabled(false);
                textToServerLabel.setText(textToServer);
                serverResponseLabel.setText("");
                greetingService.greetServer(textToServer, new AsyncCallback<String>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        dialogBox.setText("Remote Procedure Call - Failure");
                        serverResponseLabel.addStyleName("serverResponseLabelError");
                        serverResponseLabel.setHTML(SERVER_ERROR);
                        dialogBox.center();
                        closeButton.setFocus(true);
                    }

                    public void onSuccess(String result) {
                        dialogBox.setText("Remote Procedure Call");
                        serverResponseLabel.removeStyleName("serverResponseLabelError");
                        serverResponseLabel.setHTML(result);
                        dialogBox.center();
                        closeButton.setFocus(true);
                    }
                });
            }
        }

        // Add a handler to send the name to the server
        MyHandler handler = new MyHandler();
        sendButton.addClickHandler(handler);
        nameField.addKeyUpHandler(handler);
    }
}
