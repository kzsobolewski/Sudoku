package com.kzsobolewski.sudoku.net

import android.util.Log
import com.kzsobolewski.sudoku.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SudokuBoardRepository : SudokuBoardApi {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.BUILD_TYPE == "debug")
            setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val service: SudokuBoardApi = retrofit.create(SudokuBoardApi::class.java)

    override suspend fun getBoard(difficulty: SudokuDifficulty): SudokuApiResponse? {
        return try {
            service.getBoard(difficulty)
        } catch (e: Exception) {
            Log.e(SudokuBoardRepository::class.simpleName, e.localizedMessage, e)
            null
        }
    }
}