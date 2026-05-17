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
import com.google.gwt.maps.client.MapImpl;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;

/**
 * Options for {@link AdvancedMarkerElement}.
 */
public class AdvancedMarkerElementOptions extends JavaScriptObject {

  protected AdvancedMarkerElementOptions() {
  }

  public final static AdvancedMarkerElementOptions newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  /**
   * Sets the map on which to display the marker.
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
   * Sets the marker's position.
   */
  public final native void setPosition(LatLng position) /*-{
    this.position = position;
  }-*/;

  /**
   * Sets the rollover text.
   */
  public final native void setTitle(String title) /*-{
    this.title = title;
  }-*/;

  /**
   * Sets the content of the marker. Can be a DOM element or a PinElement.
   */
  public final native void setContent(Element content) /*-{
    this.content = content;
  }-*/;

  /**
   * Sets the content of the marker. Can be a DOM element or a PinElement.
   */
  public final native void setContent(JavaScriptObject content) /*-{
    this.content = content;
  }-*/;

  /**
   * Sets whether the marker is clickable.
   */
  public final native void setGmpClickable(boolean clickable) /*-{
    this.gmpClickable = clickable;
  }-*/;

  /**
   * Sets whether the marker is draggable.
   */
  public final native void setGmpDraggable(boolean draggable) /*-{
    this.gmpDraggable = draggable;
  }-*/;

  /**
   * Sets the collision behavior.
   */
  public final native void setCollisionBehavior(String collisionBehavior) /*-{
    this.collisionBehavior = collisionBehavior;
  }-*/;

  /**
   * Sets the zIndex.
   */
  public final native void setZIndex(double zIndex) /*-{
    this.zIndex = zIndex;
  }-*/;

}
