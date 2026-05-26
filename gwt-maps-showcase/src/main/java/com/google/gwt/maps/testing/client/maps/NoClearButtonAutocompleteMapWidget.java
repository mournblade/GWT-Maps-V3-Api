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
import com.google.gwt.maps.client.placeslib.PlaceAutocompleteElement;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Demonstrates the modern PlaceAutocompleteElement with the clear button toggle.
 * <br>Note: Requires Google Maps version 3.65.1c or later (v=weekly).
 */
public class NoClearButtonAutocompleteMapWidget extends Composite {

  private VerticalPanel pWidget;
  private MapWidget mapWidget;
  private PlaceAutocompleteElement placeAutocomplete;
  private boolean noClearButton = true;

  public NoClearButtonAutocompleteMapWidget() {
    pWidget = new VerticalPanel();
    initWidget(pWidget);
    draw();
  }

  private void draw() {
    pWidget.clear();

    placeAutocomplete = new PlaceAutocompleteElement();
    placeAutocomplete.setWidth("350px");
    placeAutocomplete.setPlaceholder("Search places");
    
    // 1. Initial state
    placeAutocomplete.setNoClearButton(noClearButton);

    Button toggleButton = new Button(noClearButton ? "Show Clear Button" : "Hide Clear Button");
    toggleButton.addStyleName("showcase-button");
    toggleButton.addClickHandler(event -> {
      noClearButton = !noClearButton;
      placeAutocomplete.setNoClearButton(noClearButton);
      toggleButton.setText(noClearButton ? "Show Clear Button" : "Hide Clear Button");
    });

    Button clearText = new Button("Clear Text");
    clearText.addStyleName("showcase-button");
    clearText.addClickHandler(event -> {
      placeAutocomplete.setValue("");
    });

    HorizontalPanel hp = new HorizontalPanel();
    hp.add(placeAutocomplete);
    hp.add(toggleButton);
    hp.add(clearText);
    pWidget.add(hp);

    hp.setCellVerticalAlignment(placeAutocomplete, HorizontalPanel.ALIGN_BOTTOM);
    hp.setCellVerticalAlignment(toggleButton, HorizontalPanel.ALIGN_BOTTOM);
    hp.setCellVerticalAlignment(clearText, HorizontalPanel.ALIGN_BOTTOM);

    drawMap();
  }

  private void drawMap() {
    LatLng center = LatLng.newInstance(59.3293, 18.0686);
    MapOptions opts = MapOptions.newInstance();
    opts.setZoom(10);
    opts.setCenter(center);
    opts.setMapTypeId(MapTypeId.ROADMAP);

    mapWidget = new MapWidget(opts);
    pWidget.add(mapWidget);
    mapWidget.setSize("750px", "500px");
  }
}
