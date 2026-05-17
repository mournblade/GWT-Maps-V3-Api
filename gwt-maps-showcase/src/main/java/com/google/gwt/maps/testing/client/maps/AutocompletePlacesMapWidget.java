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
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.placeslib.Place;
import com.google.gwt.maps.client.placeslib.PlaceAutocompleteElement;
import com.google.gwt.maps.client.placeslib.PlaceFetchFieldsHandler;
import com.google.gwt.maps.client.placeslib.PlaceSelectHandler;
import com.google.gwt.maps.client.placeslib.AutocompleteType;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * <br>
 * <br>
 * See <a href=
 * "https://developers.google.com/maps/documentation/javascript/layers.html#FusionTables"
 * >FusionTables API Doc</a>
 */
public class AutocompletePlacesMapWidget extends Composite {

  private VerticalPanel pWidget;

  private MapWidget mapWidget;

  private PlaceAutocompleteElement placeAutocomplete;

  public AutocompletePlacesMapWidget() {
    pWidget = new VerticalPanel();
    initWidget(pWidget);

    draw();
  }

  private void draw() {

    pWidget.clear();

    HTML html = new HTML("<br><br>Map with autocomplete places &nbsp;&nbsp;");
    placeAutocomplete = new PlaceAutocompleteElement();
    placeAutocomplete.setWidth("350px");
    placeAutocomplete.setPlaceholder("Search places");

    HorizontalPanel hp = new HorizontalPanel();
    hp.add(html);
    hp.add(placeAutocomplete);

    pWidget.add(hp);

    hp.setCellVerticalAlignment(placeAutocomplete, HorizontalPanel.ALIGN_BOTTOM);

    drawMap();

    drawAutoComplete();
  }

  private void drawMap() {
    LatLng center = LatLng.newInstance(49.496675, -102.65625);
    MapOptions opts = MapOptions.newInstance();
    opts.setZoom(8);
    opts.setCenter(center);
    opts.setMapTypeId(MapTypeId.TERRAIN);

    mapWidget = new MapWidget(opts);
    pWidget.add(mapWidget);
    mapWidget.setSize("750px", "500px");

    mapWidget.addClickHandler(new ClickMapHandler() {
      public void onEvent(ClickMapEvent event) {
        // TODO fix the event getting, getting ....
        GWT.log("clicked on latlng=" + event.getMouseEvent().getLatLng());
      }
    });
  }

  private void drawAutoComplete() {

    AutocompleteType[] types = new AutocompleteType[2];
    types[0] = AutocompleteType.ESTABLISHMENT;
    types[1] = AutocompleteType.GEOCODE;

    // placeAutocomplete.setTypes(types);
    placeAutocomplete.setRequestedFields("location");
    placeAutocomplete.setLocationBias(mapWidget.getBounds());

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
            // mapWidget.setZoom(8);
            GWT.log("place changed center=" + center);
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

}
