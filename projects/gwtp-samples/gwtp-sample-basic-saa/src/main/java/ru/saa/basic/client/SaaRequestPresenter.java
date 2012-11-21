package ru.saa.basic.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.*;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 21.11.12
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class SaaRequestPresenter extends Presenter<SaaRequestPresenter.MyView, SaaRequestPresenter.MyProxy> {

    public static final String SAA_TOKEN = "saaToken";
    private final PlaceManager placeManagerSaa;

    @Inject
    public SaaRequestPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManagerSaa) {
        super(eventBus, view, proxy);
        this.placeManagerSaa = placeManagerSaa;
    }

    @Override
    protected void onBind() {
        super.onBind();
        registerHandler(getView().getSendSaaButtonSaa().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                sendSaaNameToServer();
            }
        }));
    }

    private void sendSaaNameToServer() {
        getView().setErrorSaa("");
        String text2Srv = getView().getNameSaa();

        if (text2Srv.matches("\\d+")) {
            getView().setErrorSaa("Name can't be digit");
            return;
        }

        placeManagerSaa.revealPlace(new PlaceRequest(SaaResponsePresenter.NAME_TOKEN).with(
                SaaResponsePresenter.textToServerParam, text2Srv));
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }

    interface MyView extends View {
        String getNameSaa();

        Button getSendSaaButtonSaa();

        void resetAndFocusSaa();

        void setErrorSaa(String errorText);

    }

    @ProxyStandard
    @NameToken(SAA_TOKEN)
    interface MyProxy extends Proxy<SaaRequestPresenter>, Place {
    }
}
