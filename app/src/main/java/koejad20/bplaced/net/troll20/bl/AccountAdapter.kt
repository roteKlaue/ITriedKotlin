package koejad20.bplaced.net.troll20.bl

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import koejad20.bplaced.net.troll20.AccountPage
import koejad20.bplaced.net.troll20.R
import koejad20.bplaced.net.troll20.pojos.Account
import java.util.*

class AccountAdapter(private val context: Context, private val accounts: List<Account>) :
    RecyclerView.Adapter<AccountHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountHolder {
        return AccountHolder(inflater.inflate(R.layout.bankaccount, parent, false))
    }

    override fun onBindViewHolder(holder: AccountHolder, position: Int) {
        val data = accounts[position]
        holder.getImage().setImageResource(data.imageId)
        holder.getIban().text = data.iban
        holder.getMaxMoney().text =
            String.format(Locale.ENGLISH, "%.02f $", data.overdraft.toFloat() + data.amountOfMoney)
        holder.getMoney().text =
            String.format(Locale.ENGLISH, "%.02f $", data.amountOfMoney.toFloat())
        holder.getLabel().text = data.label
        holder.getOverdraft().text =
            String.format(Locale.ENGLISH, "%.02f $", data.overdraft.toFloat())
        holder.getLayout().setOnLongClickListener {
            val integer = Intent(context, AccountPage::class.java)
            integer.putExtra("wriss", data)
            context.startActivity(integer)
            true
        }
    }

    override fun getItemCount(): Int {
        return accounts.size
    }
}