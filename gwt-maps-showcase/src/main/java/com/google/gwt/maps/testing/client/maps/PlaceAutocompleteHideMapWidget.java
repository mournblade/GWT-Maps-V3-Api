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
import com.google.gwt.maps.client.controls.ControlPosition;
import com.google.gwt.maps.client.events.bounds.BoundsChangeMapEvent;
import com.google.gwt.maps.client.events.bounds.BoundsChangeMapHandler;
import com.google.gwt.maps.client.placeslib.Place;
import com.google.gwt.maps.client.placeslib.PlaceAutocompleteMapControl;
import com.google.gwt.maps.client.placeslib.PlaceFetchFieldsHandler;
import com.google.gwt.maps.client.placeslib.PlaceSelectHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Focused repro for PlaceAutocompleteElement show/hide lifecycle behavior.
 */
public class PlaceAutocompleteHideMapWidget extends Composite {

  private final VerticalPanel pWidget;
  private final SimplePanel hiddenContainer;
  private final Button toggleVisibilityButton;
  private final Button toggleMapControlButton;

  private MapWidget mapWidget;
  private PlaceAutocompleteMapControl placeAutocomplete;
  private boolean containerVisible = true;

  public PlaceAutocompleteHideMapWidget() {
    pWidget = new VerticalPanel();
    hiddenContainer = new SimplePanel();
    toggleVisibilityButton = new Button("Hide containing panel");
    toggleMapControlButton = new Button("Detach map control");
    initWidget(pWidget);

    draw();
  }

  private void draw() {
    pWidget.clear();

    pWidget.add(new HTML("<h3>PlaceAutocompleteElement hide repro</h3>"
        + "<p>Type in the autocomplete so the clear X appears, then hide the containing panel. "
        + "If the X remains visible, the component is leaking UI outside its hidden parent.</p>"));

    toggleVisibilityButton.addClickHandler(event -> toggleContainerVisibility());
    toggleMapControlButton.addClickHandler(event -> toggleMapControl());
    pWidget.add(toggleVisibilityButton);
    pWidget.add(toggleMapControlButton);

    drawMap();
    drawAutoComplete();
  }

  private void drawMap() {
    LatLng center = LatLng.newInstance(59.3293, 18.0686);
    MapOptions opts = MapOptions.newInstance();
    opts.setZoom(10);
    opts.setCenter(center);
    opts.setMapTypeId(MapTypeId.ROADMAP);

    mapWidget = new MapWidget(opts);
    mapWidget.setSize("750px", "500px");
    hiddenContainer.setWidget(mapWidget);
    pWidget.add(hiddenContainer);
  }

  private void drawAutoComplete() {
    placeAutocomplete = new PlaceAutocompleteMapControl(mapWidget, ControlPosition.TOP_LEFT);
    placeAutocomplete.setPlaceholder("Search places");
    placeAutocomplete.setRequestedFields("location", "displayName", "formattedAddress");
    placeAutocomplete.setLocationBias(mapWidget.getBounds());

    attachMapControl();

    placeAutocomplete.addPlaceSelectHandler(new PlaceSelectHandler() {
      public void onPlaceSelect(final Place place) {
        if (place == null) {
          return;
        }
        place.fetchFields(new PlaceFetchFieldsHandler() {
          public void onSuccess(Place fetchedPlace) {
            LatLng center = fetchedPlace.getLocation();
            if (center == null) {
              return;
            }
            mapWidget.panTo(center);
            GWT.log("place selected center=" + center);
          }

          public void onFailure(String message) {
            GWT.log("place fetch failed: " + message);
          }
        }, "location");
      }
    });

    mapWidget.addBoundsChangeHandler(new BoundsChangeMapHandler() {
      public void onEvent(BoundsChangeMapEvent event) {
        LatLngBounds bounds = mapWidget.getBounds();
        placeAutocomplete.setLocationBias(bounds);
      }
    });
  }

  private void toggleContainerVisibility() {
    containerVisible = !containerVisible;
    hiddenContainer.setVisible(containerVisible);
    toggleVisibilityButton.setText(containerVisible ? "Hide containing panel" : "Show containing panel");
  }

  private void toggleMapControl() {
    if (placeAutocomplete.isAttachedToMap()) {
      detachMapControl();
    } else {
      attachMapControl();
    }
  }

  private void attachMapControl() {
    placeAutocomplete.attach();
    toggleMapControlButton.setText("Detach map control");
  }

  private void detachMapControl() {
    placeAutocomplete.detach();
    toggleMapControlButton.setText("Attach map control");
  }

}
