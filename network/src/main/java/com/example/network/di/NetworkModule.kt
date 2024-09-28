package com.example.network.di

import com.example.network.data.DrugsAPI
import com.example.network.data.DrugsRemoteDataSource
import com.example.network.data.DrugsRemoteDataSourceImpl
import com.example.network.data.DrugsRemoteRepositoryImpl
import com.example.network.domain.DrugsRemoteRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://shans.d2.i-partner.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun providesDrugsAPI(retrofit: Retrofit): DrugsAPI {
        return retrofit.create(DrugsAPI::class.java)
    }
    @Provides
       fun providesDrugsRemoteDataSource(drugsRemoteDataSourceImpl: DrugsRemoteDataSourceImpl): DrugsRemoteDataSource {
           return drugsRemoteDataSourceImpl
       }
       @Provides
       fun providesDrugsRemoteRepository(drugsRemoteRepositoryImpl: DrugsRemoteRepositoryImpl): DrugsRemoteRepository {
           return drugsRemoteRepositoryImpl
       }
}

