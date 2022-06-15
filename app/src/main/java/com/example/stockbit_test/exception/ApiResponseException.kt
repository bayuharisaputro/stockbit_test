package com.example.stockbit_test.exception

class ApiResponseException(val errorCode: String?, val errorMessage: String?) : Exception()