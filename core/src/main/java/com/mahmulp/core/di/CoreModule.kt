package com.mahmulp.core.di

import androidx.room.Room
import com.mahmulp.core.BuildConfig
import com.mahmulp.core.data.StoryRepository
import com.mahmulp.core.data.source.local.LocalDataSource
import com.mahmulp.core.data.source.local.room.StoryDatabase
import com.mahmulp.core.data.source.remote.RemoteDataSource
import com.mahmulp.core.data.source.remote.network.ApiService
import com.mahmulp.core.domain.repository.IStoryRepository
import com.mahmulp.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<StoryDatabase>().storyDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("mahmulp".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            StoryDatabase::class.java, "Story.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "story-api.dicoding.dev"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/qCP4eoKVje0xZdOY+LDsYOVqaH8xPXVKNMjT8EWo1vM=")
            .add(hostname, "sha256/bdrBhpj38ffhxpubzkINl0rG+UyossdhcBYj+Zx2fcc=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val baseUrl = BuildConfig.BASE_URL
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get(), get()) }
    factory { AppExecutors() }
    single<IStoryRepository> {
        StoryRepository(
            get(),
            get(),
            get()
        )
    }
}