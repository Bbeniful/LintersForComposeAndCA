package com.example.simpleapp.data.repository

import com.example.simpleapp.domain.repository.DemoRepository

class DemoRepositoryImpl: DemoRepository {

    override fun getData(): List<String> {
        return listOf("Item 1", "Item 2", "Item 3")
    }
}