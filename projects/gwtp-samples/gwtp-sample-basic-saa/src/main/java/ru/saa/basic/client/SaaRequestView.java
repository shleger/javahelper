package ru.saa.basic.client;

import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.samples.basic.client.MainPageView;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 21.11.12
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
public class SaaRequestView extends ViewImpl implements SaaRequestPresenter.MyView {

    private final Label errorLabel;
    private final TextBox nameField;
    private final Button sendButton;
    private HTMLPanel panel;

    @Inject
    public SaaRequestView() {

         panel = new HTMLPanel(MainPageView.html);

        sendButton = new Button("Send to");
        nameField = new TextBox();
        nameField.setText("Ya User");
        errorLabel = new Label();

        // We can add style names to widgets
//        sendButton.addStyleName("sendButton");     //todo   why err ??

        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element
        panel.add(nameField, "nameFieldContainer");
        panel.add(sendButton, "sendButtonContainer");
        panel.add(errorLabel, "errorLabelContainer");
    }

    @Override
    public String getNameSaa() {
        return nameField.getText();
    }

    @Override
    public Button getSendSaaButtonSaa() {
        return sendButton;
    }

    @Override
    public void resetAndFocusSaa() {
        nameField.setFocus(true);
        nameField.selectAll();
    }

    @Override
    public void setErrorSaa(String errorText) {
        errorLabel.setText(errorText);
    }

    @Override
    public Widget asWidget() {
        return panel;
    }
}
