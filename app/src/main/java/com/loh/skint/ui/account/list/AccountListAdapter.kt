package com.loh.skint.ui.account.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.loh.skint.R
import com.loh.skint.injection.scope.ActivityScoped
import com.loh.skint.ui.model.Account
import com.loh.skint.util.inflate
import kotlinx.android.synthetic.main.item_account_list.view.*
import javax.inject.Inject

@ActivityScoped
class AccountListAdapter @Inject constructor() : RecyclerView.Adapter<ViewHolder>() {

    private var accounts: List<Account> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_account_list))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(accounts[position])
    }

    override fun getItemCount(): Int = this.accounts.size

    fun setAccounts(accounts: List<Account>) {
        this.accounts = accounts
        notifyItemRangeChanged(0, this.accounts.size)
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(account: Account) {
        itemView.apply {
            item_account_name.text = account.name
            item_account_balance.text = "£ ${account.balance}"

            item_account_icon.setImageResource(when (account.name) {
                "Current Account" -> R.drawable.ic_credit_card
                "Savings Account" -> R.drawable.ic_piggy_bank
                "Wallet" -> R.drawable.ic_wallet
                else -> R.drawable.ic_money_bag
            })
        }
    }
}