package com.google.gwt.maps.client.overlays;

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
