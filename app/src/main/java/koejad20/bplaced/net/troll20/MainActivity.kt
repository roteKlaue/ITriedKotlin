package koejad20.bplaced.net.troll20

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import koejad20.bplaced.net.troll20.bl.AccountAdapter
import koejad20.bplaced.net.troll20.bl.IOAccess
import koejad20.bplaced.net.troll20.pojos.Account
import java.util.*
import kotlin.streams.toList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerV : RecyclerView = findViewById(R.id.recycles)
        allAccounts = IOAccess.getFileData(this)
        adapter = AccountAdapter(this, allAccounts!!)
        recyclerV.adapter = adapter
        recyclerV.layoutManager = LinearLayoutManager(this)
        recyclerView = recyclerV
        findViewById<ImageButton>(R.id.settings).setOnClickListener {
            startActivity(Intent(this, Settings::class.java))
        }
        context = this
    }

     companion object {
         var allAccounts:List<Account>? = null
         @SuppressLint("StaticFieldLeak")
         var adapter: AccountAdapter? = null
         private val filters = object {
             var minAmount: Long = 0
             var label: String = "both"
         }

         var recyclerView: RecyclerView? = null

         fun setLabel(label: String) {
             filters.label = label
         }

         fun setMinAmount(amount: Long) {
             filters.minAmount = amount
         }

         fun getMinAmount(): Long {
             return filters.minAmount
         }

         @SuppressLint("StaticFieldLeak")
         var context: Context? = null

         fun filter() {
             recyclerView?.adapter = context?.let { cont ->
                 AccountAdapter(cont, allAccounts!!.stream().filter { it.label.lowercase(Locale.ROOT).equals(filters.label.lowercase(
                     Locale.ROOT)) || filters.label.equals("both") }
                     .filter { sus(it, filters.minAmount) }.toList())
             }
         }

         val sus: (Account, Long) -> Boolean = { a: Account, b: Long ->
             a.amountOfMoney >= b
         }
     }
}