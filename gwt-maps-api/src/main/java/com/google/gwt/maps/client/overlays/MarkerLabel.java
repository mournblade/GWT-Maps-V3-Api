package com.google.gwt.maps.client.overlays;

/*
 * #%L
 * GWT Maps API V3 - Core API
 * %%
 * Copyright (C) 2011 - 2022 GWT Maps API V3
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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.mvc.MVCObject;

/**
 * No comments for you.
 * It was hard to write, so it
 * should be hard to read.
 *
 * @author Mathias af Jochnick
 * @Created: 2022-04-07
 */
public class MarkerLabel extends MVCObject<MarkerLabel> {

    protected MarkerLabel() {}

    /**
     * creates This object defines the marker shape to use in determination of a marker's clickable region. The shape
     * consists of two properties ° type and coord ° which define the general type of marker and coordinates specific to
     * that type of marker.
     */
    public final static MarkerLabel newInstance() {
        return JavaScriptObject.createObject().cast();
    }

    public final static MarkerLabel newInstance(String text) {
        MarkerLabel label = JavaScriptObject.createObject().cast();
        label.setText(text);
        return label;
    }
    
    public final native String getText() /*-{
        return this.text;
    }-*/;

    public final native String getClassName() /*-{
        return this.className;
    }-*/;

    public final native String getColor() /*-{
        return this.color;
    }-*/;

    public final native String getFontFamily() /*-{
        return this.fontFamily;
    }-*/;

    public final native String getFontSize() /*-{
        return this.fontSize;
    }-*/;

    public final native String getFontWeight() /*-{
        return this.fontWeight;
    }-*/;

    public final native void setText(String text) /*-{
        this.text = text;
    }-*/;

    public final native void setClassName(String className) /*-{
        this.className = className;
    }-*/;

    public final native void setColor(String color) /*-{
        this.color = color;
    }-*/;

    public final native void setFontFamily(String fontFamily) /*-{
        this.fontFamily = fontFamily;
    }-*/;

    public final native void setFontSize(String fontSize) /*-{
        this.fontSize = fontSize;
    }-*/;

    public final native void setFontWeight(String fontWeight) /*-{
        this.fontWeight = fontWeight;
    }-*/;
}
