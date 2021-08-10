package com.example.camera2

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.camera2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val camPermission = android.Manifest.permission.CAMERA
        if (checkSelfPermission(camPermission) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(camPermission), CAM_PERMISSION_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAM_PERMISSION_CODE
            && grantResults?.get(0) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "This app requires camera permission", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    companion object {
        val CAM_PERMISSION_CODE = 666
    }
}