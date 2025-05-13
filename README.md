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

## Design Pattern

-

# Screenshotsa

![Genre Screen](https://raw.githubusercontent.com/anugrahsputra/movie-ums/refs/heads/main/screenshots/genre.png)
![Movies Screen](https://raw.githubusercontent.com/anugrahsputra/movie-ums/refs/heads/main/screenshots/movies.png)
![Movies Detail](https://raw.githubusercontent.com/anugrahsputra/movie-uns/refs/heads/main/screenshots/movie_detail.png)
![Movies Detail Trailer](https://raw.githubusercontent.com/anugrahsputra/movie-uns/refs/heads/main/screenshots/movie_detail_trailer.png)
![Movies Detail User Review](https://raw.githubusercontent.com/anugrahsputra/movie-uns/refs/heads/main/screenshots/movie_detail_reviews.png)

