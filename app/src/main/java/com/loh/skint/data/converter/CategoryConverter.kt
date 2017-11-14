package com.loh.skint.data.converter

import com.loh.skint.domain.model.Category
import com.loh.skint.domain.model.findCategoryById
import io.requery.Converter

class CategoryConverter : Converter<Category, Int> {

    override fun getMappedType(): Class<Category> = Category::class.java

    override fun getPersistedType(): Class<Int> = Int::class.java

    override fun getPersistedSize(): Int? {
        return null
    }

    override fun convertToPersisted(value: Category?): Int? {
        return value?.id
    }

    override fun convertToMapped(type: Class<out Category>?, value: Int?): Category? {
        return value?.let { findCategoryById(value) }
    }
}