package com.google.gwt.maps.client.controls;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Options for the rendering of the fullscreen control on the map. <br>
 * <br>
 * See <a href="https://developers.google.com/maps/documentation/javascript/reference#FullscreenControlOptions">
 * FullscreenControlOptions API Doc</a>
 */
public class FullscreenControlOptions extends JavaScriptObject {

  /**
   * creates Options for the rendering of the fullscreen control on the map.
   */
  public static FullscreenControlOptions newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  /**
   * Options for the rendering of the fullscreen control on the map. use newInstance()
   */
  protected FullscreenControlOptions() {
  }

  /**
   * Position id. Used to specify the position of the control on the map. The default position is RIGHT_TOP.
   */
  public final ControlPosition getPosition() {
    return ControlPosition.fromValue(getPositionImpl());
  }

  private final native int getPositionImpl() /*-{
    return this.position;
  }-*/;

  /**
   * Position id. Used to specify the position of the control on the map. The default position is RIGHT_TOP.
   * 
   * @param position {@link ControlPosition}
   */
  public final void setPosition(ControlPosition position) {
    setPositionImpl(position.value());
  }

  private final native void setPositionImpl(int position) /*-{
    this.position = position;
  }-*/;

}
