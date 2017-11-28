package com.loh.skint.ui.record.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.loh.skint.R
import com.loh.skint.injection.component.ActivityComponent
import com.loh.skint.ui.base.activity.BaseActivity
import com.loh.skint.util.INTENT_ACCOUNT_ID
import com.loh.skint.util.INTENT_RECORD_ID
import com.loh.skint.util.colorize
import com.loh.skint.util.tint
import kotlinx.android.synthetic.main.activity_record_detail.*
import java.util.*
import javax.inject.Inject

class RecordDetailActivity : BaseActivity(), View {

    @Inject lateinit var presenter: Presenter

    private val deleteGoalDialog: MaterialDialog by lazy {
        MaterialDialog.Builder(this)
                .title(R.string.dialog_delete_record)
                .content(R.string.dialog_delete_record_message)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive({ _, _ -> presenter.deleteRecord() })
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackToolbar(toolbar)
        presenter.attach(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getRecord()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_record_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete_record) {
            deleteGoalDialog.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun getLayoutRes(): Int = R.layout.activity_record_detail

    override fun inject(component: ActivityComponent) = component.inject(this)

    override fun getAccountUUID() = intent.getSerializableExtra(INTENT_ACCOUNT_ID) as UUID

    override fun getRecordUUID() = intent.getSerializableExtra(INTENT_RECORD_ID) as UUID

    override fun setCategoryIcon(iconRes: Int) {
        record_icon.setImageResource(iconRes)
        record_icon.tint(R.color.white)
    }

    override fun setAmount(amount: String, color: Int) {
        record_amount.text = amount
        record_amount.colorize(color)
    }

    override fun setTransferType(type: String) {
        record_transfer_type_input.text = type
    }

    override fun setDate(date: String) {
        record_date_input.text = date
    }

    override fun setNote(note: String) {
        record_note_input.text = note
    }

    override fun showMessage(messageRes: Int) = Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()

    override fun exit() = finish()
}