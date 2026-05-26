package com.google.gwt.maps.client.placeslib;

/*
 * #%L
 * GWT Maps API V3 - Core API
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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.controls.ControlPosition;
import com.google.gwt.maps.client.mvc.MVCArray;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Convenience map control for the Places API PlaceAutocompleteElement web component.
 * <p>
 * The Google Places autocomplete widget is a self-contained web component. It works well as
 * a Google Maps control, but some host layouts hide panes by changing outer layout state
 * rather than by removing the map control DOM node. In that case, internal autocomplete UI
 * such as the clear button can remain visible even though the surrounding pane is hidden.
 * This wrapper gives callers an explicit attach/detach lifecycle for the autocomplete
 * control without destroying or recreating the map itself.
 */
public class PlaceAutocompleteMapControl extends Composite {

  private final MapWidget mapWidget;
  private final ControlPosition controlPosition;
  private final FlowPanel container;
  private final PlaceAutocompleteElement placeAutocomplete;

  private boolean attached;

  public PlaceAutocompleteMapControl(MapWidget mapWidget, ControlPosition controlPosition) {
    this.mapWidget = mapWidget;
    this.controlPosition = controlPosition;
    this.container = new FlowPanel();
    this.placeAutocomplete = new PlaceAutocompleteElement();
    placeAutocomplete.setNoClearButton(true);

    initWidget(container);
    setupContainer();
    placeAutocomplete.setWidth("100%");

    // The web component follows the page/browser color scheme by default. Most existing
    // GWT map controls in this library are light themed, so keep this control light unless
    // a caller explicitly overrides it.
    setColorScheme("light");
    container.add(placeAutocomplete);
  }

  public PlaceAutocompleteElement getPlaceAutocomplete() {
    return placeAutocomplete;
  }

  public void setPlaceholder(String placeholder) {
    placeAutocomplete.setPlaceholder(placeholder);
  }

  public void setRequestedFields(String... fields) {
    placeAutocomplete.setRequestedFields(fields);
  }

  public void setTypes(AutocompleteType... types) {
    placeAutocomplete.setTypes(types);
  }

  public void setTypes(String... types) {
    placeAutocomplete.setTypes(types);
  }

  public void setLocationBias(LatLngBounds bounds) {
    placeAutocomplete.setLocationBias(bounds);
  }

  public void setLocationRestriction(LatLngBounds bounds) {
    placeAutocomplete.setLocationRestriction(bounds);
  }

  /**
   * Sets whether the clear button is removed from the search box.
   * <br>Note: Requires Google Maps version 3.65.1c or later.
   */
  public void setNoClearButton(boolean noClear) {
    placeAutocomplete.setNoClearButton(noClear);
  }

  public void setWidth(String width) {
    container.setWidth(width);
  }

  public void setMaxWidth(String maxWidth) {
    container.getElement().getStyle().setProperty("maxWidth", maxWidth);
  }

  public void setColorScheme(String colorScheme) {
    if (colorScheme == null) {
      placeAutocomplete.getElement().getStyle().clearProperty("colorScheme");
      return;
    }
    placeAutocomplete.getElement().getStyle().setProperty("colorScheme", colorScheme);
  }

  public void focus() {
    placeAutocomplete.focus();
  }

  public void clear() {
    placeAutocomplete.setValue("");
    placeAutocomplete.blur();
  }

  public boolean isAttachedToMap() {
    return attached;
  }

  public void attach() {
    if (attached) {
      return;
    }

    // Add only this wrapper's container to the map controls array. We intentionally do not
    // call MapWidget#setControls(Widget) here because that helper removes the previous
    // control at the same position; callers may have other controls in the same slot.
    mapWidget.getControls(controlPosition).push(container.getElement());
    attached = true;
  }

  public void detach() {
    if (!attached) {
      return;
    }

    // Clear before removing the control. This lets the Google component discard its active
    // input state and avoids leaving internal UI, such as the clear button, behind in host
    // layouts that hide/show map panes.
    clear();
    MVCArray<Element> controls = mapWidget.getControls(controlPosition);

    // Remove just our element from the controls array. Do not clear the whole position,
    // because that would remove unrelated controls owned by the map or application.
    for (int i = 0; i < controls.getLength(); i++) {
      if (controls.get(i) == container.getElement()) {
        controls.removeAt(i);
        break;
      }
    }
    attached = false;
  }

  public com.google.gwt.event.shared.HandlerRegistration addPlaceSelectHandler(PlaceSelectHandler handler) {
    return placeAutocomplete.addPlaceSelectHandler(handler);
  }

  private void setupContainer() {
    container.getElement().getStyle().setMargin(8, com.google.gwt.dom.client.Style.Unit.PX);
    container.setWidth("420px");
    setMaxWidth("calc(100vw - 48px)");

    // The Google web component renders its own input and popup. The wrapper only controls
    // the map-control box around it: stable sizing, stacking, and attach/detach lifecycle.
    container.getElement().getStyle().setPosition(Position.RELATIVE);
    container.getElement().getStyle().setZIndex(1000000);
    preventMapHitsFrom(container.getElement());
  }

  private static native void preventMapHitsFrom(Element element) /*-{
    // OverlayView.preventMapHitsFrom is the documented Maps API hook, but it is not
    // enough for PlaceAutocompleteElement in some host layouts: the map can still
    // receive pointer/key events before the web component gets a usable focus state.
    // Stop bubbling at the wrapper as a fallback so click/focus/type stays inside
    // the autocomplete control.
    var stop = function(event) {
      event.stopPropagation();
    };
    element.addEventListener("click", stop);
    element.addEventListener("mousedown", stop);
    element.addEventListener("mouseup", stop);
    element.addEventListener("pointerdown", stop);
    element.addEventListener("pointerup", stop);
    element.addEventListener("touchstart", stop);
    element.addEventListener("touchend", stop);
    element.addEventListener("keydown", stop);
    element.addEventListener("keyup", stop);
    element.addEventListener("keypress", stop);

    if ($wnd.google && $wnd.google.maps && $wnd.google.maps.OverlayView
        && $wnd.google.maps.OverlayView.preventMapHitsFrom) {
      $wnd.google.maps.OverlayView.preventMapHitsFrom(element);
    }
  }-*/;
}
