package ru.saa.sample.gxt.client.ui.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 03.08.12
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class HelloWidgetWorld  extends Composite{
    interface HelloWidgetWorldUiBinder extends UiBinder<Widget, HelloWidgetWorld> {
    }

    private static HelloWidgetWorldUiBinder ourUiBinder = GWT.create(HelloWidgetWorldUiBinder.class);

    @UiField
    ListBox listBox;



    public HelloWidgetWorld(String ... names) {

        initWidget(ourUiBinder.createAndBindUi(this));

        for (String name : names) {
            listBox.addItem(name);
        }

    }
}