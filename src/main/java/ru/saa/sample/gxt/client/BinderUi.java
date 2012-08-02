package ru.saa.sample.gxt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 02.08.12
 * Time: 16:29
 * To change this template use File | Settings | File Templates.
 */
public class BinderUi {
    interface BinderUiUiBinder extends UiBinder<DivElement, BinderUi> {
    }

    @UiField
    SpanElement nameSpan;


    DivElement rootElement;

    private static BinderUiUiBinder ourUiBinder = GWT.create(BinderUiUiBinder.class);

    public BinderUi() {
         rootElement = ourUiBinder.createAndBindUi(this);
    }



    public void  setTitle(String title){
           nameSpan.setInnerText(title);
    }

    public Element getElement() {
        return rootElement;
    }
}