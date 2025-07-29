# Test Utilities Package

This package contains shared test utilities and data factories used across all test classes.

## TestMovieFactory

Factory for creating test Movie and RemoteMovie instances with consistent default values.

### Functions:

- `createMovie()` - Creates domain Movie objects
- `createRemoteMovie()` - Creates RemoteMovie objects
- `createMovieList()` - Creates lists of Movie objects
- `createRemoteMovieList()` - Creates lists of RemoteMovie objects

### Usage:

```kotlin
// Single movie with defaults
val movie = TestMovieFactory.createMovie()

// Custom movie
val movie = TestMovieFactory.createMovie(
	id = "123",
	title = "Custom Title"
)

// List of movies
val movies = TestMovieFactory.createMovieList(count = 5, startId = 1)
```

## TestDataFactory

Factory for creating common test data structures like responses and collections.

### Functions:

- `createPaginatedResponse()` - Creates PaginatedResponse objects
- `createMoviePagingData()` - Creates PagingData objects
- `createEmptyPaginatedResponse()` - Creates empty responses

### Usage:

```kotlin
// Paginated response with test data
val response = TestDataFactory.createPaginatedResponse(
	results = TestMovieFactory.createRemoteMovieList(),
	page = 1,
	totalPages = 5
)

// PagingData for testing
val pagingData = TestDataFactory.createMoviePagingData(movies)
```

## Benefits

- **Consistency**: All tests use the same baseline test data
- **Maintainability**: Changes to test data structure only need to be made in one place
- **Readability**: Tests focus on behavior rather than data setup
- **Reusability**: Common patterns available across all test classes
