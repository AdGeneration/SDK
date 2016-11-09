#include "ADGConnectionForIOS.h"
#include "ADGConnectionObjC.h"

static ADGConnectionObjC *adgc;
static NSString *horizontal_;
static NSString *vertical_;
static int defaultx;
static int defaulty;

void ADGConnectionForIOS::initADG(char *adid , char *type , int x , int y , void (*func)(const char * , const char *)){
    initADGAction((char *)adid , (char *)type , x , y , 0 , 0 , 1.0 , func);
    return;
}

void ADGConnectionForIOS::initADGWithWH(char *adid , char *type , int x , int y, int width , int height , void (*func)(const char * , const char *)){
    initADGAction((char *)adid , (char *)type , x , y , width , height , 1.0 , func);
}

void ADGConnectionForIOS::initADGWithScale(char *adid , char *type , int x , int y, int width , int height , double scale , void (*func)(const char * , const char *)){
    initADGAction((char *)adid , (char *)type , x , y , width , height , scale , func);
}

void ADGConnectionForIOS::initADGAction(char *adid , char *type , int x , int y , int width , int height , double scale , void (*func)(const char * , const char *)){
    if(!adgc){
        adgc = [[ADGConnectionObjC alloc] init];
    }
    
    defaultx = x;
    defaulty = y;
    
    [adgc initADG:[NSString stringWithCString:adid encoding:NSUTF8StringEncoding] type:[NSString stringWithCString:type encoding:NSUTF8StringEncoding] x:x y:y width:width height:height scale:(float)scale horizontal:horizontal_ vertical:vertical_ func:func];
    
    [adgc showADG];

    return;
}

void ADGConnectionForIOS::loadADG(){
    [adgc loadADG];
}

void ADGConnectionForIOS::showADG(){
    [adgc showADG];
    [adgc resumeADG];
}

void ADGConnectionForIOS::hideADG(){
    [adgc hideADG];
    [adgc pauseADG];
}

void ADGConnectionForIOS::pauseADG(){
    [adgc pauseADG];
}

void ADGConnectionForIOS::resumeADG(){
    [adgc resumeADG];
}

void ADGConnectionForIOS::changeLocationADG(int x , int y){
    [adgc changeLocationADG:x y:y];
}

void ADGConnectionForIOS::changeLocationEasyADG(char *horizontal , char *vertical){
    horizontal_ = [[NSString stringWithCString:horizontal encoding:NSUTF8StringEncoding] retain];
    vertical_ = [[NSString stringWithCString:vertical encoding:NSUTF8StringEncoding] retain];
    if(adgc){
        [adgc changeLocationEasyADG:horizontal_ vertical:vertical_];
    }
}

void ADGConnectionForIOS::moveDefaultLocationADG(){
    [adgc changeLocationADG:defaultx y:defaulty];
}

void ADGConnectionForIOS::finishADG(){
    [horizontal_ release];
    horizontal_ = nil;
    [vertical_ release];
    vertical_ = nil;

    [adgc hideADG];
    [adgc dismissInterADG];
}

void ADGConnectionForIOS::initInterADG(char *adid , int backgroundType , int closeButtonType , int span , bool isPercentage , bool isPreventAccidentClick , void (*func)(const char * , const char *)){
    if(!adgc){
        adgc = [[ADGConnectionObjC alloc] init];
    }

    [adgc initInterADG:[NSString stringWithCString:adid encoding:NSUTF8StringEncoding] backgroundType:backgroundType closeButtonType:closeButtonType span:span isPercentage:isPercentage isPreventAccidentClick:isPreventAccidentClick func:func];
}

void ADGConnectionForIOS::loadInterADG(){
    [adgc loadInterADG];
}

void ADGConnectionForIOS::showInterADG(){
    [adgc showInterADG];
}

void ADGConnectionForIOS::dismissInterADG(){
    [adgc dismissInterADG];
}

float ADGConnectionForIOS::getNativeWidth(){
    return [adgc getNativeWidthADG];
}

float ADGConnectionForIOS::getNativeHeight(){
    return [adgc getNativeHeightADG];
}