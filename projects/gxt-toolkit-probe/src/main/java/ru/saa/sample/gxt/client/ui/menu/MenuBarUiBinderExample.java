package ru.saa.sample.gxt.client.ui.menu;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MenuBarUiBinderExample implements IsWidget, EntryPoint {

    interface MyUiBinder extends UiBinder<Widget, MenuBarUiBinderExample> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    public Widget asWidget() {
        return uiBinder.createAndBindUi(this);
    }

    public void onModuleLoad() {
        RootPanel.get().add(asWidget());
    }

    @UiHandler(value = {"menuNew", "menuOpen", "menuEdit", "menuSearch", "menuFoo", "menuTheme"})
    public void onMenuSelection(SelectionEvent<Item> event) {
        MenuItem item = (MenuItem) event.getSelectedItem();
        Info.display("Action", "You selected the " + item.getText());
    }
}
