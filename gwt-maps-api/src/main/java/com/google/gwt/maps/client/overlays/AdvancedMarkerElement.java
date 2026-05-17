package com.google.gwt.maps.client.overlays;

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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.maps.client.MapImpl;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.MapEventType;
import com.google.gwt.maps.client.events.MapHandlerRegistration;
import com.google.gwt.maps.client.events.click.ClickEventFormatter;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.mvc.MVCObject;

/**
 * AdvancedMarkerElement is a modern, highly customizable marker.
 * <br>
 * See <a href="https://developers.google.com/maps/documentation/javascript/reference/advanced-markers#AdvancedMarkerElement">AdvancedMarkerElement API Doc</a>
 */
public class AdvancedMarkerElement extends MVCObject<AdvancedMarkerElement> {

  protected AdvancedMarkerElement() {
  }

  /**
   * Creates an advanced marker with the options specified.
   * 
   * @param options {@link AdvancedMarkerElementOptions}
   */
  public static AdvancedMarkerElement newInstance(AdvancedMarkerElementOptions options) {
    return createJso(options).cast();
  }

  private static final native JavaScriptObject createJso(AdvancedMarkerElementOptions options) /*-{
    return new $wnd.google.maps.marker.AdvancedMarkerElement(options);
  }-*/;

  /**
   * get content
   */
  public final native Element getContent() /*-{
    return this.content;
  }-*/;

  /**
   * set content
   */
  public final native void setContent(Element content) /*-{
    this.content = content;
  }-*/;

  /**
   * set content
   */
  public final native void setContent(JavaScriptObject content) /*-{
    this.content = content;
  }-*/;

  /**
   * get map
   */
  public final MapWidget getMap() {
    return MapWidget.newInstance(getMapImpl());
  }

  private final native MapImpl getMapImpl() /*-{
    return this.map;
  }-*/;

  /**
   * set map
   */
  public final void setMap(MapWidget mapWidget) {
    if (mapWidget == null) {
      setMapImpl(null);
    } else {
      setMapImpl(mapWidget.getJso());
    }
  }

  private final native void setMapImpl(MapImpl map) /*-{
    this.map = map;
  }-*/;

  /**
   * get position
   */
  public final native LatLng getPosition() /*-{
    return this.position;
  }-*/;

  /**
   * set position
   */
  public final native void setPosition(LatLng position) /*-{
    this.position = position;
  }-*/;

  /**
   * get title
   */
  public final native String getTitle() /*-{
    return this.title;
  }-*/;

  /**
   * set title
   */
  public final native void setTitle(String title) /*-{
    this.title = title;
  }-*/;

  /**
   * get zIndex
   */
  public final native double getZIndex() /*-{
    return this.zIndex;
  }-*/;

  /**
   * set zIndex
   */
  public final native void setZIndex(double zIndex) /*-{
    this.zIndex = zIndex;
  }-*/;

  /**
   * Adds a click handler to the marker.
   */
  public final HandlerRegistration addClickHandler(ClickMapHandler handler) {
    return MapHandlerRegistration.addHandler(this, MapEventType.CLICK, handler, new ClickEventFormatter());
  }

  /**
   * Close the marker (remove from map).
   */
  public final void close() {
    setMap(null);
  }
}
