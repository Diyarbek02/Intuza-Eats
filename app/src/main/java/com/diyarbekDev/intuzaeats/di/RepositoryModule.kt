package com.diyarbekDev.intuzaeats.di

import com.diyarbekDev.intuzaeats.domain.repository.MainRepository
import com.diyarbekDev.intuzaeats.domain.repository.impl.MainRepsoitoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindMainRepository(impl: MainRepsoitoryImpl): MainRepository
}