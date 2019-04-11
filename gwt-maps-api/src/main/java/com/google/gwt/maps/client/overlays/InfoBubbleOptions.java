package com.google.gwt.maps.client.overlays;

import com.google.gwt.core.client.JavaScriptObject;

public class InfoBubbleOptions extends InfoWindowOptions {

  protected InfoBubbleOptions() {
  }

  public static InfoBubbleOptions newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  public final native void setHideCloseButton(boolean hideCloseButton) /*-{
    this.hideCloseButton = hideCloseButton;
  }-*/;

  public final native boolean getHideCloseButton() /*-{
    return this.hideCloseButton;
  }-*/;

  public final native void setCloseSrc(String closeSrc) /*-{
    this.closeSrc = closeSrc;
  }-*/;

  public final native String getCloseSrc() /*-{
    return this.closeSrc;
  }-*/;

  public final native void setBorderRadius(int borderRadius) /*-{
    this.borderRadius = borderRadius;
  }-*/;

  public final native int getBorderRadius() /*-{
    return this.borderRadius;
  }-*/;

  public final native void setShadowStyle(int shadowStyle) /*-{
    this.shadowStyle = shadowStyle;
  }-*/;

  public final native int getShadowStyle() /*-{
    return this.shadowStyle;
  }-*/;

  public final native void setPadding(int padding) /*-{
    this.padding = padding;
  }-*/;

  public final native int getPadding() /*-{
    return this.padding;
  }-*/;

  public final native void setDisableAnimation(boolean disableAnimation) /*-{
    this.disableAnimation = disableAnimation;
  }-*/;

  public final native boolean getDisableAnimation() /*-{
    return this.disableAnimation;
  }-*/;

  public final native void setArrowSize(int arrowSize) /*-{
    this.arrowSize = arrowSize;
  }-*/;

  public final native int getArrowSize() /*-{
    return this.arrowSize;
  }-*/;

}
