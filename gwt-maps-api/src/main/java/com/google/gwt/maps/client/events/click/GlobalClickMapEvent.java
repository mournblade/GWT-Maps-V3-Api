package com.google.gwt.maps.client.events.click;

import com.google.gwt.ajaxloader.client.Properties;
import com.google.gwt.maps.client.events.MapEvent;
import com.google.gwt.maps.client.events.MouseEvent;
import com.google.gwt.maps.client.overlays.Marker;

public class GlobalClickMapEvent extends MapEvent<GlobalClickMapHandler, GlobalClickMapEvent> {
  public static Type<GlobalClickMapHandler> TYPE = new Type<>();

  private Marker marker;

  public GlobalClickMapEvent(Marker marker, Properties properties) {
    super(properties);
    this.marker = marker;
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<GlobalClickMapHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(GlobalClickMapHandler handler) {
    handler.onEvent(this);
  }

  public MouseEvent getMouseEvent() {
    return new MouseEvent(properties);
  }

  public Marker getMarker() {
    return marker;
  }

}
