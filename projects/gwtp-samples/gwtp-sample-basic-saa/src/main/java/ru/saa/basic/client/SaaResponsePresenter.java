/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package ru.saa.basic.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.*;
import ru.saa.basic.shared.SendTextToServer;
import ru.saa.basic.shared.SendTextToServerResult;


/**
 * @author Philippe Beaudoin
 */
public class SaaResponsePresenter extends
    Presenter<SaaResponsePresenter.MyView, SaaResponsePresenter.MyProxy> {
  /**
   * {@link ru.saa.basic.client.SaaResponsePresenter}'s proxy.
   */
  @ProxyCodeSplit
  @NameToken(NAME_TOKEN)
  public interface MyProxy extends Proxy<SaaResponsePresenter>, Place {
  }

  /**
   * {@link ru.saa.basic.client.SaaResponsePresenter}'s view.
   */
  public interface MyView extends View {
    Button getCloseButton();

    void setServerResponse(String serverResponse);

    void setTextToServer(String textToServer);
  }

  public static final String NAME_TOKEN = "saaResponse";

  public static final String textToServerParam = "textToServerParam";

  private final DispatchAsync dispatcher;
  private final PlaceManager placeManager;

  private String textToServer;

  @Inject
  public SaaResponsePresenter(EventBus eventBus, MyView view, MyProxy proxy,
                              PlaceManager placeManager, DispatchAsync dispatcher) {
    super(eventBus, view, proxy);
    this.placeManager = placeManager;
    this.dispatcher = dispatcher;
  }

  @Override
  public void prepareFromRequest(PlaceRequest request) {
    super.prepareFromRequest(request);
    textToServer = request.getParameter(textToServerParam, null);
  }

  @Override
  protected void onBind() {
    super.onBind();
    registerHandler(getView().getCloseButton().addClickHandler(
        new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
            placeManager.revealPlace(new PlaceRequest(
                SaaRequestPresenter.SAA_TOKEN));
          }
        }));
  }

  @Override
  protected void onReset() {
    super.onReset();
    getView().setTextToServer(textToServer);
    getView().setServerResponse("Waiting for response...");
    dispatcher.execute(new SendTextToServer(textToServer),
        new AsyncCallback<SendTextToServerResult>() {
          @Override
          public void onFailure(Throwable caught) {
            getView().setServerResponse(
                "An error occured: " + caught.getMessage());
          }

          @Override
          public void onSuccess(SendTextToServerResult result) {
            getView().setServerResponse(result.getResponse());
          }
        });
  }

  @Override
  protected void revealInParent() {
    RevealRootContentEvent.fire(this, this);
  }
}