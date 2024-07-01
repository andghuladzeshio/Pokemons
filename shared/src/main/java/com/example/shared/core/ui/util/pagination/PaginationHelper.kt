package com.example.shared.core.ui.util.pagination

interface PaginationHelper<T> {
    var pageSize: Int

    fun getOffset(): Int

    fun addPage(items: List<T>)

    fun getItems(): List<T>

    fun clearItems()
}