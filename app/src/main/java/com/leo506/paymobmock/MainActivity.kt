package com.leo506.paymobmock

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.leo506.paymobmock.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val amount = intent.getStringExtra("AMOUNT")
        val packageNameToInvoke = intent.getStringExtra("PACKAGE_NAME") ?: ""
        val activityNameToInvoke = intent.getStringExtra("CLASS_PACKAGE_NAME") ?: ""

        binding.amountText.text = amount;

        binding.payButton.setOnClickListener {
            try {
                startActivity(Intent().apply {
                    component = ComponentName(packageNameToInvoke, activityNameToInvoke)
                    putExtra("STATUS", "Success")
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
                    addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
                })
                finish()
            } catch (e: ActivityNotFoundException) {
                AlertDialog.Builder(this).apply {
                    setTitle("Activity not found")
                    show()
                }
            }

        }
    }
}