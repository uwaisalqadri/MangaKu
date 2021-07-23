package com.uwaisalqadri.mangaapp.utils

/**
 * Created by Uwais Alqadri on July 22, 2021
 */
sealed class Resource<out R> {
	data class Success<out T>(val data: T) : Resource<T>()
	data class Error<T>(val errorMessage: String, val data: T? = null) : Resource<T>()
	object Empty : Resource<Nothing>()
	object Loading : Resource<Nothing>()
}





