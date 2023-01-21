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
            integer.putExtra("w", data)
            context.startActivity(integer)
            true
        }
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AccountAdapter) return false

        if (context != other.context) return false
        if (accounts != other.accounts) return false
        if (inflater != other.inflater) return false

        return true
    }

    override fun hashCode(): Int {
        var result = context.hashCode()
        result = 31 * result + accounts.hashCode()
        result = 31 * result + inflater.hashCode()
        return result
    }

    override fun toString(): String {
        return "AccountAdapter(context=$context, accounts=$accounts, inflater=$inflater)"
    }
}