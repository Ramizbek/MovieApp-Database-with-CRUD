package ramizbek.aliyev.movieapp_databasecrud

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import ramizbek.aliyev.movieapp_databasecrud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        smsDialog(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.my_container).navigateUp()

    companion object dialogShow {
        lateinit var dialog: AlertDialog
        fun smsDialog(context: Context) {
            val alertDialog = AlertDialog.Builder(context, R.style.NewDialog)


            dialog = alertDialog.create()

            val dialogView =
                LayoutInflater.from(context).inflate(R.layout.item_dialog, null, false)
            dialog.setView(dialogView)

            dialog.show()
            SmsDialog().start()
        }

        fun dialogCancel() {
            dialog.cancel()
        }
    }
}

class SmsDialog() : Thread() {
    override fun run() {
        super.run()
        sleep(2000)
        MainActivity.dialogCancel()
    }
}