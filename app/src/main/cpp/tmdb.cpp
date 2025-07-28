#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring
Java_com_movie_tmdb_core_secrets_NativeSecrets_apiFromJNI(JNIEnv *env, jobject thiz) {
    std::string api_secret = "4820fa8bf0cf72de14551eb3dd1c2efd";
    return env->NewStringUTF(api_secret.c_str());
}
