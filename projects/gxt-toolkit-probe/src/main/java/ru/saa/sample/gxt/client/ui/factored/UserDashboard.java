package ru.saa.sample.gxt.client.ui.factored;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 03.08.12
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
public class UserDashboard extends Composite{
    interface UserDashboardUiBinder extends UiBinder<Widget, UserDashboard> {
    }

    private static UserDashboardUiBinder ourUiBinder = GWT.create(UserDashboardUiBinder.class);

    public UserDashboard() {

        initWidget(ourUiBinder.createAndBindUi(this));

    }
}