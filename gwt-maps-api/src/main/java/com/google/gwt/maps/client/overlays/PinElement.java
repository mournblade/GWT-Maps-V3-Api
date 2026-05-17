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

/**
 * PinElement is used to customize the appearance of an {@link AdvancedMarkerElement}.
 * <br>
 * See <a href="https://developers.google.com/maps/documentation/javascript/reference/advanced-markers#PinElement">PinElement API Doc</a>
 */
public class PinElement extends JavaScriptObject {

  protected PinElement() {
  }

  /**
   * Creates a pin element with the options specified.
   * 
   * @param options {@link PinElementOptions}
   */
  public static PinElement newInstance(PinElementOptions options) {
    return createJso(options).cast();
  }

  private static final native JavaScriptObject createJso(PinElementOptions options) /*-{
    return new $wnd.google.maps.marker.PinElement(options);
  }-*/;

  /**
   * Returns the DOM element representing the pin.
   */
  public final native Element getElement() /*-{
    return this.element;
  }-*/;

  /**
   * Sets the background color.
   */
  public final native void setBackground(String background) /*-{
    this.background = background;
  }-*/;

  /**
   * Sets the border color.
   */
  public final native void setBorderColor(String borderColor) /*-{
    this.borderColor = borderColor;
  }-*/;

  /**
   * Sets the glyph (content inside the pin).
   * 
   * @deprecated Use {@link #setGlyphText(String)} or {@link #setGlyphSrc(String)} instead.
   */
  @Deprecated
  public final native void setGlyph(String glyph) /*-{
    this.glyph = glyph;
  }-*/;

  /**
   * Sets the glyph text.
   */
  public final native void setGlyphText(String glyphText) /*-{
    this.glyphText = glyphText;
  }-*/;

  /**
   * Sets the glyph source URL.
   */
  public final native void setGlyphSrc(String glyphSrc) /*-{
    this.glyphSrc = glyphSrc;
  }-*/;

  /**
   * Sets the glyph (content inside the pin). Can be a string or a DOM element.
   */
  public final native void setGlyph(Element glyph) /*-{
    this.glyph = glyph;
  }-*/;

  /**
   * Sets the glyph color.
   */
  public final native void setGlyphColor(String glyphColor) /*-{
    this.glyphColor = glyphColor;
  }-*/;

  /**
   * Sets the scale of the pin.
   */
  public final native void setScale(double scale) /*-{
    this.scale = scale;
  }-*/;

}
