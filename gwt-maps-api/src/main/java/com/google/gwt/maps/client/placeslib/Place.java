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
import com.google.gwt.maps.client.base.LatLng;

/**
 * Wrapper for the Places API Place object returned by PlaceAutocompleteElement.
 */
public class Place extends JavaScriptObject {

  protected Place() {
  }

  public final native String getId() /*-{
    return this.id || this.placeId || null;
  }-*/;

  public final native String getFormattedAddress() /*-{
    return this.formattedAddress || null;
  }-*/;

  public final native LatLng getLocation() /*-{
    return this.location || null;
  }-*/;

  /**
   * Gets the display name for the place.
   */
  public final native String getDisplayName() /*-{
    if (typeof this.displayName === 'string') {
      return this.displayName;
    }
    return (this.displayName && this.displayName.text) ? this.displayName.text : null;
  }-*/;

  public final void fetchFields(PlaceFetchFieldsHandler handler, String... fields) {
    if (handler == null) {
      return;
    }
    String[] safeFields = fields == null ? new String[0] : fields;
    JsArrayString array = ArrayHelper.toJsArrayString(safeFields);
    fetchFieldsImpl(array, handler);
  }

  private final native void fetchFieldsImpl(JsArrayString fields, PlaceFetchFieldsHandler handler) /*-{
    var request = {
      fields : fields
    };
    var promise = this.fetchFields(request);
    if (!promise || !promise.then) {
      $entry(handler.@com.google.gwt.maps.client.placeslib.PlaceFetchFieldsHandler::onSuccess(Lcom/google/gwt/maps/client/placeslib/Place;)(this));
      return;
    }
    promise.then(function(place) {
      $entry(handler.@com.google.gwt.maps.client.placeslib.PlaceFetchFieldsHandler::onSuccess(Lcom/google/gwt/maps/client/placeslib/Place;)(place));
    }, function(error) {
      var message = error && error.message ? error.message : (error ? "" + error : null);
      $entry(handler.@com.google.gwt.maps.client.placeslib.PlaceFetchFieldsHandler::onFailure(Ljava/lang/String;)(message));
    });
  }-*/;
}
