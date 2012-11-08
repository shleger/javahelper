package ru.saa.sample.gxt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.sencha.gxt.widget.core.client.button.TextButton;
import ru.saa.sample.gxt.client.internationalization.LangConstants;
import ru.saa.sample.gxt.client.internationalization.LangConstantsMessages;
import ru.saa.sample.gxt.client.ui.factored.UserDashboard;
import ru.saa.sample.gxt.client.ui.menu.MenuBarUiBinderExample;
import ru.saa.sample.gxt.client.ui.verysimple.BinderUi;
import ru.saa.sample.gxt.client.ui.widget.HelloWidgetWorld;
import ru.saa.sample.gxt.shared.FieldVerifier;

import java.util.Date;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * @see  <url>https://developers.google.com/web-toolkit/doc/latest/DevGuideUiBinder#Hello_World</url>
 *
 */
public class GridKit implements EntryPoint {
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



        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element

        // GXT button
        RootPanel.get("gxtContainer").add(textButton);




    }
}
