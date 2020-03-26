package com.google.gwt.maps.client.events.click;

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
