# Dependency Injection Setup

This directory contains the Hilt dependency injection modules for the TMDB app, organized by architectural layers.

## Module Structure

### Core Modules
- **`NetworkModule`**: Provides core networking dependencies
  - Retrofit instance configured for TMDB API
  - OkHttpClient with logging and API key interceptors
  - Moshi for JSON serialization
  - HTTP timeout and logging configuration

### Feature Modules
- **`MovieListModule`**: Main entry point for movie list feature
  - Includes data and domain modules
  - Coordinates all movie list dependencies

### Data Layer
- **`DataModule`**: Provides data layer implementations
  - Binds `DefaultMovieRepository` to `MovieRepository`
  - Binds `MovieRetrofitRemoteDataSource` to `MovieRemoteDataSource`
  - Provides `TmdbService` from Retrofit

### Domain Layer
- **`DomainModule`**: Placeholder for domain dependencies
  - Currently empty as use cases use `@Inject` constructors
  - Ready for future domain-specific bindings

## Dependency Graph

```
NetworkModule
├── Retrofit
├── OkHttpClient (with interceptors)
├── Moshi
└── ApiKeyInterceptor

MovieListModule
├── DataModule
│   ├── TmdbService
│   ├── MovieRemoteDataSource → MovieRetrofitRemoteDataSource
│   └── MovieRepository → DefaultMovieRepository
└── DomainModule
    └── GetMoviesUseCase (via @Inject constructor)
```

## Usage

All dependencies are automatically injectable via `@Inject` constructors:

```kotlin
class SomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    // Use case is automatically injected
}
```

## Configuration Notes

1. **API Key**: Update `ApiKeyInterceptor.API_KEY` with actual TMDB API key
2. **Base URL**: Configured in `ApiConstants.TMDB_BASE_URL_V3`
3. **Timeouts**: Set to 30 seconds for connect/read/write
4. **Logging**: Full body logging enabled (disable in production)

## Testing

- Use `@HiltAndroidTest` for instrumentation tests
- Mock modules can be created for testing isolated components
- See `DependencyInjectionTest` for basic structure verification
