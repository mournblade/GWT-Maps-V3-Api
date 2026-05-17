package com.google.gwt.maps.testing.client.maps;

/*
 * #%L
 * GWT Maps API V3 - Showcase
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

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlays.AdvancedMarkerElement;
import com.google.gwt.maps.client.overlays.AdvancedMarkerElementOptions;
import com.google.gwt.maps.client.overlays.PinElement;
import com.google.gwt.maps.client.overlays.PinElementOptions;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AdvancedMarkerMapWidget extends Composite {

  private final VerticalPanel pWidget;
  private MapWidget mapWidget;

  public AdvancedMarkerMapWidget() {
    pWidget = new VerticalPanel();
    initWidget(pWidget);

    draw();
  }

  private void draw() {
    pWidget.add(new HTML("<br>Advanced Marker Example (Requires Map ID for full features)"));

    drawMap();
    drawAdvancedMarker();
    drawCustomPinMarker();
  }

  private void drawMap() {
    LatLng center = LatLng.newInstance(47.6062, -122.3321); // Seattle
    MapOptions opts = MapOptions.newInstance();
    opts.setZoom(10);
    opts.setCenter(center);
    opts.setMapTypeId(MapTypeId.ROADMAP);
    
    // Support testing with a real Map ID via URL parameter
    String mapId = com.google.gwt.user.client.Window.Location.getParameter("mapId");
    if (mapId != null && !mapId.isEmpty()) {
      opts.setMapId(mapId);
    }

    mapWidget = new MapWidget(opts);
    pWidget.add(mapWidget);
    mapWidget.setSize("750px", "500px");
  }

  private void drawAdvancedMarker() {
    LatLng position = LatLng.newInstance(47.6062, -122.3321);
    AdvancedMarkerElementOptions options = AdvancedMarkerElementOptions.newInstance();
    options.setPosition(position);
    options.setTitle("Standard Advanced Marker");
    options.setMap(mapWidget);

    AdvancedMarkerElement.newInstance(options);
  }

  private void drawCustomPinMarker() {
    LatLng position = LatLng.newInstance(47.6205, -122.3493); // Space Needle
    
    PinElementOptions pinOptions = PinElementOptions.newInstance();
    pinOptions.setBackground("#FBBC04");
    pinOptions.setBorderColor("#137333");
    pinOptions.setGlyphText("S");
    pinOptions.setGlyphColor("white");
    
    PinElement pin = PinElement.newInstance(pinOptions);
    
    AdvancedMarkerElementOptions options = AdvancedMarkerElementOptions.newInstance();
    options.setPosition(position);
    options.setTitle("Custom Pin Marker");
    options.setContent(pin);
    options.setMap(mapWidget);

    AdvancedMarkerElement.newInstance(options);
  }

}
