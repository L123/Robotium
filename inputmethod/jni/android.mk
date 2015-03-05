LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_STATIC_JAVA_LIBRARIES := static-library

LOCAL_PACKAGE_NAME := com.huawei
include $(BUILD_PACKAGE)