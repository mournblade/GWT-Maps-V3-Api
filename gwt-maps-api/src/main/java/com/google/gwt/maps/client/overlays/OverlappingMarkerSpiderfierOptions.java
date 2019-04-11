package com.google.gwt.maps.client.overlays;

import com.google.gwt.core.client.JavaScriptObject;

public class OverlappingMarkerSpiderfierOptions extends JavaScriptObject {

  protected OverlappingMarkerSpiderfierOptions() {
  }

  public final static OverlappingMarkerSpiderfierOptions newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  public final native void setNearbyDistance(int nearbyDistance) /*-{
    this.nearbyDistance = nearbyDistance;
  }-*/;

  public final native void setCircleFootSeparation(int circleFootSeparation) /*-{
    this.circleFootSeparation = circleFootSeparation;
  }-*/;

  public final native void setSpiralFootSeparation(int spiralFootSeparation) /*-{
    this.spiralFootSeparation = spiralFootSeparation;
  }-*/;

  public final native void setSpiralLengthFactor(int spiralLengthFactor) /*-{
    this.spiralLengthFactor = spiralLengthFactor;
  }-*/;

  public final native void setKeepSpiderfied(boolean keepSpiderfied) /*-{
    this.keepSpiderfied = keepSpiderfied;
  }-*/;

}
