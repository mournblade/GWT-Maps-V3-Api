package com.google.gwt.maps.client.overlays;

/*
 * #%L
 * GWT Maps API V3 - Core API
 * %%
 * Copyright (C) 2011 - 2019 GWT Maps API V3
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


import com.google.gwt.ajaxloader.client.Properties;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.MapImpl;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.events.MapEventType;
import com.google.gwt.maps.client.events.click.GlobalClickMapEvent;
import com.google.gwt.maps.client.events.click.GlobalClickMapHandler;

public class OverlappingMarkerSpiderfier extends JavaScriptObject {

  protected OverlappingMarkerSpiderfier() {
  }

  public final static OverlappingMarkerSpiderfier newInstance(MapWidget map,
          OverlappingMarkerSpiderfierOptions options) {
    return createJso(map.getJso(), options).cast();
  }

  private static final native JavaScriptObject createJso(MapImpl map,
          OverlappingMarkerSpiderfierOptions options) /*-{
    return new $wnd.OverlappingMarkerSpiderfier(map, options);
  }-*/;

  public final native void addMarker(Marker marker) /*-{
    this.addMarker(marker);
  }-*/;

  public final native void removeMarker(Marker marker) /*-{
    this.removeMarker(marker);
  }-*/;

  public final native void setNearbyDistance(int nearbyDistance) /*-{
    this.nearbyDistance = nearbyDistance;
  }-*/;

  public final native void clearMarkers() /*-{
    this.clearMarkers();
  }-*/;

  public final void addClickHandler(GlobalClickMapHandler handler) {
    addHandlerImpl(MapEventType.CLICK.value(), handler);
  }

  private native void addHandlerImpl(String eventName, GlobalClickMapHandler handler) /*-{
    var callback = function(marker, event) {
      $entry(@com.google.gwt.maps.client.overlays.OverlappingMarkerSpiderfier::onCallback(Lcom/google/gwt/maps/client/events/click/GlobalClickMapHandler;Lcom/google/gwt/maps/client/overlays/Marker;Lcom/google/gwt/ajaxloader/client/Properties;)(handler, marker, event));
    };
    this.addListener(eventName, callback);
  }-*/;

  private static void onCallback(final GlobalClickMapHandler handler, final Marker marker,
          final Properties properties) {
    try {
      handler.onEvent(new GlobalClickMapEvent(marker, properties));
    } catch (Throwable x) {
      GWT.getUncaughtExceptionHandler().onUncaughtException(x);
    }
  }

}
