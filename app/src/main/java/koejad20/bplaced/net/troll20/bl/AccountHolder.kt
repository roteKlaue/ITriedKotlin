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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AccountHolder) return false

        if (image != other.image) return false
        if (label != other.label) return false
        if (iban != other.iban) return false
        if (maxMoney != other.maxMoney) return false
        if (money != other.money) return false
        if (overdraft != other.overdraft) return false
        if (layout != other.layout) return false

        return true
    }

    override fun hashCode(): Int {
        var result = image.hashCode()
        result = 31 * result + label.hashCode()
        result = 31 * result + iban.hashCode()
        result = 31 * result + maxMoney.hashCode()
        result = 31 * result + money.hashCode()
        result = 31 * result + overdraft.hashCode()
        result = 31 * result + layout.hashCode()
        return result
    }

    override fun toString(): String {
        return "AccountHolder(image=$image, label=$label, iban=$iban, maxMoney=$maxMoney, money=$money, overdraft=$overdraft, layout=$layout)"
    }
}