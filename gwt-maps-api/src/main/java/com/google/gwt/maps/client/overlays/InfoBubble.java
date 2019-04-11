package com.google.gwt.maps.client.overlays;

import com.google.gwt.core.client.JavaScriptObject;

public final class InfoBubble extends InfoWindow {

  protected InfoBubble() {
  }

  public static final InfoBubble newInstance(InfoBubbleOptions options) {
    return createJso(options).cast();
  }

  private static final native JavaScriptObject createJso(InfoBubbleOptions options) /*-{
    return new $wnd.InfoBubble(options);
  }-*/;

  public final void closeAndRemove() {
    close();
    remove();
  }

  public final native void remove() /*-{
    this.onRemove();
  }-*/;

}
