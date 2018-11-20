//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.gwt.maps.client.placeslib.placeresult;

/*
 * #%L
 * GWT Maps API V3 - Core API
 * %%
 * Copyright (C) 2011 - 2018 GWT Maps API V3
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

public class PlaceResultPhotos extends JavaScriptObject {
    protected PlaceResultPhotos() {
    }

    public final native int getHeight() /*-{
        return this.height;
    }-*/;

    public final native String getHtmlAttributions() /*-{
        return this.html_attributions;
    }-*/;

    public final native String getUrl(int maxWidth, int maxHeight) /*-{
        return this.getUrl({'maxWidth': maxWidth, 'maxHeight': maxHeight});
    }-*/;

    public final native String getPhotoReference() /*-{
        return this.photo_reference;
    }-*/;

    public final native int getWidth() /*-{
        return this.width;
    }-*/;

    public final native RawReference getRawReference() /*-{
        return this.raw_reference;
    }-*/;
}

