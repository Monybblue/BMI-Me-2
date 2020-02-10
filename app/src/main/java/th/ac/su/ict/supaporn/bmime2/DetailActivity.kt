package th.ac.su.ict.supaporn.bmime2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val bmi  = intent.getDoubleExtra("bmi",0.0)
        val result  = intent.getStringExtra("result")
        val height  = intent.getDoubleExtra("height",-1.0)
        val weight  = intent.getDoubleExtra("weight",-1.0)

        var btnClose   = findViewById<Button>(R.id.btnClose)
        var tvResultBMI    = findViewById<TextView>(R.id.tvResultBMI)
        var tvResult = findViewById<TextView>(R.id.tvResult)
        var tvWorH     = findViewById<TextView>(R.id.tvWorH)

        tvResultBMI.setText(bmi.round(2).toString())
        tvResult.setText(result)
        tvWorH.setText("height: "+height+" weight: "+weight)

        var btnShare = findViewById<Button>(R.id.btnShare)

        btnShare.setOnClickListener{

            var value    = "My BMI is "+bmi.round(2)+" ("+result+")"

            var intent     = Intent()
            intent.action  = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,value)
            intent.type    = "text/plan"

            startActivity(Intent.createChooser(intent,"share Info"))
        }

        btnClose.setOnClickListener {
            var Close = Intent(this@DetailActivity,MainActivity::class.java)

            startActivity(Close)
        }
    }

}
fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}