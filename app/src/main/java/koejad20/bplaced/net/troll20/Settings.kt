package koejad20.bplaced.net.troll20

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var minAmount: EditText
    private val map: HashMap<Number, String> = HashMap()
    private val reverse: HashMap<String, Int> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        radioGroup = findViewById(R.id.groupy)
        minAmount = findViewById(R.id.minAmount)
        minAmount.setText(MainActivity.getMinAmount().toString())

        map[R.id.giro] = "giro"
        map[R.id.both] = "both"
        map[R.id.student] = "student"

        reverse["giro"] = R.id.giro
        reverse["student"] = R.id.student
        reverse["both"] = R.id.both

        radioGroup.check(reverse[MainActivity.getLabel()]!!)

        findViewById<Button>(R.id.reject).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.confirm).setOnClickListener {
            MainActivity.setLabel(map[radioGroup.checkedRadioButtonId] + "")
            MainActivity.setMinAmount(minAmount.text.toString().toLong())
            MainActivity.filter()
            finish()
        }

        findViewById<Button>(R.id.sort).setOnClickListener {
            MainActivity.sort()
        }
    }
}
