#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "ADGManagerViewController.h"

extern "C" void UnitySendMessage(const char *, const char *, const char *);
extern UIViewController *UnityGetGLViewController();

#pragma mark - Objective-C code

@interface ADGNI : NSObject<ADGManagerViewControllerDelegate>{
    NSDictionary *adgparam_;
    NSString *gameObjName_;
}

@property (nonatomic, retain) ADGManagerViewController *adg;

@end

@implementation ADGNI

@synthesize adg = adg_;

- (void)setParams:(UIViewController *)viewCon adid:(NSString *)adid adtype:(NSString *)adtype x:(float)x y:(float)y objName:(NSString *)objName width:(int)width height:(int)height{
    //size
    NSInteger adTypeInt = 0;
    if([adtype isEqualToString:@"FREE"] && width > 0 && height > 0){
        adgparam_ = @{
                      @"locationid" : adid,
                      @"adtype" : @(kADG_AdType_Free),
                      @"originx" : @(x),
                      @"originy" : @(y),
                      @"w" : @(width),
                      @"h" : @(height)
                      };
    }
    else{
        if([adtype isEqualToString:@"SP"]){
            adTypeInt = kADG_AdType_Sp;
        }
        else if([adtype isEqualToString:@"LARGE"]){
            adTypeInt = kADG_AdType_Large;
        }
        else if([adtype isEqualToString:@"RECT"]){
            adTypeInt = kADG_AdType_Rect;
        }
        else if([adtype isEqualToString:@"TABLET"]){
            adTypeInt = kADG_AdType_Tablet;
        }
        
        //set parameters
        adgparam_ = @{
                      @"locationid" : adid,
                      @"adtype" : @(adTypeInt),
                      @"originx" : @(x),
                      @"originy" : @(y)
                      };
    }
    
    [adgparam_ retain];
    
    gameObjName_ = [[NSString stringWithString:objName] retain];
    
    adg_ = [[ADGManagerViewController alloc] initWithAdParams:adgparam_ :viewCon.view];	
    [adg_.view setHidden:NO];
    adg_.delegate = self;
    [adg_ loadRequest];
}

- (void)pause{
    [adg_ pauseRefresh];
}

- (void)resume{
    [adg_ resumeRefresh];
}

- (void)hide{
    [adg_.view setHidden:YES];
}

- (void)show{
    [adg_.view setHidden:NO];
}
    
- (void) changeLocation:(float)x y:(float)y{
    CGPoint point = CGPointMake(x, y);
    [adg_ setAdOrigin:point];
}

- (void)finish{
    if(!adg_)return;
    
    [gameObjName_ release];
    gameObjName_ = nil;
    
    [adgparam_ release];
    adgparam_ = nil;
    
    [adg_ pauseRefresh];
    [adg_.view setHidden:YES];
}

- (BOOL)canUseGameObj{
    if(!gameObjName_)return NO;
    return [gameObjName_ length] > 0;
}

#pragma mark ADGManagerViewControllerDelegate

- (void)ADGManagerViewControllerReceiveAd:(ADGManagerViewController *)adgManagerViewController
{
    if([self canUseGameObj]){
        UnitySendMessage([gameObjName_ UTF8String] , "ADGReceiveAd" , "ADGReceiveAd from iOS");
    }
}

- (void)ADGManagerViewControllerFailedToReceiveAd:(ADGManagerViewController *)adgManagerViewController
{
    if([self canUseGameObj]){
        UnitySendMessage([gameObjName_ UTF8String] , "ADGFailedToReceiveAd" , "ADGFailedToReceiveAd from iOS");
    }
}

- (void)ADGBrowserViewControllerShow
{
    if([self canUseGameObj]){
        UnitySendMessage([gameObjName_ UTF8String] , "ADGBrowserShow" , "ADGBrowserShow from iOS");
    }
}

- (void)ADGBrowserViewControllerClose
{
    if([self canUseGameObj]){
        UnitySendMessage([gameObjName_ UTF8String] , "ADGBrowserClose" , "ADGBrowserClose from iOS");
    }
}

- (void)ADGVideoViewAppear
{
    if([self canUseGameObj]){
        UnitySendMessage([gameObjName_ UTF8String] , "ADGVideoShow" , "ADGVideoShow from iOS");
    }
}

- (void)ADGVideoViewDisappear
{
    if([self canUseGameObj]){
        UnitySendMessage([gameObjName_ UTF8String] , "ADGVideoDisappear" , "ADGVideoDisappear from iOS");
    }
}

@end

#pragma mark - C++ code

#pragma mark definition for NativeInterface

extern "C"{
    void *_initADG(const char* adid , const char* adtype , float x , float y , const char* objName , int width , int height);
    void _renewADG(void *adgni , const char* adid , const char* adtype , float x , float y , const char* objName);
    void _pauseADG(void *adgni);
    void _resumeADG(void *adgni);
    void _hideADG(void *adgni);
    void _showADG(void *adgni);
    void _finishADG(void *adgni);
    void _changeLocationADG(void *adgni , int horizontal, int vertical, float left, float right, float top, float bottom);
}

#pragma mark method for NativeInterface

void *_initADG(const char* adid , const char* adtype , float x , float y , const char* objName , int width , int height)
{
    NSString *adidStr = [NSString stringWithCString:adid encoding:NSUTF8StringEncoding];
    NSString *adtypeStr = [NSString stringWithCString:adtype encoding:NSUTF8StringEncoding];
    NSString *objNameStr = [NSString stringWithCString:objName encoding:NSUTF8StringEncoding];
    
    id adgni = [[ADGNI alloc] init];
    [adgni setParams:UnityGetGLViewController() adid:adidStr adtype:adtypeStr x:x y:y objName:objNameStr width:width height:height];
    
    return adgni;
}

void _renewADG(void *adgni , const char* adid , const char* adtype , float x , float y , const char* objName){
    NSString *adidStr = [NSString stringWithCString:adid encoding:NSUTF8StringEncoding];
    NSString *adtypeStr = [NSString stringWithCString:adtype encoding:NSUTF8StringEncoding];
    NSString *objNameStr = [NSString stringWithCString:objName encoding:NSUTF8StringEncoding];
    
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp setParams:UnityGetGLViewController() adid:adidStr adtype:adtypeStr x:x y:y objName:objNameStr];
}

void _pauseADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp pause];
}

void _resumeADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp resume];
}

void _hideADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp hide];
}

void _showADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp show];
}

enum Horizontal {
    LEFT                = 1 << 0,
    CENTER_HORIZONTAL   = 1 << 1,
    RIGHT               = 1 << 2,
};

enum Vertical {
    TOP             = 1 << 0,
    CENTER_VERTICAL = 1 << 1,
    BOTTOM          = 1 << 2,
};

void _changeLocationADG(void *adgni , int horizontal, int vertical, float left, float top, float right, float bottom) {
    
    CGPoint point = CGPointZero;
    CGSize screenSize = UnityGetGLViewController().view.bounds.size;
    ADGNI *adgni_temp = (ADGNI *)adgni;
    CGSize bannerSize = adgni_temp.adg.view.bounds.size;
    
    if((horizontal & LEFT) == LEFT) {
        point.x = 0;
    }
    if((horizontal & RIGHT) == RIGHT) {
        point.x = screenSize.width - bannerSize.width;
    }
    if((horizontal & CENTER_HORIZONTAL) == CENTER_HORIZONTAL) {
        point.x = (screenSize.width - bannerSize.width)/2;
    }
    
    if((vertical & TOP) == TOP) {
        point.y = 0;
    }
    if((vertical & CENTER_VERTICAL) == CENTER_VERTICAL) {
        point.y = (screenSize.height - bannerSize.height)/2;
    }
    if((vertical & BOTTOM) == BOTTOM) {
        point.y = screenSize.height - bannerSize.height;
    }
    
    point.x += left;
    point.y += top;
    point.x -= right;
    point.y -= bottom;
    
    [adgni_temp changeLocation:point.x y:point.y];
}

void _finishADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp finish];
}

