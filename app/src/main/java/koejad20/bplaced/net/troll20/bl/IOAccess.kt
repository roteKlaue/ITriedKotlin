package koejad20.bplaced.net.troll20.bl

import android.content.Context
import koejad20.bplaced.net.troll20.pojos.Account
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.LinkedList
import kotlin.String
import kotlin.streams.toList

object IOAccess {
    fun getFileData(context: Context): List<Account> {
        try {
            return BufferedReader(InputStreamReader(context.assets.open("account_data.csv"))).lines()
                .skip(1)
                .map { c: String ->
                    val properties = c.split(",")
                    if (properties[1] == "GIRO") Account.makeGiroAccount(
                        properties[4].toLong(),
                        properties[3].toDouble(),
                        properties[2],
                        properties[6].toFloat()
                    ) else Account.makeChildAccountAccount(
                        properties[3].toDouble(),
                        properties[2],
                        properties[6].toFloat(),
                        properties[5].toBoolean()
                    )
                }.toList()
        } catch (e: IOException) {
            System.err.println(e.message)
        }
        return LinkedList()
    }
}