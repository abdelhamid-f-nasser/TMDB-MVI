/**
 * Foundation Package
 *
 * This package contains pure, framework-independent abstractions and contracts
 * that serve as building blocks for the entire application architecture.
 *
 * ## Purpose
 * - Provides reusable interfaces and abstractions
 * - Business logic independent contracts
 * - Shared across all feature modules
 * - No dependencies on Android framework or business domain
 *
 * ## Contents
 * **Domain Foundations**
 * - UseCase: Generic contract for business operations
 *  - Normal UseCase [UseCase]: accepts input and produces output
 *  - Input only UseCase [InputOnlyUseCase]
 *  - Output only UseCase [OutputOnlyUseCase]
 *
 * **Presentation Foundations**
 * - [BaseViewModel]: Base class for ViewModels
 * - [EffectManager]: Base class for managing side effects
 * - [UiEffect]: Base class for UI effects
 * - [UiIntent]: Base class for UI Intents
 * - [UiState]: Base class for UI state
 *
 *
 * ## Usage
 * Feature modules depend on foundation for contracts:
 * ```kotlin
 * class GetMoviesUseCase : UseCase<Unit, Flow<List<Movie>>> {
 *     override suspend fun invoke(input: Unit): Flow<List<Movie>> {
 *         // Implementation
 *     }
 * }
 * ```
 *
 * ## Design Principles
 * - No business logic
 * - Framework agnostic
 * - Minimal and stable contracts
 * - Promote consistent patterns across features
 */


@file:JvmName("FoundationPackage")

package com.movie.tmdb.foundation

import com.movie.tmdb.foundation.domain.usecase.*
import com.movie.tmdb.foundation.presentation.ui.*
