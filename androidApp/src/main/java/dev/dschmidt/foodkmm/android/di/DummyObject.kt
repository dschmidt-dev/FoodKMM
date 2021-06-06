package dev.dschmidt.foodkmm.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


interface Dummy {
    fun description(): String
}

class DummyImpl(private val dummyCapacity: Int): Dummy {
    override fun description(): String {
        return "This o is $dummyCapacity dummies"
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DummyObject {

    @Singleton
    @Provides
    fun provideDummy(): Int {
        return 5
    }

    @Singleton
    @Provides
    fun provideDummyObject(dummyCapacity: Int): Dummy {
        return DummyImpl(dummyCapacity = dummyCapacity)
    }

}