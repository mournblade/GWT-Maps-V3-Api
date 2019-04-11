package com.google.gwt.maps.client.overlays;

import com.google.gwt.core.client.JavaScriptObject;

public class MarkerClustererOptions extends JavaScriptObject {

  protected MarkerClustererOptions() {
  }

  public final static MarkerClustererOptions newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  public final native void setMaxZoom(int maxZoom) /*-{
    this.maxZoom = maxZoom;
  }-*/;

  public final native void setMinimumClusterSize(int minimumClusterSize) /*-{
    this.minimumClusterSize = minimumClusterSize;
  }-*/;

  public final native void setIgnoreHiddenMarkers(boolean ignoreHiddenMarkers) /*-{
    this.ignoreHiddenMarkers = ignoreHiddenMarkers;
  }-*/;

}
