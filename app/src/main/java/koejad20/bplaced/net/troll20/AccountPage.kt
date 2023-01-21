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
        account = intent.getParcelableExtra("w")!!
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
                val temp: Account = MainActivity.allAccounts!!.stream().filter{ e: Account -> e.iban.equals(iban.text.toString())  }.toList()[0]
                temp.amountOfMoney += value
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AccountPage) return false

        if (iban != other.iban) return false
        if (account != other.account) return false
        if (money != other.money) return false
        if (maxmoney != other.maxmoney) return false
        if (overdraft != other.overdraft) return false
        if (ibantwo != other.ibantwo) return false
        if (type != other.type) return false
        if (amountToTransfer != other.amountToTransfer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = iban.hashCode()
        result = 31 * result + account.hashCode()
        result = 31 * result + money.hashCode()
        result = 31 * result + maxmoney.hashCode()
        result = 31 * result + overdraft.hashCode()
        result = 31 * result + ibantwo.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + amountToTransfer.hashCode()
        return result
    }

    override fun toString(): String {
        return "AccountPage(iban=$iban, account=$account, money=$money, maxmoney=$maxmoney, overdraft=$overdraft, ibantwo=$ibantwo, type=$type, amountToTransfer=$amountToTransfer)"
    }
}