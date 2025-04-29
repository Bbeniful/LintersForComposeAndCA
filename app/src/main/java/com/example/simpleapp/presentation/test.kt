package com.example.simpleapp.presentation

import java.io.File

interface FileProvider {
    fun getFileContent(path: String): String
}

class FileProviderImpl : FileProvider {

    override fun getFileContent(path: String): String {
        return File(path).readText()
    }
}

interface FileDataSource {
    fun getFileContent(path: String): String
}

class FileDataSourceImpl(private val fileProvider: FileProvider) : FileDataSource {

    override fun getFileContent(path: String): String {
        return fileProvider.getFileContent(path)
    }
}



