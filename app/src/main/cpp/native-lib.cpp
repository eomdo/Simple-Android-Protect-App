#include <jni.h>
#include <string>
#include "Calculator.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nativetest_LoginActivity_detectFrida(
        JNIEnv *env,
        jobject) {

    FridaDetect frida = FridaDetect();
    return reinterpret_cast<jstring>(frida.is_frida_binary());
}
