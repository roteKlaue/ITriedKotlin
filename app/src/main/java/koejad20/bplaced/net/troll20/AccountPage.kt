package koejad20.bplaced.net.troll20

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import koejad20.bplaced.net.troll20.pojos.Account
import java.util.*
import kotlin.collections.ArrayList
import kotlin.streams.toList

class AccountPage : AppCompatActivity(), View.OnClickListener {
    private lateinit var iban: AutoCompleteTextView
    private lateinit var account: Account
    private lateinit var money: TextView
    private lateinit var maxmoney: TextView
    private lateinit var overdraft: TextView
    private lateinit var ibantwo: TextView
    private lateinit var type: TextView
    private lateinit var amountToTransfer: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        account = intent.getParcelableExtra("wriss")!!
        setContentView(R.layout.activity_account_page)
        ibantwo = findViewById(R.id.ibantwopointo)
        type = findViewById(R.id.type)
        iban = findViewById(R.id.auto)
        overdraft = findViewById(R.id.overdraft_maxxed)
        maxmoney = findViewById(R.id.maxmoney)
        money = findViewById(R.id.money)
        iban.threshold = 1
        iban.setAdapter(ArrayAdapter(this, android.R.layout.select_dialog_item, ArrayList(MainActivity.allAccounts!!.stream().map { it.iban }.toList())))
        amountToTransfer = findViewById(R.id.amountOfMoney)
        findViewById<Button>(R.id.back_out_of_bounds).setOnClickListener { finish() }
        findViewById<Button>(R.id.transfer).setOnClickListener(this)
        update()
    }

    override fun onClick(v: View?) {
        try {
            val value = amountToTransfer.text.toString().toDouble()
            if (MainActivity.allAccounts?.stream()?.map { it.iban }?.toList()?.contains(iban.text.toString()) == true && value > 0 && value <= account.amountOfMoney + account.overdraft) {
                account.amountOfMoney -= value
                MainActivity.allAccounts!!.stream().filter{ e: Account -> e.iban.equals(iban.text.toString())  }.toList()[0].amountOfMoney += value
                update()
                MainActivity.adapter!!.notifyDataSetChanged()
                finish()
            } else {
                Toast.makeText(this, "Please provide proper arguments.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: java.lang.NumberFormatException) {
            Toast.makeText(this, "Please provide proper arguments.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun update() {
        ibantwo.text = account.iban
        type.text = account.label
        money.text = String.format(Locale.ENGLISH, "%.02f", account.amountOfMoney.toFloat())
        maxmoney.text = String.format(Locale.ENGLISH, "%.02f", (account.amountOfMoney + account.overdraft).toFloat())
        overdraft.text = String.format(Locale.ENGLISH, "%.02f", account.overdraft.toFloat())
    }
}