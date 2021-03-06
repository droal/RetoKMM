package com.droal.marvel.characters.datasource.database.model

import com.droal.marvel.characters.domain.Character
import droal.shareddb.SelectAllCharacters

fun SelectAllCharacters.toCharacter(): Character{
    return Character(
        id = id.toInt(),
        name = name,
        description = description,
        modified = modified,
        resourceURI = resourceURI,
        thumbnailPath = path,
        urls = "",
        comics = "",
        stories = "",
        events = "",
        series = ""
    )
}
