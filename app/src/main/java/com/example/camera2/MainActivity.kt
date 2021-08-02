package com.example.camera2

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.camera2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()

    }

    override fun onResume() {
        super.onResume()

        // Ask for camera permission if necessary
        val camPermission = android.Manifest.permission.CAMERA
        if (Build.VERSION.SDK_INT >= 23 &&
            checkSelfPermission(camPermission) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(camPermission), CAM_PERMISSION_CODE)
        } else {
//            initCam()
//            camSurfaceView.onResume()
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

    fun isNativeCamSupported(): Boolean {
        val camManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        var nativeCamSupported = false

        for (camId in camManager.cameraIdList) {
            val characteristics = camManager.getCameraCharacteristics(camId)
            val hwLevel = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)
//            if (hwLevel != INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
//                nativeCamSupported = true
//                break
//            }
        }

        return nativeCamSupported
    }

    /**
     * A native method that is implemented by the 'camera2' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        val CAM_PERMISSION_CODE = 666
        // Used to load the 'camera2' library on application startup.
        init {
            System.loadLibrary("camera2")
        }
    }
}