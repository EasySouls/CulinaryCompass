package dev.easysouls.culinarycompass.data.beers

import dev.easysouls.culinarycompass.data.beers.local.BeerEntity
import dev.easysouls.culinarycompass.data.beers.remote.BeerDto
import dev.easysouls.culinarycompass.domain.beers.Beer

fun BeerDto.toBeerEntity(): BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}