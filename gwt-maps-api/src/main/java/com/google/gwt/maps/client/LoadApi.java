package com.google.gwt.maps.client;

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
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Load Maps javascript v3 api
 */
public class LoadApi {

  /**
   * Use the stable quarterly channel.
   */
  public final static String API_VERSION = "quarterly";

  private static int callbackCounter = 0;

  /**
   * private constructor
   */
  private LoadApi() {
  }

  /**
   * Load Maps javascript v3 api with default libraries. these are not loaded {@link LoadLibrary}
   *
   * @param onLoad - callback on success
   */
  public static void go(Runnable onLoad) {
    load(onLoad, null, null, null, null, null);
  }

  /**
   * loads maps api
   *
   * @param onLoad callback on success
   * @param loadLibraries load additional libraries like geometry
   */
  public static void go(Runnable onLoad, ArrayList<LoadLibrary> loadLibraries) {
    load(onLoad, loadLibraries, null, null, null, null);
  }

  /**
   * loads maps api
   *
   * @param onLoad callback on success
   * @param loadLibraries load additional libraries like geometry*
   * @param language choose a language
   * @param otherParams add additional params. like "key=YOUR_API_KEY"
   */
  public static void go(Runnable onLoad, ArrayList<LoadLibrary> loadLibraries, Language language, String otherParams) {
    load(onLoad, loadLibraries, language, null, otherParams, null);
  }

  /**
   * loads maps api
   *
   * @param onLoad callback on success
   * @param loadLibraries load additional libraries like geometry*
   * @param language choose a language
   * @param otherParams add additional params. like "key=YOUR_API_KEY"
   * @param version version to use, like "beta"
   */
  public static void go(Runnable onLoad, ArrayList<LoadLibrary> loadLibraries, Language language, String otherParams,
      String version) {
    load(onLoad, loadLibraries, language, null, otherParams, version);
  }

  /**
   * loads maps api
   *
   * @param onLoad callback on success
   * @param loadLibraries load additional libraries like geometry
   * @param language choose a language
   */
  public static void go(Runnable onLoad, LoadLibrary[] loadLibraries, Language language) {
    ArrayList<LoadLibrary> loadLibrariesList = new ArrayList<LoadLibrary>(Arrays.asList(loadLibraries));
    load(onLoad, loadLibrariesList, language, null, null, null);
  }

  /**
   * loads maps api
   *
   * @param onLoad callback on success
   * @param loadLibraries load additional libraries like geometry
   * @param otherParams add additional params. like "key=YOUR_API_KEY"
   */
  public static void go(Runnable onLoad, ArrayList<LoadLibrary> loadLibraries, String otherParams) {
    load(onLoad, loadLibraries, null, null, otherParams, null);
  }

  private static void load(final Runnable onLoad, ArrayList<LoadLibrary> loadLibraries, Language language,
      String callbackMethod, String otherParams, String version) {

    StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/js?");
    url.append("v=").append(version != null ? version : API_VERSION);

    if (otherParams != null && !otherParams.isEmpty()) {
      url.append("&").append(otherParams);
    }

    if (loadLibraries != null && !loadLibraries.isEmpty()) {
      url.append("&").append(getLibraries(loadLibraries));
    }

    if (language != null) {
      url.append("&language=").append(language.getValue());
    }

    String callbackName = callbackMethod;
    if (callbackName == null) {
      callbackName = nextCallbackName();
      exportCallback(onLoad, callbackName);
    }

    url.append("&callback=").append(callbackName);
    url.append("&loading=async");

    injectScript(url.toString());
  }

  private static String nextCallbackName() {
    return "__gwt_maps_callback_" + callbackCounter++;
  }

  private static native void exportCallback(Runnable onLoad, String callbackName) /*-{
    $wnd[callbackName] = $entry(function() {
      onLoad.@java.lang.Runnable::run()();
      delete $wnd[callbackName];
    });
  }-*/;

  private static void injectScript(String url) {
    Document doc = Document.get();
    ScriptElement script = doc.createScriptElement();
    script.setSrc(url);
    script.setType("text/javascript");
    Element head = doc.getElementsByTagName("head").getItem(0);
    if (head == null) {
      head = doc.getDocumentElement();
    }
    head.appendChild(script);
  }

  /**
   * get the url libraries parameter
   *
   * @param loadLibraries
   */
  private static String getLibraries(ArrayList<LoadLibrary> loadLibraries) {
    if (loadLibraries == null) {
      return "";
    }
    String s = "libraries=";
    Iterator<LoadLibrary> itr = loadLibraries.iterator();
    int i = 0;
    while (itr.hasNext()) {
      LoadLibrary ll = itr.next();
      if (ll != null) {
        if (i > 0) {
          s += ",";
        }
        s += ll.value();
        i++;
      }
    }
    return s;
  }

  /**
   * Libraries not loaded by default <br>
   * <br>
   * See <a href= "https://developers.google.com/maps/documentation/javascript/basics.html#Libraries" >Libraries API
   * Doc</a>
   */
  public static enum LoadLibrary {

    /**
     * Provides a graphical interface for users to draw polygons, rectangles, polylines, circles, and markers on the
     * map. Consult the Drawing Library documentation for more information.
     */
    DRAWING,

    /**
     * Geometry includes utility functions for calculating scalar geometric values (such as distance and area) on the
     * the surface of the earth. Consult the Geometry Library documentation for more information.
     */
    GEOMETRY,

    /**
     * Places enables your application to search for businesses, geographic locations, and points of interest near a
     * given location, or as a user types. Consult the Places Library documentation for more information.
     */
    PLACES,

    /**
     * Visualization contains functionality for adding advanced visualization layers to your Maps API application.
     * Consult the Visualization documentation for more information.
     */
    VISUALIZATION,

    /**
     * Required for Advanced Markers.
     */
    MARKER;

    public static LoadLibrary fromValue(String value) {
      return valueOf(value.toUpperCase());
    }

    public String value() {
      return name().toLowerCase();
    }

    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }


    /**
     * @See <a href="https://developers.google.com/maps/documentation/javascript/basics#Localization">Localization docs</a>
     * @See <a href="https://spreadsheets.google.com/pub?key=p9pdwsai2hDMsLkXsoM05KQ&gid=1">List of languages</a>
     */
    public static enum Language {

        GERMAN("de"),
        GREEK("el"),
        ENGLISH("en"),
        ENGLISH_AUSTRALIAN("en-AU"),
        ENGLISH_GREAT_BRITAIN("en-GB"),
        SPANISH("es"),
        BASQUE("eu"),
        FARSI("fa"),
        FINNISH("fi"),
        FILIPINO("fil"),
        FRENCH("fr"),
        GALICIAN("gl"),
        GUJARATI("gu"),
        HINDI("hi"),
        CROATIAN("hr"),
        HUNGARIAN("hu"),
        INDONESIAN("id"),
        ITALIAN("it"),
        HEBREW("iw"),
        JAPANESE("ja"),
        KANNADA("kn"),
        KOREAN("ko"),
        LITHUANIAN("lt"),
        LATVIAN("lv"),
        MALAYALAM("ml"),
        MARATHI("mr"),
        DUTCH("nl"),
        NORWEGIAN_NYNORSK("nn"),
        NORWEGIAN("no"),
        ORIYA("or"),
        POLISH("pl"),
        PORTUGUESE("pt"),
        PORTUGUESE_BRAZIL("pt-BR"),
        PORTUGUESE_PORTUGAL("pt-PT"),
        ROMANSCH("rm"),
        ROMANIAN("ro"),
        RUSSIAN("ru"),
        SLOVAK("sk"),
        SLOVENIAN("sl"),
        SERBIAN("sr"),
        SWEDISH("sv"),
        TAGALOG("tl"),
        TAMIL("ta"),
        TELUGU("te"),
        THAI("th"),
        TURKISH("tr"),
        UKRAINIAN("uk"),
        VIETNAMESE("vi"),
        CHINESE_SIMPLIFIED("zh-CN"),        CHINESE_TRADITIONAL("zh-TW"),
        DANISH("da");

        private String value;

        Language(String value) {
            this.value = value;
        }

        public static Language fromValue(String value) {
            return valueOf(value.toUpperCase());
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return name().toLowerCase() + "(" + value + ")";
        }
    }
}
