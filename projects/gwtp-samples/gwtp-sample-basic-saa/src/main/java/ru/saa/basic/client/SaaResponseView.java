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

import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.samples.basic.client.ResponseView;

/**
 * @author Philippe Beaudoin
 */
public class SaaResponseView extends ViewImpl implements SaaResponsePresenter.MyView {


  HTMLPanel panel = new HTMLPanel(ResponseView.html);

  private final Button closeButton;
  private final HTML serverResponseLabel;
  private final Label textToServerLabel;

  @Inject
  public SaaResponseView() {
    closeButton = new Button("Close");
    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");
    textToServerLabel = new Label();
    serverResponseLabel = new HTML();

    // Add the nameField and sendButton to the RootPanel
    // Use RootPanel.get() to get the entire body element
    panel.add(closeButton, "closeButton");
    panel.add(textToServerLabel, "textToServerContainer");
    panel.add(serverResponseLabel, "serverResponseContainer");
  }

  @Override
  public Widget asWidget() {
    return panel;
  }

  @Override
  public Button getCloseButton() {
    return closeButton;
  }

  @Override
  public void setServerResponse(String serverResponse) {
    serverResponseLabel.setHTML(serverResponse);
  }

  @Override
  public void setTextToServer(String textToServer) {
    textToServerLabel.setText(textToServer);
  }

}
