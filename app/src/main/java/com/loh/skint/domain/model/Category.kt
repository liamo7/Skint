package com.loh.skint.domain.model

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.loh.skint.R
import com.loh.skint.data.entity.TransferType

data class Category(
        val id: Int,
        val transferType: TransferType,
        @StringRes val nameRes: Int,
        @DrawableRes val iconRes: Int,
        @ColorRes val colorRes: Int
) {
    companion object {
        fun getCategories() = listOf(
                Category(0, TransferType.INCOME, R.string.category_other, R.drawable.ic_other, R.color.category_other),
                Category(1, TransferType.INCOME, R.string.category_salary, R.drawable.ic_salary, R.color.category_salary),
                Category(2, TransferType.INCOME, R.string.category_loan, R.drawable.ic_loan, R.color.category_loan),
                Category(3, TransferType.INCOME, R.string.category_gifts, R.drawable.ic_gift, R.color.category_gifts),

                Category(4, TransferType.EXPENSE, R.string.category_car, R.drawable.ic_car, R.color.category_car),
                Category(5, TransferType.EXPENSE, R.string.category_travel, R.drawable.ic_travel, R.color.category_travel),
                Category(6, TransferType.EXPENSE, R.string.category_food_drink, R.drawable.ic_food_drink, R.color.category_food_drink),
                Category(7, TransferType.EXPENSE, R.string.category_personal, R.drawable.ic_personal, R.color.category_personal),
                Category(8, TransferType.EXPENSE, R.string.category_bills, R.drawable.ic_bills, R.color.category_bills),
                Category(9, TransferType.EXPENSE, R.string.category_entertainment, R.drawable.ic_entertainment, R.color.category_entertainment),
                Category(10, TransferType.EXPENSE, R.string.category_home, R.drawable.ic_home, R.color.category_home),
                Category(11, TransferType.EXPENSE, R.string.category_utilities, R.drawable.ic_utilities, R.color.category_utilities),
                Category(12, TransferType.EXPENSE, R.string.category_shopping, R.drawable.ic_shopping, R.color.category_shopping),
                Category(13, TransferType.EXPENSE, R.string.category_accommodation, R.drawable.ic_accomodation, R.color.category_accommodation),
                Category(14, TransferType.EXPENSE, R.string.category_health, R.drawable.ic_health, R.color.category_health),
                Category(15, TransferType.EXPENSE, R.string.category_clothing, R.drawable.ic_clothing, R.color.category_clothing),
                Category(16, TransferType.EXPENSE, R.string.category_transport, R.drawable.ic_transport, R.color.category_transport),
                Category(17, TransferType.EXPENSE, R.string.category_groceries, R.drawable.ic_groceries, R.color.category_groceries),
                Category(18, TransferType.EXPENSE, R.string.category_drinks, R.drawable.ic_drinks, R.color.category_drinks),
                Category(20, TransferType.EXPENSE, R.string.category_hobbies, R.drawable.ic_hobbies, R.color.category_hobbies),
                Category(20, TransferType.EXPENSE, R.string.category_pets, R.drawable.ic_pets, R.color.category_pets),
                Category(21, TransferType.EXPENSE, R.string.category_education, R.drawable.ic_education, R.color.category_education),
                Category(22, TransferType.EXPENSE, R.string.category_cinema, R.drawable.ic_cinema, R.color.category_cinema),
                Category(23, TransferType.EXPENSE, R.string.category_kids, R.drawable.ic_kids, R.color.category_kids),
                Category(24, TransferType.EXPENSE, R.string.category_rent, R.drawable.ic_rent, R.color.category_rent),
                Category(25, TransferType.EXPENSE, R.string.category_mortgage, R.drawable.ic_mortgage, R.color.category_mortgage),
                Category(26, TransferType.EXPENSE, R.string.category_phone, R.drawable.ic_phone, R.color.category_phone),
                Category(27, TransferType.EXPENSE, R.string.category_savings, R.drawable.ic_savings, R.color.category_savings)
        )

        val UNINITALISED_CATEGORY = Category(-1, TransferType.INCOME, -1, -1, -1)

        fun findCategoryById(id: Int): Category {
            return getCategories().first { it.id == id }
        }

        fun findCategoryByDrawableRes(@DrawableRes id: Int): Category {
            return getCategories().first { it.iconRes == id }
        }
    }
}