package com.droal.marvel.dependencyInjection

import com.droal.marvel.datasource.database.DatabaseSourceImpl
import com.droal.marvel.datasource.database.IDatabaseSource
import com.droal.marvel.datasource.network.IMarvelAPI
import com.droal.marvel.datasource.network.MarvelAPIImpl
import com.droal.marvel.datasource.repository.CharacterRepository
import com.droal.marvel.interactors.GetAllCharacters
import droal.shareddb.cache.DatabaseDriverFactory
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.kodein.di.*
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val KodeinInjector = DI{

    bind<CoroutineContext>() with provider { Dispatchers.Main }


    val httpClient = HttpClient{
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }
    }

    /**
     * NETWORK API
     */



    /**
     * NETWORK DATA SOURCE
     */
    bind<IMarvelAPI>() with provider { MarvelAPIImpl(httpClient)}

    /**
     * Disk Data Source
     */
   // bind<IDatabaseSource>() with provider { DatabaseSourceImpl(DatabaseDriverFactory()) }


    /**
     * REPOSITORIES
     */
    bind<CharacterRepository>() with provider { CharacterRepository(instance()) }


    /**
     * USECASES
     */
    bind<GetAllCharacters>() with singleton { GetAllCharacters(instance()) }
}