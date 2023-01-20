package koejad20.bplaced.net.troll20.bl

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import koejad20.bplaced.net.troll20.R

class AccountHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView
    private val label: TextView
    private val iban: TextView
    private val maxMoney: TextView
    private val money: TextView
    private val overdraft: TextView
    private val layout: RelativeLayout

    init {
        image = itemView.findViewById(R.id.image_for_account)
        label = itemView.findViewById(R.id.label)
        iban = itemView.findViewById(R.id.iban)
        maxMoney = itemView.findViewById(R.id.max_money_display)
        money = itemView.findViewById(R.id.money_display)
        overdraft = itemView.findViewById(R.id.overdraft)
        layout = itemView.findViewById(R.id.account_holding)
    }

    fun getLabel(): TextView {
        return label
    }

    fun getImage(): ImageView {
        return image
    }

    fun getIban(): TextView {
        return iban
    }

    fun getMoney(): TextView {
        return money
    }

    fun getMaxMoney(): TextView {
        return maxMoney
    }

    fun getOverdraft() : TextView {
        return overdraft
    }

    fun getLayout() : RelativeLayout {
        return layout
    }

}