package com.movie.tmdb.foundation.domain.usecase


/**
 * Base interface for use cases that only **accepts input** and does not produce output.
 *
 * Represents a single business operation that can be executed.
 * Use Cases encapsulate business logic and coordinate between repositories.
 *
 *
 * Type Parameters:
 * @param Input The input parameters for the use case
 *
 * @see [UseCase]
 * @see [OutputOnlyUseCase]
 *
 * Note: this allows us to avoid [Unit] boilerplate code.
 */
interface InputOnlyUseCase<in Input> {

	/**
	 * Executes the use case with the given input.
	 *
	 * @return The result of the use case execution
	 */
	operator fun invoke(input: Input)
}
