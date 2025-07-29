# Foundation Package

This package contains pure, framework-independent abstractions and contracts
that serve as building blocks for the entire application architecture.

## Purpose

- Provides reusable interfaces and abstractions
- Business logic independent contracts
- Shared across all feature modules
- No dependencies on Android framework or business domain

## Contents

### Domain Foundations

- **UseCase**: Generic contract for business operations
    - Normal UseCase: accepts input and produces output
    - Input only UseCase: requires input, no output
    - Output only UseCase: produces output, no input

### Presentation Foundations

- **MVI Contracts**: Interfaces for Model-View-Intent architecture
    - `StateManager`: State management contract
    - `EffectManager`: One-time effect management contract
    - `IntentProcessor`: Intent handling contract
    - `MviManager`: Composite contract for MVI pattern
- **Delegates**: Default implementations for MVI contracts using Kotlin delegation
    - `DefaultStateManager`: delegate for managing UI state with a StateFlow
    - `DefaultEffectManager`: delegate for managing one-time UI effects with a StateFlow
    - `DefaultIntentProcessor`: delegate for processing user/system intents via a lambda handler
    - `DefaultMviManager`: Composite delegate for MVI, delegating state, intent, and effect
      management

## Usage

Feature modules depend on foundation for contracts:

```kotlin
class GetMoviesUseCase : UseCase<Unit, Flow<List<Movie>>> {
    override suspend fun invoke(input: Unit): Flow<List<Movie>> {
        // Implementation
    }
}
```

## Design Principles

- No business logic
- Framework agnostic
- Minimal and stable contracts
- Promote consistent patterns across features
