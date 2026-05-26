package com.google.gwt.maps.testing.client;

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

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.maps.testing.client.maps.*;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Modernized GWT Maps Showcase (2026).
 * Demonstrates both legacy and modern features of the Google Maps JS API.
 */
public class Showcase implements EntryPoint {

  private final String mapsContainer = "maps";

  @Override
  public void onModuleLoad() {
    loadMapApi();
  }

  private void loadMapApi() {
    // Load all necessary libraries
    ArrayList<LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
    loadLibraries.add(LoadLibrary.DRAWING);
    loadLibraries.add(LoadLibrary.GEOMETRY);
    loadLibraries.add(LoadLibrary.PLACES);
    loadLibraries.add(LoadLibrary.VISUALIZATION);
    loadLibraries.add(LoadLibrary.MARKER);

    Runnable onLoad = () -> draw();

    // Support credentials and versioning via URL parameters
    String key = com.google.gwt.user.client.Window.Location.getParameter("key");
    String v = com.google.gwt.user.client.Window.Location.getParameter("v");
    String map_ids = com.google.gwt.user.client.Window.Location.getParameter("map_ids");
    
    String otherParams = (key != null && !key.isEmpty()) ? "key=" + key : "";
    if (map_ids != null && !map_ids.isEmpty()) {
      otherParams += (otherParams.isEmpty() ? "" : "&") + "map_ids=" + map_ids;
    }
    
    LoadApi.go(onLoad, loadLibraries, null, otherParams.isEmpty() ? null : otherParams, v);
  }

  private void draw() {
    // --- Modern Features Section ---
    addCategoryHeader("Modern Features (2026)");

    addDemoHeader("Advanced Markers", "Custom HTML pins, collisions, and 3D builds.");
    drawAdvancedMarkers();

    addDemoHeader("Modern Search", "Official Google 3.65.1c 'No Clear Button' feature.");
    drawNoClearButtonAutocomplete();

    addDemoHeader("Legacy Smart Search", "Standard GWT TextBox with rich landmark results.");
    drawLegacyAutocomplete();

    // --- Core Map Demos Section ---
    addCategoryHeader("Core Map Demos");

    addDemoHeader("Basic Map", "Animations and standard markers.");
    drawBasicMap();

    addDemoHeader("Styled Map", "Cloud-ready custom map styles.");
    drawStyledMap();

    addDemoHeader("Map Controls", "Positioning standard UI elements.");
    drawControlsMap();

    // --- Services & Overlays Section ---
    addCategoryHeader("Services & Overlays");

    addDemoHeader("Directions Service", "Calculating routes and transit.");
    drawDirections();

    addDemoHeader("Drawing Library", "Interactive shapes and tools.");
    drawDrawingMap();

    addDemoHeader("KML Overlays", "Rendering external geospatial data.");
    drawKmlMap();

    addDemoHeader("Street View", "Immersive 360-degree imagery.");
    drawStreetView();
  }

  private void addCategoryHeader(String title) {
    RootPanel.get(mapsContainer).add(new HTML("<h2 class='category-header'>" + title + "</h2>"));
  }

  private void addDemoHeader(String title, String subTitle) {
    RootPanel.get(mapsContainer).add(new HTML("<div class='demo-header-box'>"
        + "<h3 class='demo-title'>" + title + "</h3>"
        + "<p class='demo-subtitle'>" + subTitle + "</p></div>"));
  }


  private void addMapWidget(Widget widget) {
    RootPanel.get(mapsContainer).add(widget);
  }

  // --- Modern Demos ---

  private void drawAdvancedMarkers() {
    addMapWidget(new AdvancedMarkerMapWidget());
  }

  private void drawNoClearButtonAutocomplete() {
    addMapWidget(new NoClearButtonAutocompleteMapWidget());
  }

  private void drawLegacyAutocomplete() {
    addMapWidget(new LegacyAutocompleteMapWidget());
  }

  // --- Core Demos ---

  private void drawBasicMap() {
    addMapWidget(new BasicMapWidget());
  }

  private void drawStyledMap() {
    addMapWidget(new StyledMapWidget());
  }

  private void drawControlsMap() {
    addMapWidget(new ControlsMapWidget());
  }

  // --- Services Demos ---

  private void drawDirections() {
    addMapWidget(new DirectionsServiceMapWidget());
  }

  private void drawDrawingMap() {
    addMapWidget(new DrawingMapWidget());
  }

  private void drawKmlMap() {
    addMapWidget(new KmlMapWidget());
  }

  private void drawStreetView() {
    addMapWidget(new StreetViewMapWidget());
  }
  
}
