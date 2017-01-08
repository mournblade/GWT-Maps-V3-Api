package com.google.gwt.maps.client.events;

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

import com.google.gwt.ajaxloader.client.Properties;
import com.google.gwt.ajaxloader.client.Properties.TypeException;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.events.MouseEvent;

import java.lang.String;

/**
 * This object is returned from various mouse events on the map and overlays, and contains all the fields shown below. <br>
 * <br>
 * See <a href= "https://developers.google.com/maps/documentation/javascript/reference#IconMouseEvent" >IconMouseEvent API
 * Doc</a>
 */
public class IconMouseEvent extends MouseEvent {

    /**
     * {@link placeId}
     */
    private String placeId;

    /**
     * Create a new IconMouseEvent from properties of the map event call back
     *
     * @param properties {@link com.google.gwt.ajaxloader.client.Properties}
     */
    public IconMouseEvent(Properties properties) {
        super(properties);
        parseProperties(properties);
    }

    /**
     * Parse the properties for {@link String}
     *
     * @param properties
     */
    private void parseProperties(Properties properties) {
        try {
            placeId = properties.getString("placeId");
        } catch (TypeException e) {
            e.printStackTrace();
        }
    }

    /**
     * The latitude/longitude that was below the cursor when the event occurred.
     *
     * @return point of mouse event
     */
    public final String getPlaceId() {
        return placeId;
    }
}
