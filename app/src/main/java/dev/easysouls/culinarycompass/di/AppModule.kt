package dev.easysouls.culinarycompass.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.easysouls.culinarycompass.data.beers.local.BeerDatabase
import dev.easysouls.culinarycompass.data.beers.local.BeerEntity
import dev.easysouls.culinarycompass.data.beers.remote.BeerApi
import dev.easysouls.culinarycompass.data.beers.remote.BeerRemoteMediator
import dev.easysouls.culinarycompass.data.freemealdb.remote.FreeMealApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context): BeerDatabase {
        return Room.databaseBuilder(
            context,
            BeerDatabase::class.java,
            "beers.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBeerApi(): BeerApi {
        return Retrofit.Builder()
            .baseUrl(BeerApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BeerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBeerPager(
        beerDatabase: BeerDatabase, beerApi: BeerApi
    ): Pager<Int, BeerEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                beerDb = beerDatabase,
                beerApi = beerApi
            ),
            pagingSourceFactory = {
                beerDatabase.dao.pagingSource()
            }
        )
    }

    @Provides
    @Singleton
    fun provideFreeMealRetrofitInstance(): FreeMealApi {
        return Retrofit.Builder()
            .baseUrl(FreeMealApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(FreeMealApi::class.java)
    }
}