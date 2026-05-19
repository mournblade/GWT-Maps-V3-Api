# Google Maps Modernization Guide (2026)

This document explains the core differences between the classic Maps API and the modern Vector engine enabled by this library upgrade.

## 1. Raster vs. Vector: "Where is the map built?"

The fundamental change in modern Google Maps is the shift in rendering responsibility.

### Raster Rendering (The Classic Way)
- **Built on:** Google's Servers.
- **How it works:** Google generates static image tiles (`.png`) and sends them to the browser.
- **Performance:** Slower zooming; map becomes pixelated during transitions.
- **Styling:** Supports local JSON styles (`setMapTypeStyles`) because Google's server can "paint" the styles onto the images before sending them.
- **3D:** No true 3D building data or rotation.

### Vector Rendering (The Modern Way)
- **Built on:** Your Browser (using WebGL/Graphics Card).
- **How it works:** Google sends raw data (coordinates of streets, buildings, etc.) and your computer "draws" the map live.
- **Performance:** Ultra-smooth zooming, horizontal labels during rotation, and crisp rendering on high-DPI screens.
- **Styling:** **Does NOT support local JSON styles.** For performance, styles must be pre-configured in the Google Cloud Console.
- **3D:** Supports 3D buildings and camera tilt/rotation.

---

## 2. The Map ID System

A **Map ID** is a unique identifier from your Google Cloud Console that links your API Key to specific modern features.

- **Required for:**
  - `AdvancedMarkerElement` (Modern Pins and HTML Markers).
  - Vector rendering engine.
  - Cloud-based map styling.
- **Project Scope:** A Map ID is strictly tied to the project that owns the API Key. Using a Map ID from a different project will trigger an `ApiProjectMapError`.
- **Placeholder:** `DEMO_MAP_ID` is a test ID provided by Google, but it only works reliably with Google's public demo keys. For private projects, you should create your own ID.

---

## 3. Styling Logic in 2026

If you provide a **Map ID**, you must change how you handle "Clean" or "Custom" maps.

### Why does local JSON styling break in Vector mode?
It boils down to **where the map is built**:
- **Raster:** Google's server draws an image for you. It can easily "paint" your custom JSON styles onto that image before sending it.
- **Vector:** Google sends raw data to your browser, and your graphics card draws it live. To make this performant, Google **pre-compiles** the style bundle associated with your Map ID. 
- **The Restriction:** Because styles are pre-compiled and optimized on Google's servers for speed, you **cannot** inject dynamic JSON styles at runtime in Vector mode.

### How to Style:
- **The Old Way (JSON):** 
  ```java
  // Works in RASTER mode, but IGNORED in VECTOR mode
  options.setMapTypeStyles(new MapTypeStyle[]{...});
  ```
- **The Modern Way (Cloud Tooling):**
  1. Go to **Google Cloud Console > Google Maps Platform > Map Styles**.
  2. Create a style (e.g., "Hide Points of Interest").
  3. Link that style to your **Map ID**.
  4. Your Java app will automatically show the styled map without any code changes.

---

## 4. Forcing Raster Mode

If you want the benefits of a Map ID (like Advanced Markers) but prefer the classic look or want to avoid WebGL browser warnings, you can force the Raster engine:

```java
MapOptions options = MapOptions.newInstance();
options.setMapId("YOUR_MAP_ID");
options.setRenderingType(RenderingType.RASTER); // Disables WebGL engine
```

## 5. Modern Markers (AdvancedMarkerElement)

The new marker system uses Web Components.

- **Why "Advanced"?** It supports custom HTML content (GWT Widgets), collision detection, and 3D altitude.
- **Pinned Styling:** Use `PinElement` to change colors, glyphs, and borders without needing custom `.png` files.
- **Compatibility:** The old `Marker` class still works, but it will continue to show a deprecation warning in the console. Transition to `AdvancedMarkerElement` whenever custom UI is needed.
