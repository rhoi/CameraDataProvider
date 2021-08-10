package com.example.camera2

import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import com.example.ICameraAIDLInterface

class CameraParamsService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return object : ICameraAIDLInterface.Stub(), IBinder {
            override fun getCamerasAmount(): Int {
                val camPermission = android.Manifest.permission.CAMERA
                if (checkSelfPermission(camPermission) == PackageManager.PERMISSION_GRANTED) {
                    return intFromJNI()
                }
                return 0
            }
        }
    }

    external fun intFromJNI(): Int

    companion object {
        init {
            System.loadLibrary("camera2")
        }
    }

}