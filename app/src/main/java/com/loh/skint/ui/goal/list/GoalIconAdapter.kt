package com.loh.skint.ui.goal.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loh.skint.R
import com.loh.skint.util.inflate
import com.loh.skint.util.tint
import kotlinx.android.synthetic.main.item_goal_icon.view.*

class GoalIconAdapter constructor(private val callback: (Int) -> Unit = {}) : RecyclerView.Adapter<GoalIconAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.item_goal_icon))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            setOnClickListener { callback(position) }
            item_icon.setImageResource(icons[position])
            item_icon.tint(R.color.black)
        }
    }

    override fun getItemCount(): Int = icons.size

    companion object {
        val icons = listOf(
                R.drawable.ic_accomodation,
                R.drawable.ic_bills,
                R.drawable.ic_budget,
                R.drawable.ic_bullseye,
                R.drawable.ic_car,
                R.drawable.ic_cinema,
                R.drawable.ic_clothing,
                R.drawable.ic_coin_hand,
                R.drawable.ic_credit_card,
                R.drawable.ic_dashboard,
                R.drawable.ic_dollar,
                R.drawable.ic_drinks,
                R.drawable.ic_education,
                R.drawable.ic_entertainment,
                R.drawable.ic_food_drink,
                R.drawable.ic_gift,
                R.drawable.ic_groceries,
                R.drawable.ic_health,
                R.drawable.ic_hobbies,
                R.drawable.ic_home,
                R.drawable.ic_kids,
                R.drawable.ic_loan,
                R.drawable.ic_location,
                R.drawable.ic_money_bag,
                R.drawable.ic_mortgage,
                R.drawable.ic_other,
                R.drawable.ic_personal,
                R.drawable.ic_pets,
                R.drawable.ic_phone,
                R.drawable.ic_piggy_bank,
                R.drawable.ic_rent,
                R.drawable.ic_report,
                R.drawable.ic_safebox,
                R.drawable.ic_salary,
                R.drawable.ic_savings,
                R.drawable.ic_shopping,
                R.drawable.ic_transport,
                R.drawable.ic_travel,
                R.drawable.ic_utilities,
                R.drawable.ic_wallet
        )
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}