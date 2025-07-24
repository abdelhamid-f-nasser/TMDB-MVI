package com.movie.tmdb.foundation.domain.usecase


/**
 * Base interface for all Use Cases in the domain layer with both [Input] and [Output].
 *
 * Represents a single business operation that can be executed.
 * Use Cases encapsulate business logic and coordinate between repositories.
 *
 *
 * Type Parameters:
 * @param Input The input parameters for the use case
 * @param Output The output result of the use case
 *
 * Make sure to follow the below principles:
 * - Each use case handles one business operation
 * - Depends on repository abstractions, not implementations
 * - Different use case types for different execution patterns
 *
 * Note: for non complex business logic, it'll act as a proxy however; making it a standard.
 */
interface UseCase<in Input, out Output> {

	/**
	 * Executes the use case with the given input.
	 *
	 * @param input The input parameters for the use case
	 * @return The result of the use case execution
	 */
	suspend operator fun invoke(input: Input): Output
}


