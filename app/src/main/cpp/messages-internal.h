#ifndef __CAMERA2NDK_MESSAGES_INTERNAL_H__
#define __CAMERA2NDK_MESSAGES_INTERNAL_H__

#include <android/log.h>

#define  LOG_TAG    "native-camera2-jni"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

#endif // __CAMERA2NDK_MESSAGES_INTERNAL_H__