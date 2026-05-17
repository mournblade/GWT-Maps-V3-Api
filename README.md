# GWT Maps API V3 (Modernized 2026)

A modernized GWT wrapper for the Google Maps JavaScript API. This fork has been updated to support modern loading patterns, Advanced Markers, and WebGL/Raster rendering control.

## Modern Features
- **Modern Loader:** Replaced deprecated `jsapi` with direct script injection.
- **Async Loading:** Uses `loading=async` for better performance.
- **Advanced Markers:** Full support for `AdvancedMarkerElement` and `PinElement`.
- **Rendering Control:** Ability to force `RASTER` or `VECTOR` rendering engines.
- **Cleanup:** Removed deprecated Weather and Panoramio libraries.

## Basic Usage

### 1. Initialize the API
In your `MapLoader` or `EntryPoint`, load the API using the modern `LoadApi.go` method.

```java
ArrayList<LoadLibrary> loadLibraries = new ArrayList<>();
loadLibraries.add(LoadLibrary.GEOMETRY);
loadLibraries.add(LoadLibrary.PLACES);
loadLibraries.add(LoadLibrary.MARKER); // Required for Advanced Markers

LoadApi.go(() -> {
    // Callback when API is ready
    initApp();
}, loadLibraries, Language.ENGLISH, "key=YOUR_API_KEY");
```

### 2. Configure Map Options
Advanced features work best with a **Map ID**. You can also force the **Raster** engine to avoid WebGL noise.

```java
MapOptions options = MapOptions.newInstance();
options.setMapId("YOUR_MAP_ID"); // Required for Advanced Markers
options.setRenderingType(RenderingType.RASTER); // Force classic Raster engine

MapWidget map = new MapWidget(options);
```

### 3. Using Advanced Markers
The modern replacement for `google.maps.Marker`.

```java
AdvancedMarkerElementOptions opts = AdvancedMarkerElementOptions.newInstance();
opts.setPosition(LatLng.newInstance(47.6, -122.3));
opts.setTitle("Modern Marker");
opts.setMap(mapWidget);

// Customizing the Pin appearance
PinElementOptions pinOpts = PinElementOptions.newInstance();
pinOpts.setBackground("#FBBC04");
pinOpts.setGlyphText("N");
PinElement pin = PinElement.newInstance(pinOpts);

opts.setContent(pin); // Assign the customized pin

AdvancedMarkerElement marker = AdvancedMarkerElement.newInstance(opts);
```

## Compilation
Requires Maven and GWT 2.10.0+.

```bash
mvn clean install -DskipTests
```

## Testing the Showcase
Run the showcase in your browser and pass credentials via URL parameters:
`http://localhost:8080/showcase.html?key=YOUR_KEY&mapId=YOUR_ID`
