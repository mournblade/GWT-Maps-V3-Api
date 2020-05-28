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

import com.google.gwt.core.client.*;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.click.ClickMapEvent;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.placeslib.PlaceDetailsHandler;
import com.google.gwt.maps.client.placeslib.PlaceDetailsRequest;
import com.google.gwt.maps.client.placeslib.PlaceResult;
import com.google.gwt.maps.client.placeslib.PlaceSearchHandler;
import com.google.gwt.maps.client.placeslib.PlaceSearchPagination;
import com.google.gwt.maps.client.placeslib.PlaceSearchRequest;
import com.google.gwt.maps.client.placeslib.PlacesService;
import com.google.gwt.maps.client.placeslib.PlacesServiceStatus;
import com.google.gwt.maps.client.placeslib.placeresult.PlaceResultOpeningHours;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PlaceSearchMapWidget extends Composite {

  private static double STHLM_LAT = 59.332790;
  private static double STHLM_LON = 18.064490;

  private final VerticalPanel pWidget;
  private MapWidget mapWidget;

  public PlaceSearchMapWidget() {
    pWidget = new VerticalPanel();
    initWidget(pWidget);

    draw();
  }

  private void draw() {
    pWidget.clear();
    pWidget.add(new HTML("<br>Click on the map to start the PlaceSearchRequest"));

    drawMap(STHLM_LAT, STHLM_LON);
  }

  private void searchRequest(LatLng clickLocation) {
    String[] types = new String[1];
    types[0] = "establishment";

    PlaceSearchRequest request = PlaceSearchRequest.newInstance();
    request.setLocation(clickLocation);
    request.setRadius(500d);
    request.setName("Mcdonalds");
    // TODO add more AutocompleteTypes...
    // request.setTypes(AutocompleteType.ESTABLISHMENT);
    request.setTypes(types);

    PlacesService placeService = PlacesService.newInstance(mapWidget);
    placeService.nearbySearch(request, new PlaceSearchHandler() {

      @Override
      public void onCallback(JsArray<PlaceResult> results, PlaceSearchPagination pagination, PlacesServiceStatus status) {

        if (status == PlacesServiceStatus.OK) {
          GWT.log("I found this many places " + results.length());

          // look up the details for the first place
          if (results.length() > 0) {
            PlaceResult result = results.get(0);
            String reference = result.getReference();
            getPlaceDetails(reference);

            String json = new JSONObject(result).toString();
            GWT.log("details=" + json);
          }
        } else {
          Window.alert("Status is: status=" + status);
        }
      }

    });
  }

  private void getPlaceDetails(String reference) {
    if (reference == null || reference.isEmpty()) {
      return;
    }

    PlacesService placeService = PlacesService.newInstance(mapWidget);
    PlaceDetailsRequest request = PlaceDetailsRequest.newInstance();
    request.setReference(reference);

    placeService.getDetails(request, new PlaceDetailsHandler() {
      @Override
      public void onCallback(PlaceResult result, PlacesServiceStatus status) {
        PlaceResultOpeningHours openingHours = result.getOpeningHours();
        JsDate isClosedTime = JsDate.create(2020, 4, 28, 4, 0, 0);
        JsDate isOpenTime = JsDate.create(2020, 4, 28, 12, 0, 0);
        GWT.log(result.getName() + " is open now: " + openingHours.getIsOpen());
        GWT.log(result.getName() + " should be closed at " + isClosedTime.toLocaleString() + " : " + !openingHours.getIsOpenAtTime(isClosedTime));
        GWT.log(result.getName() + " should be open at " + isOpenTime.toLocaleString() + " : " + openingHours.getIsOpenAtTime(isOpenTime));
        GWT.log(result.getName() + " utc offset: " + result.getUtcOffset());
        if (status == PlacesServiceStatus.OK) {
          Window.alert("Found place details: name=" + result.getName());
        } else {
          String json = new JSONObject(result).toString();
          GWT.log("details=" + json);
          Window.alert("Status is: status=" + status + " ::: " + json);
        }
      }
    });
    
  }

  private void drawMap(double lat, double lon) {
    LatLng center = LatLng.newInstance(lat, lon);
    MapOptions opts = MapOptions.newInstance();
    opts.setZoom(20);
    opts.setCenter(center);
    opts.setMapTypeId(MapTypeId.HYBRID);

    mapWidget = new MapWidget(opts);
    pWidget.add(mapWidget);
    mapWidget.setSize("750px", "500px");

    mapWidget.addClickHandler(new ClickMapHandler() {
      @Override
      public void onEvent(ClickMapEvent event) {
        // TODO fix the event getting, getting ....
        GWT.log("clicked on latlng=" + event.getMouseEvent().getLatLng());

        searchRequest(event.getMouseEvent().getLatLng());
      }
    });
  }

}
