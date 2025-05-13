# TODO
- [x] create screen to show all movies genres
- [x] create screen to show list of movies by genres (infinite scroll)
- [x] create screen to show details of the selected movie
- [x] shows movie reviews (infinite scroll)

# Features
- [x] shows list of movie genres
- [x] shows list of movies by genres
- [x] shows movie details
- [x] shows movie reviews

# Technologies
## Core Framework
- **Android SDK**: Target SDK 35, Minimum SDK 26
- **Kotlin**: Primary development language with JVM target 11

## Build Tools & Plugins
- **Gradle**: Build automation tool
- **KSP (Kotlin Symbol Processing)**: Annotation processing for Kotlin
- **Kotlin Serialization**: JSON serialization/deserialization

## Architecture & UI
- **Jetpack Compose**: Modern declarative UI toolkit
  - Compose Navigation
  - Compose Material Icons
  - Material 3
- **Hilt**: Dependency injection framework
  - Hilt Navigation Compose integration

## Networking & Data
- **Ktor**: HTTP client for API communication
- **Kotlinx Serialization**: JSON processing
- **Coil**: Image loading library for Compose
- **Paging**: Library for handling paginated data sources
  - Paging Compose integration

## Concurrency
- **Kotlin Coroutines**: Asynchronous programming
  - Coroutines Swing extension

## Media
- **YouTube Player**: Integration for video playback

## Configuration
- API integration configured with:
  - BASE_URL and API_KEY environment variables
  - BuildConfig integration for secure access

# Screenshots
<table>
  <tr>
    <td><img src="https://raw.githubusercontent.com/anugrahsputra/movie-uns/main/screenshots/genre.png" width="250"/></td>
    <td><img src="https://raw.githubusercontent.com/anugrahsputra/movie-uns/main/screenshots/movies.png" width="250"/></td>
  </tr>
  <tr>
    <td><img src="https://raw.githubusercontent.com/anugrahsputra/movie-uns/main/screenshots/movie_detail.png" width="250"/></td>
    <td><img src="https://raw.githubusercontent.com/anugrahsputra/movie-uns/main/screenshots/movie_detail_trailer.png" width="250"/></td>
  </tr>
  <tr>
    <td><img src="https://raw.githubusercontent.com/anugrahsputra/movie-uns/main/screenshots/movie_detail_reviews.png" width="250"/></td>
  </tr>
</table>