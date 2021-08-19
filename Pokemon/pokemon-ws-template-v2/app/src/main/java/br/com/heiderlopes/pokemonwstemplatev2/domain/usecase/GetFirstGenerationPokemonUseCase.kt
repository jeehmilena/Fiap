package br.com.heiderlopes.pokemonwstemplatev2.domain.usecase

import br.com.heiderlopes.pokemonwstemplatev2.domain.repository.PokemonRepository

class GetFirstGenerationPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke() = pokemonRepository.getPokemon(
        size = 150, sort = "number,asc"
    )
}