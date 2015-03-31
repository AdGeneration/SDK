#include "ADGConnectionForAndroid.h"
#include <jni.h>
#include "platform/android/jni/JniHelper.h"

cocos2d::JniMethodInfo mInfo;
jobject adg = NULL;
char *originHorizontal;
char *originVertical;

void ADGConnectionForAndroid::initADG(char *adid , char *type , char *horizontal , char *vertical){
	initADGWithWH((char *)adid , (char *)type , (char *)horizontal , (char *)vertical , 0 , 0);
	return;
}

void ADGConnectionForAndroid::initADGWithWH(char *adid , char *type , char *horizontal , char *vertical , int width , int height){
    initADGWithScale((char *)adid , (char *)type , (char *)horizontal , (char *)vertical , 0 , 0 , 1.0);
    return;
}

void ADGConnectionForAndroid::initADGWithScale(char *adid , char *type , char *horizontal , char *vertical , int width , int height , double scale){
    originHorizontal = horizontal;
    originVertical = vertical;
    
    if (cocos2d::JniHelper::getStaticMethodInfo(mInfo, "com/socdm/d/adgeneration/plugin/cocos2dx/adgni/ADGNICocos2dx", "initADG", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IID)Lcom/socdm/d/adgeneration/plugin/cocos2dx/adgni/ADGNICocos2dx;")) {
        jstring jAdid = mInfo.env->NewStringUTF(adid);
        jstring jType = mInfo.env->NewStringUTF(type);
        jstring jHorizontal = mInfo.env->NewStringUTF(horizontal);
        jstring jVertical = mInfo.env->NewStringUTF(vertical);

        jmethodID methodID = 0;
        JNIEnv *pEnv = 0;
        jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
        jclass classID;
        
        if(adg == NULL){
            adg = mInfo.env->CallStaticObjectMethod(mInfo.classID, mInfo.methodID , jAdid , jType , jHorizontal , jVertical , width , height , scale);        
            adg = mInfo.env->NewGlobalRef(adg);
        }
        else if(ret == JNI_OK){
            classID = pEnv->GetObjectClass(adg);
            methodID = pEnv->GetMethodID(classID, "setParamsADG", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IID)V");
            mInfo.env->CallVoidMethod(adg, methodID , jAdid , jType , jHorizontal , jVertical , width , height , scale);            
        }

        if(ret == JNI_OK){
            classID = pEnv->GetObjectClass(adg);
            methodID = pEnv->GetMethodID(classID, "startADG", "()V");
            mInfo.env->CallVoidMethod(adg, methodID);
        }
        
        mInfo.env->DeleteLocalRef(mInfo.classID);
        mInfo.env->DeleteLocalRef(jAdid);
        mInfo.env->DeleteLocalRef(jType);
        mInfo.env->DeleteLocalRef(jHorizontal);
        mInfo.env->DeleteLocalRef(jVertical);
    }
    return;
}

void ADGConnectionForAndroid::showADG(){
    resumeADG();
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
    if(ret == JNI_OK){
        jclass classID = pEnv->GetObjectClass(adg);
        methodID = pEnv->GetMethodID(classID, "showADG", "()V");
        mInfo.env->CallVoidMethod(adg, methodID);
    }
}

void ADGConnectionForAndroid::hideADG(){
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
    if(ret == JNI_OK){
        jclass classID = pEnv->GetObjectClass(adg);
        methodID = pEnv->GetMethodID(classID, "hideADG", "()V");
        mInfo.env->CallVoidMethod(adg, methodID);
    }
    pauseADG();
}

void ADGConnectionForAndroid::resumeADG(){
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
    if(ret == JNI_OK){
        jclass classID = pEnv->GetObjectClass(adg);
        methodID = pEnv->GetMethodID(classID, "resumeADG", "()V");
        mInfo.env->CallVoidMethod(adg, methodID);
    }
}

void ADGConnectionForAndroid::pauseADG(){
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
    if(ret == JNI_OK){
        jclass classID = pEnv->GetObjectClass(adg);
        methodID = pEnv->GetMethodID(classID, "pauseADG", "()V");
        mInfo.env->CallVoidMethod(adg, methodID);
    }
}

void ADGConnectionForAndroid::changeLocationADG(char *horizontal , char *vertical){
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
    if(ret == JNI_OK){
        jclass classID = pEnv->GetObjectClass(adg);
        jstring jHorizontal = mInfo.env->NewStringUTF(horizontal);
        jstring jVertical = mInfo.env->NewStringUTF(vertical);
        methodID = pEnv->GetMethodID(classID, "changeLocationADG", "(Ljava/lang/String;Ljava/lang/String;)V");
        mInfo.env->CallVoidMethod(adg, methodID,jHorizontal,jVertical);
    }
}

void ADGConnectionForAndroid::moveDefaultLocationADG(){
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
    if(ret == JNI_OK){
        jclass classID = pEnv->GetObjectClass(adg);
        jstring jHorizontal = mInfo.env->NewStringUTF(originHorizontal);
        jstring jVertical = mInfo.env->NewStringUTF(originVertical);
        methodID = pEnv->GetMethodID(classID, "changeLocationADG", "(Ljava/lang/String;Ljava/lang/String;)V");
        mInfo.env->CallVoidMethod(adg, methodID,jHorizontal,jVertical);
    }
}

void ADGConnectionForAndroid::changeMarginADG(int left , int top , int right , int bottom){
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
    if(ret == JNI_OK){
        jclass classID = pEnv->GetObjectClass(adg);
        methodID = pEnv->GetMethodID(classID, "changeMarginADG", "(IIII)V");
        mInfo.env->CallVoidMethod(adg, methodID,left,top,right,bottom);
    }
}

void ADGConnectionForAndroid::finishADG(){
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
    if(ret == JNI_OK){
        jclass classID = pEnv->GetObjectClass(adg);
        methodID = pEnv->GetMethodID(classID, "finishADG", "()V");
        mInfo.env->CallVoidMethod(adg, methodID);
    }
    mInfo.env->DeleteGlobalRef(adg);
    adg = NULL;
}

void ADGConnectionForAndroid::initInterADG(char *adid , int backgroundType , int closeButtonType , int span , bool isPercentage , bool isPreventAccidentClick){
    if (cocos2d::JniHelper::getStaticMethodInfo(mInfo, "com/socdm/d/adgeneration/plugin/cocos2dx/adgni/ADGNICocos2dx", "initInterADG", "(Ljava/lang/String;IIIZZ)Lcom/socdm/d/adgeneration/plugin/cocos2dx/adgni/ADGNICocos2dx;")) {
        jstring jAdid = mInfo.env->NewStringUTF(adid);

        jmethodID methodID = 0;
        JNIEnv *pEnv = 0;
        jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
        jclass classID;
        
        if(adg == NULL){
            adg = mInfo.env->CallStaticObjectMethod(mInfo.classID, mInfo.methodID , jAdid , backgroundType , closeButtonType , span , isPercentage , isPreventAccidentClick);
            adg = mInfo.env->NewGlobalRef(adg);
        }
        else if(ret == JNI_OK){
            classID = pEnv->GetObjectClass(adg);
            methodID = pEnv->GetMethodID(classID, "setParamsInterADG", "(Ljava/lang/String;IIIZZ)V");
            mInfo.env->CallVoidMethod(adg, methodID , jAdid , backgroundType , closeButtonType , span , isPercentage , isPreventAccidentClick);            
        }

        if(ret == JNI_OK){
            classID = pEnv->GetObjectClass(adg);
            methodID = pEnv->GetMethodID(classID, "readyInterADG", "()V");
            mInfo.env->CallVoidMethod(adg, methodID);
        }
        
        mInfo.env->DeleteLocalRef(mInfo.classID);
        mInfo.env->DeleteLocalRef(jAdid);
    }
    return;
}

void ADGConnectionForAndroid::loadInterADG(){
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
    if(ret == JNI_OK){
        jclass classID = pEnv->GetObjectClass(adg);
        methodID = pEnv->GetMethodID(classID, "loadInterADG", "()V");
        mInfo.env->CallVoidMethod(adg, methodID);
    }
}

void ADGConnectionForAndroid::showInterADG(){
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
    if(ret == JNI_OK){
        jclass classID = pEnv->GetObjectClass(adg);
        methodID = pEnv->GetMethodID(classID, "showInterADG", "()V");
        mInfo.env->CallVoidMethod(adg, methodID);
    }
}

void ADGConnectionForAndroid::dismissInterADG(){
    jmethodID methodID = 0;
    JNIEnv *pEnv = 0;
    jint ret = cocos2d::JniHelper::getJavaVM()->GetEnv((void**)&pEnv, JNI_VERSION_1_4);
    if(ret == JNI_OK){
        jclass classID = pEnv->GetObjectClass(adg);
        methodID = pEnv->GetMethodID(classID, "dismissInterADG", "()V");
        mInfo.env->CallVoidMethod(adg, methodID);
    }
}

