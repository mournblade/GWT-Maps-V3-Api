package com.google.gwt.maps.client.placeslib;

/*
 * #%L
 * GWT Maps API V3 - Core API
 * %%
 * Copyright (C) 2011 - 2012 GWT Maps API V3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.user.client.ui.Widget;

/**
 * Wrapper for the Places API PlaceAutocompleteElement web component.
 */
public class PlaceAutocompleteElement extends Widget {

  public PlaceAutocompleteElement() {
    setElement(createElement());
  }

  public Place getPlace() {
    return getPlaceImpl(getElement());
  }

  public void setPlaceholder(String placeholder) {
    if (placeholder == null) {
      getElement().removeAttribute("placeholder");
      return;
    }
    getElement().setAttribute("placeholder", placeholder);
  }

  public void setRequestedFields(String... fields) {
    String[] safeFields = fields == null ? new String[0] : fields;
    JsArrayString array = ArrayHelper.toJsArrayString(safeFields);
    setRequestedFieldsImpl(getElement(), array);
  }

  public void setTypes(AutocompleteType... types) {
    if (types == null) {
      return;
    }
    String[] values = new String[types.length];
    for (int i = 0; i < types.length; i++) {
      values[i] = types[i] == null ? null : types[i].value();
    }
    setTypesImpl(getElement(), ArrayHelper.toJsArrayString(values));
  }

  public void setTypes(String... types) {
    String[] safeTypes = types == null ? new String[0] : types;
    setTypesImpl(getElement(), ArrayHelper.toJsArrayString(safeTypes));
  }

  public void setLocationBias(LatLngBounds bounds) {
    setLocationBiasImpl(getElement(), bounds);
  }

  public void setLocationRestriction(LatLngBounds bounds) {
    setLocationRestrictionImpl(getElement(), bounds);
  }

  /**
   * Gets the value of the input field.
   */
  public final native String getValue() /*-{
    return this.@com.google.gwt.user.client.ui.Widget::getElement()().value || "";
  }-*/;

  /**
   * Sets the value of the input field.
   */
  public final native void setValue(String value) /*-{
    this.@com.google.gwt.user.client.ui.Widget::getElement()().value = value || "";
  }-*/;

  /**
   * Focus the input field.
   */
  public final native void focus() /*-{
    this.@com.google.gwt.user.client.ui.Widget::getElement()().focus();
  }-*/;

  public HandlerRegistration addPlaceSelectHandler(PlaceSelectHandler handler) {
    if (handler == null) {
      return new HandlerRegistration() {
        @Override
        public void removeHandler() {
        }
      };
    }
    final JavaScriptObject listener = addPlaceSelectHandlerImpl(getElement(), handler);
    return new HandlerRegistration() {
      @Override
      public void removeHandler() {
        removePlaceSelectHandlerImpl(getElement(), listener);
      }
    };
  }

  private static native Element createElement() /*-{
    if ($wnd.google && $wnd.google.maps && $wnd.google.maps.places
        && $wnd.google.maps.places.PlaceAutocompleteElement) {
      return new $wnd.google.maps.places.PlaceAutocompleteElement();
    }
    return $doc.createElement("gmp-place-autocomplete");
  }-*/;

  private static native Place getPlaceImpl(Element element) /*-{
    return element.place || null;
  }-*/;

  private static native void setRequestedFieldsImpl(Element element, JsArrayString fields) /*-{
    element.requestedFields = fields;
    element.requestedPlaceFields = fields;
    element.fields = fields;
  }-*/;

  private static native void setTypesImpl(Element element, JsArrayString types) /*-{
    element.types = types;
  }-*/;

  private static native void setLocationBiasImpl(Element element, LatLngBounds bounds) /*-{
    element.locationBias = bounds;
  }-*/;

  private static native void setLocationRestrictionImpl(Element element, LatLngBounds bounds) /*-{
    element.locationRestriction = bounds;
  }-*/;

  private static native JavaScriptObject addPlaceSelectHandlerImpl(Element element, PlaceSelectHandler handler) /*-{
    var callback = function(event) {
      var place = null;
      if (event) {
        place = event.place || (event.detail && event.detail.place) || null;
      }
      $entry(handler.@com.google.gwt.maps.client.placeslib.PlaceSelectHandler::onPlaceSelect(Lcom/google/gwt/maps/client/placeslib/Place;)(place));
    };
    element.addEventListener("gmp-placeselect", callback);
    return callback;
  }-*/;

  private static native void removePlaceSelectHandlerImpl(Element element, JavaScriptObject listener) /*-{
    element.removeEventListener("gmp-placeselect", listener);
  }-*/;
}
