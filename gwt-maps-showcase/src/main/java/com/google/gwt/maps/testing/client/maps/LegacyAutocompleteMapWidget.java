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

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.events.bounds.BoundsChangeMapEvent;
import com.google.gwt.maps.client.events.bounds.BoundsChangeMapHandler;
import com.google.gwt.maps.client.placeslib.Autocomplete;
import com.google.gwt.maps.client.placeslib.AutocompleteOptions;
import com.google.gwt.maps.client.placeslib.PlaceGeometry;
import com.google.gwt.maps.client.placeslib.PlaceResult;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Demonstrates the Legacy Autocomplete using a standard GWT TextBox.
 * 
 * This version is "Smart" because we have removed the GEOCODE restriction,
 * allowing it to find Landmarks (like Stadshuset) and businesses.
 */
public class LegacyAutocompleteMapWidget extends Composite {

  private VerticalPanel pWidget;
  private MapWidget mapWidget;
  private TextBox searchTextBox;
  private Autocomplete autoComplete;

  public LegacyAutocompleteMapWidget() {
    pWidget = new VerticalPanel();
    initWidget(pWidget);

    draw();
  }

  private void draw() {
    pWidget.clear();

    searchTextBox = new TextBox();
    searchTextBox.setWidth("350px");
    searchTextBox.getElement().setAttribute("placeholder", "Search landmarks or addresses");

    HorizontalPanel hp = new HorizontalPanel();
    hp.add(searchTextBox);
    
    Button clearText = new Button("Clear Text");
    clearText.addStyleName("showcase-button");
    clearText.addClickHandler(event -> {
      searchTextBox.setText("");
    });
    hp.add(clearText);
    
    pWidget.add(hp);

    hp.setCellVerticalAlignment(searchTextBox, HorizontalPanel.ALIGN_BOTTOM);
    hp.setCellVerticalAlignment(clearText, HorizontalPanel.ALIGN_BOTTOM);

    drawMap();
    drawAutoComplete();
  }

  private void drawMap() {
    LatLng center = LatLng.newInstance(59.3293, 18.0686); // Stockholm
    MapOptions opts = MapOptions.newInstance();
    opts.setZoom(12);
    opts.setCenter(center);
    opts.setMapTypeId(MapTypeId.ROADMAP);

    mapWidget = new MapWidget(opts);
    pWidget.add(mapWidget);
    mapWidget.setSize("750px", "500px");
  }

  private void drawAutoComplete() {
    AutocompleteOptions options = AutocompleteOptions.newInstance();
    options.setBounds(mapWidget.getBounds());

    // Attach logic to the standard GWT TextBox
    autoComplete = Autocomplete.newInstance(searchTextBox.getElement(), options);

    // Handle selection
    autoComplete.addPlaceChangeHandler(event -> {
      PlaceResult result = autoComplete.getPlace();
      PlaceGeometry geo = result.getGeometry();
      if (geo != null) {
        LatLng location = geo.getLocation();
        mapWidget.panTo(location);
        mapWidget.setZoom(17);
        GWT.log("Selected: " + result.getName() + " at " + location.toString());
      }
    });

    mapWidget.addBoundsChangeHandler(new BoundsChangeMapHandler() {
      public void onEvent(BoundsChangeMapEvent event) {
        LatLngBounds bounds = mapWidget.getBounds();
        autoComplete.setBounds(bounds);
      }
    });
  }
}
