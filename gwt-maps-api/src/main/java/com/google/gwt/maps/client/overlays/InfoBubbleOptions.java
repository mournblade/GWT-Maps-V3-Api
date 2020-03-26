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
