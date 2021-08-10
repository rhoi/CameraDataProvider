#include <assert.h>
#include <jni.h>
#include <camera/NdkCameraManager.h>
#include <string>

#include "messages-internal.h"

jint openCamera();

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_camera2_CameraParamsService_intFromJNI(JNIEnv *env, jobject thiz) {
    return openCamera();
}

int openCamera() {
    ACameraIdList *cameraIdList = NULL;
    camera_status_t camera_status = ACAMERA_OK;
    ACameraManager *cameraManager = ACameraManager_create();

    camera_status = ACameraManager_getCameraIdList(cameraManager, &cameraIdList);
    if (camera_status != ACAMERA_OK) {
        LOGE("Failed to get camera id list (reason: %d)\n", camera_status);
        return 0;
    }

    if (cameraIdList->numCameras < 1) {
        LOGE("No camera device detected.\n");
        return 0;
    } else {
        return cameraIdList->numCameras;
    }
}


