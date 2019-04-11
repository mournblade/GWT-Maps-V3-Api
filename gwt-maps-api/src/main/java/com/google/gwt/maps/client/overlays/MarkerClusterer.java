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


import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.MapImpl;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.mvc.MVCArray;

public class MarkerClusterer extends JavaScriptObject {

  protected MarkerClusterer() {
  }

  public final static MarkerClusterer newInstance(MapWidget map, MVCArray<Marker> markers,
          MarkerClustererOptions options) {
    return createJso(map.getJso(), markers, options).cast();
  }

  private static final native JavaScriptObject createJso(MapImpl map, MVCArray<Marker> markers,
          MarkerClustererOptions options) /*-{
    return new $wnd.MarkerClusterer(map, markers, options);
  }-*/;

  public final native void addMarker(Marker marker) /*-{
    this.addMarker(marker);
  }-*/;

  public final native void removeMarker(Marker marker) /*-{
    this.removeMarker(marker);
  }-*/;

  public final native void setMinimumClusterSize(int minimumClusterSize) /*-{
    this.minClusterSize_ = minimumClusterSize;
  }-*/;

  public final native void clearMarkers() /*-{
    this.clearMarkers();
  }-*/;

  public final native void repaint() /*-{
    this.repaint();
  }-*/;

}
