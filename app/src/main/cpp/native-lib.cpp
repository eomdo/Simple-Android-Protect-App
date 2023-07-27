#include <jni.h>
#include <string>
#include "Calculator.h"

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_example_BSecure_LoginActivity_detectFrida(JNIEnv *env, jobject thiz) {
    FridaDetect frida = FridaDetect();
    return frida.is_frida_binary();
}