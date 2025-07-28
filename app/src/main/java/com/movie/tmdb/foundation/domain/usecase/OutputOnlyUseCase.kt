package com.movie.tmdb.foundation.domain.usecase


/**
 * Base interface for use cases that only **produce output** and does not require inputs.
 *
 * Represents a single business operation that can be executed.
 * Use Cases encapsulate business logic and coordinate between repositories.
 *
 *
 * Type Parameters:
 * @param Output The output result of the use case
 *
 * @see [UseCase]
 * @see [InputOnlyUseCase]
 *
 * Note: this allows us to avoid [Unit] boilerplate code.
 */
interface OutputOnlyUseCase<out Output> {

	/**
	 * Executes the use case with the given input.
	 *
	 * @return The result of the use case execution
	 */
	operator fun invoke(): Output
}
