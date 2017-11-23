package com.loh.skint.ui.goal.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loh.skint.R
import com.loh.skint.domain.model.Goal
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.util.inflate
import kotlinx.android.synthetic.main.item_goal_list.view.*
import javax.inject.Inject

@ActivityScoped
class GoalListAdapter @Inject constructor() : RecyclerView.Adapter<GoalListAdapter.ViewHolder>() {

    var goals = mutableListOf<Goal>()
        set(value) {
            field = value
            notifyItemRangeChanged(0, field.size)
        }

    var callback: (Goal) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_goal_list))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goal = goals[position]

        holder.itemView.apply {
            item_goal_container.setOnClickListener { callback(goal) }
            item_goal_icon.setImageResource(goal.iconResId)
            item_goal_name.text = goal.name
            item_goal_saved_amount.text = goal.prettySavedAmount()
            item_goal_target_amount.text = goal.prettyTargetAmount()
            item_goal_progress.progress = goal.progress()
        }
    }

    override fun getItemCount(): Int = goals.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}