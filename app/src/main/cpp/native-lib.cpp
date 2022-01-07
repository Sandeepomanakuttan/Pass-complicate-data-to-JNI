#include <jni.h>
#include <string>
using namespace std;
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_sandeep_passingdatatoandroidndk_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_sandeep_passingdatatoandroidndk_NativeLibrary_stringFromJNI(JNIEnv *env,
                            jobject thiz) {
    return env->NewStringUTF("hello from c++");


}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_sandeep_passingdatatoandroidndk_NativeLibrary_passDataToJni(JNIEnv *env,
                                                                             jobject thiz,
                                                                             jintArray array,
                                                                             jint number,
                                                                             jfloat fnumber){
    jint *temp =env->GetIntArrayElements(array, NULL);
    int ret=0;

    if (temp[0]==1 && temp[1]==2&&temp[2]==3&& number==4){
         ret=0;
    }else{
        ret=-1;
    }

    env->ReleaseIntArrayElements(array, temp, 0);
    return ret;
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_sandeep_passingdatatoandroidndk_NativeLibrary_passObjectToJni(JNIEnv *env,
                                                                               jobject thiz,
                                                                              jobject sampleobj) {
    jclass sampleclass = env->GetObjectClass(sampleobj);

    jfieldID sampleIntField = env->GetFieldID(sampleclass,"sint", "I");
    jfieldID sampleBooleanField = env->GetFieldID(sampleclass,"sboolean","Z");
    jfieldID sampleSringField = env->GetFieldID(sampleclass,"sString","Ljava/lang/String;");

    jint sampleInt =env -> GetIntField(sampleobj, sampleIntField);
    jboolean sampleBoolean = env->GetBooleanField(sampleobj,sampleBooleanField);

    const char *successString = "Success";

    env->SetObjectField(sampleobj,sampleSringField,env->NewStringUTF(successString));

    return sampleInt;



}
extern "C"
JNIEXPORT jobject JNICALL
Java_com_example_sandeep_passingdatatoandroidndk_NativeLibrary_getDataObj(JNIEnv *env,
                                                                          jobject thiz) {
    jclass sampleclass = env->FindClass("com/example/sandeep/passingdatatoandroidndk/Sample");
    jmethodID methodId = env->GetMethodID(sampleclass,"<init>", "()V");
    jobject sampleobj = env->NewObject(sampleclass,methodId);

    const char *suc="Success";
    jfieldID sampleSringField = env->GetFieldID(sampleclass,"sString","Ljava/lang/String;");
    env->SetObjectField(sampleobj,sampleSringField,env->NewStringUTF(suc));

    jint no=10;

    jfieldID smf=env->GetFieldID(sampleclass,"sint", "I");
    env->SetIntField(sampleobj,smf,no);

    return sampleobj;
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_sandeep_passingdatatoandroidndk_NativeLibrary_callBackMethod(JNIEnv *env,
                                                                              jobject obj) {

    jclass jclass1=env->GetObjectClass(obj);
    jmethodID id=env->GetMethodID(jclass1,"oncall", "(ILjava/lang/String;)V");

    jint data=10;
    const char *suc="Success";

    env->CallVoidMethod(obj,id,data,env->NewStringUTF(suc));

}