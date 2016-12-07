#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <ADG/ADGManagerViewController.h>
#import <ADG/ADGInterstitial.h>

extern "C" void UnitySendMessage(const char *, const char *, const char *);
extern UIViewController *UnityGetGLViewController();

#pragma mark - Objective-C code

@interface ADGNI : NSObject<ADGManagerViewControllerDelegate , ADGInterstitialDelegate>{
    NSDictionary *adgParam_;
    NSDictionary *adgInterParam_;
    NSString *gameObjName_;
    NSString *adgInterId_;
    CGRect frame_;
    int width_;
    int height_;
    /*NSInteger backgroundType_;
    NSInteger closeButtonType_;
    NSInteger span_;
    BOOL isPercentage_;
    BOOL isPreventAccidentClick_;*/
}

@property (nonatomic, retain) ADGManagerViewController *adg;
@property (nonatomic, retain) ADGInterstitial *adgInter;

@end

@implementation ADGNI

@synthesize adg = adg_;
@synthesize adgInter = adgInter_;

- (void)setParams:(UIViewController *)viewCon adid:(NSString *)adid adtype:(NSString *)adtype x:(float)x y:(float)y objName:(NSString *)objName width:(int)width height:(int)height scale:(float)scale horizontal:(NSString *)horizontal vertical:(NSString *)vertical enableTest:(BOOL)enableTest{
    //size
    NSInteger adTypeInt = 0;
        if([adtype isEqualToString:@"SP"]){
        adTypeInt = kADG_AdType_Sp;
        width_ = kADGAdSize_Sp_Width;
        height_ = kADGAdSize_Sp_Height;
    }
    else if([adtype isEqualToString:@"LARGE"]){
        adTypeInt = kADG_AdType_Large;
        width_ = kADGAdSize_Large_Width;
        height_ = kADGAdSize_Large_Height;
    }
    else if([adtype isEqualToString:@"RECT"]){
        adTypeInt = kADG_AdType_Rect;
        width_ = kADGAdSize_Rect_Width;
        height_ = kADGAdSize_Rect_Height;
    }
    else if([adtype isEqualToString:@"TABLET"]){
        adTypeInt = kADG_AdType_Tablet;
        width_ = kADGAdSize_Tablet_Width;
        height_ = kADGAdSize_Tablet_Height;
    }
    else if([adtype isEqualToString:@"FREE"]){
        width_ = width;
        height_ = height;
    }

    frame_ = viewCon.view.frame;

    if(horizontal.length > 0){
        x = [self getXFromHorizontal:horizontal];
    }

    if(vertical.length > 0){
        y = [self getYFromtVertical:vertical];
    }

    if([adtype isEqualToString:@"FREE"] && width > 0 && height > 0){
        adgParam_ = @{
                      @"locationid" : adid,
                      @"adtype" : @(kADG_AdType_Free),
                      @"originx" : @(x),
                      @"originy" : @(y),
                      @"w" : @(width),
                      @"h" : @(height)
                      };
    }
    else{        
        //set parameters
        adgParam_ = @{
                      @"locationid" : adid,
                      @"adtype" : @(adTypeInt),
                      @"originx" : @(x),
                      @"originy" : @(y)
                      };
    }
    
    [adgParam_ retain];
    
    gameObjName_ = [[NSString stringWithString:objName] retain];
    
    adg_ = [[ADGManagerViewController alloc] initWithAdParams:adgParam_ adView:viewCon.view]; 
    [adg_.view setHidden:NO];
    adg_.delegate = self;
    adg_.rootViewController = viewCon;

    if(fabs(scale - 1.0) > 0.01){
        [adg_ setAdScale:scale];
    }
    
    [adg_ setEnableTestMode:enableTest];

    [adg_ loadRequest];
}

- (void)loadRequest{
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

- (void) changeLocationEasy:(NSString *)horizontal vertical:(NSString *)vertical{
    float x = [self getXFromHorizontal:horizontal];
    float y = [self getYFromtVertical:vertical];
    CGPoint point = CGPointMake(x, y);
    [adg_ setAdOrigin:point];
}

- (void) setBackgroundColorWithRed:(int)red green:(int)green blue:(int)blue alpha:(int)alpha{
    if(!adg_)return;

    if(alpha > 0.0){
        float f_red = (float)red / 255.0;
        float f_green = (float)green / 255.0;
        float f_blue = (float)blue / 255.0;
        float f_alpha = (float)alpha / 255.0;
        UIColor *color = [[UIColor colorWithRed:f_red green:f_green blue:f_blue alpha:f_alpha] retain];
        
        [adg_.view setBackgroundColor:color];
        [adg_ setBackGround:color opaque:NO];
    }
}

- (void)finish{
    if(!adg_)return;
    
    [self clearGameObjName];
    
    [adgParam_ release];
    adgParam_ = nil;   

    [adg_ pauseRefresh];
    [adg_.view setHidden:YES];
}

- (void)setInterParams:(UIViewController *)viewCon adid:(NSString *)adid objName:(NSString *)objName  backgroundType:(int)backgroundType closeButtonType:(int)closeButtonType span:(int)span isPercentage:(bool)isPercentage isPreventAccidentClick:(bool)isPreventAccidentClick enableTest:(bool)enableTest{
    gameObjName_ = [[NSString stringWithString:objName] retain];
    adgInterId_ = [adid copy];

    adgInter_ = [[ADGInterstitial alloc] init];
    adgInter_.delegate = self;
    adgInter_.rootViewController = viewCon;
    [adgInter_ setSpan:span isPercentage:isPercentage];
    [adgInter_ setBackgroundType:backgroundType];
    [adgInter_ setCloseButtonType:closeButtonType];
    [adgInter_ setPreventAccidentClick:isPreventAccidentClick];
    [adgInter_ setLocationId:adgInterId_];
    [adgInter_ setEnableTestMode:enableTest];
}

- (void)loadInter{
    [adgInter_ preload];
}

- (void)showInter{
    BOOL isShow = [adgInter_ show];
    if([self canUseGameObj] && !isShow){
        NSString *str = [NSString stringWithFormat:@"ADGInterstitialClose from iOS %@" , adgInterId_];
        UnitySendMessage([gameObjName_ UTF8String] , "ADGInterstitialClose" , [str UTF8String]);
    }
}

- (void)dismissInter{
    [adgInter_ dismiss];
}

- (void)finishInter{
    if(!adgInter_)return;
    [adgInter_ dismiss];

    [self clearGameObjName];

    [adgInter_ setDelegate:nil];
    [adgInter_ setRootViewController:nil];
    [adgInter_ release];
    adgInter_ = nil;
}

- (float)getNativeWidthADG{
    return (float)[self getViewWidth];
}

- (float)getNativeHeightADG{
    return (float)[self getViewHeight];
}

- (BOOL)canUseGameObj{
    if(!gameObjName_)return NO;
    return [gameObjName_ length] > 0;
}

- (void)clearGameObjName{
    if(!(adg_ && adgInter_)){
        [gameObjName_ release];
        gameObjName_ = nil;
    }
}

- (float)getXFromHorizontal:(NSString *)horizontal{
    float x = 0.0;
    if([horizontal isEqualToString:@"CENTER"]){
        x = ([self getViewWidth] - width_)  / 2;
    }
    else if([horizontal isEqualToString:@"RIGHT"]){
        x = [self getViewWidth] - width_;
    }
    return x;
}

- (float)getYFromtVertical:(NSString *)vertical{
    float y = 0.0;
    if([vertical isEqualToString:@"BOTTOM"]){
        y = [self getViewHeight] - height_;
    }
    else if([vertical isEqualToString:@"CENTER"]){
        y = ([self getViewHeight] - height_)  / 2;
    }
    return y;
}

- (int)getViewWidth{
    int width = frame_.size.width;
    switch ([UIApplication sharedApplication].statusBarOrientation) {
        case UIInterfaceOrientationPortrait:
        case UIInterfaceOrientationPortraitUpsideDown:
        width = [self getMinWithVal1:frame_.size.width val2:frame_.size.height];
        break;
        case UIInterfaceOrientationLandscapeLeft:
        case UIInterfaceOrientationLandscapeRight:
        width = [self getMaxWithVal1:frame_.size.width val2:frame_.size.height];
        break;
        case UIInterfaceOrientationUnknown:
        break;
    }
    return width;
}

- (int)getViewHeight{
    int height = frame_.size.height;
    switch ([UIApplication sharedApplication].statusBarOrientation) {
        case UIInterfaceOrientationPortrait:
        case UIInterfaceOrientationPortraitUpsideDown:
        height = [self getMaxWithVal1:frame_.size.width val2:frame_.size.height];
        break;
        case UIInterfaceOrientationLandscapeLeft:
        case UIInterfaceOrientationLandscapeRight:
        height = [self getMinWithVal1:frame_.size.width val2:frame_.size.height];
        break;
        case UIInterfaceOrientationUnknown:
        break;
    }
    return height;
}

- (int)getMaxWithVal1:(int)val1 val2:(int)val2{
    return val1 > val2 ? val1 : val2;
}

- (int)getMinWithVal1:(int)val1 val2:(int)val2{
    return val1 < val2 ? val1 : val2;
}

+ (void)addFANTestDevice:(NSString *)deviceHash {
    Class class_FBAdSettings = NSClassFromString(@"FBAdSettings");
    
    if (!class_FBAdSettings) return;
    if (![class_FBAdSettings respondsToSelector:sel_registerName([@"addTestDevice:" UTF8String])]) return;

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wobjc-method-access"
    [class_FBAdSettings addTestDevice:deviceHash];
#pragma clang diagnostic pop
}

#pragma mark ADGManagerViewControllerDelegate

- (void)ADGManagerViewControllerReceiveAd:(ADGManagerViewController *)adgManagerViewController
{
    if([self canUseGameObj]){
        NSString *str = [NSString stringWithFormat:@"ADGReceiveAd from iOS %@" , adgManagerViewController.locationid];
        UnitySendMessage([gameObjName_ UTF8String] , "ADGReceiveAd" , [str UTF8String]);
    }
}

- (void)ADGManagerViewControllerFailedToReceiveAd:(ADGManagerViewController *)adgManagerViewController code:(kADGErrorCode)code {
    if([self canUseGameObj]){
        NSString *str = @"";
        switch (code) {
            case kADGErrorCodeExceedLimit:
                str = [NSString stringWithFormat:@"ADGExceedErrorLimit from iOS %@" , adgManagerViewController.locationid];
                UnitySendMessage([gameObjName_ UTF8String] , "ADGExceedErrorLimit" , [str UTF8String]);
                break;
            case kADGErrorCodeNeedConnection:
                str = [NSString stringWithFormat:@"ADGNeedConnection from iOS %@" , adgManagerViewController.locationid];
                UnitySendMessage([gameObjName_ UTF8String] , "ADGNeedConnection" , [str UTF8String]);
                break;
            case kADGErrorCodeNoAd:
            case kADGErrorCodeCommunicationError:
            case kADGErrorCodeUnknown:
                str = [NSString stringWithFormat:@"ADGFailedToReceiveAd from iOS %@" , adgManagerViewController.locationid];
                UnitySendMessage([gameObjName_ UTF8String] , "ADGFailedToReceiveAd" , [str UTF8String]);
                break;
            case kADGErrorCodeReceivedFiller:
                str = [NSString stringWithFormat:@"ADGReceiveFiller from iOS %@" , adgManagerViewController.locationid];
                UnitySendMessage([gameObjName_ UTF8String] , "ADGReceiveFiller" , [str UTF8String]);
                break;
            default:
                break;
        }
    }
}

- (void)ADGManagerViewControllerOpenUrl:(ADGManagerViewController *)adgManagerViewController
{
    if([self canUseGameObj]){
        NSString *str = [NSString stringWithFormat:@"ADGOpenUrl from iOS %@" , adgManagerViewController.locationid];
        UnitySendMessage([gameObjName_ UTF8String] , "ADGOpenUrl" , [str UTF8String]);
    }
}

- (void)ADGInterstitialClose
{
    if([self canUseGameObj]){
        NSString *str = [NSString stringWithFormat:@"ADGInterstitialClose from iOS %@" , adgInterId_];
        UnitySendMessage([gameObjName_ UTF8String] , "ADGInterstitialClose" , [str UTF8String]);
    }
}

- (void)ADGManagerViewControllerCompleteRewardAd
{
    if([self canUseGameObj]){
        NSString *str = [NSString stringWithFormat:@"ADGCompleteRewardAd from iOS %@" , adgInterId_];
        UnitySendMessage([gameObjName_ UTF8String] , "ADGCompleteRewardAd" , [str UTF8String]);
    }
}

@end

#pragma mark - C++ code

#pragma mark definition for NativeInterface

extern "C"{
    void *_initADG(void *adgni , const char* adid , const char* adtype , float x , float y , const char* objName , int width , int height , float scale , const char* horizontal , const char* vertical, bool enableTest);
    void _loadADG(void *adgni);
    void _pauseADG(void *adgni);
    void _resumeADG(void *adgni);
    void _hideADG(void *adgni);
    void _showADG(void *adgni);
    void _finishADG(void *adgni);
    void _changeLocationADG(void *adgni , float x , float y);
    void _changeLocationEasyADG(void *adgni , const char* horizontal , const char* vertical);
    void _setBackgroundColorADG(void *adgni , int red , int green , int blue , int alpha);
    void _setAdScaleADG(void *adgni , float scale);
    void *_initInterADG(void *adgni , const char* adid , const char* objName , int backgroundType , int closeButtonType , int span , bool isPercentage , bool isPreventAccidentClick, bool enableTest);
    void _loadInterADG(void *adgni);
    void _showInterADG(void *adgni);
    void _dismissInterADG(void *adgni);
    void _finishInterADG(void *adgni);
    float _getNativeWidthADG(void *adgni);
    float _getNativeHeightADG(void *adgni);
    void _addFANTestDeviceADG(const char* deviceHash);
}

#pragma mark method for NativeInterface

void *_initADG(void *adgni , const char* adid , const char* adtype , float x , float y , const char* objName , int width , int height , float scale , const char* horizontal , const char* vertical, bool enableTest)
{
    NSString *adidStr = [NSString stringWithCString:adid encoding:NSUTF8StringEncoding];
    NSString *adtypeStr = [NSString stringWithCString:adtype encoding:NSUTF8StringEncoding];
    NSString *objNameStr = [NSString stringWithCString:objName encoding:NSUTF8StringEncoding];
    NSString *horizontalStr = [NSString stringWithCString:horizontal encoding:NSUTF8StringEncoding];
    NSString *verticalStr = [NSString stringWithCString:vertical encoding:NSUTF8StringEncoding];

    ADGNI *adgni_temp;
    
    if(adgni == NULL){
        adgni_temp = [[ADGNI alloc] init];
    }
    else{
        adgni_temp = (ADGNI *)adgni;
    }

    [adgni_temp setParams:UnityGetGLViewController() adid:adidStr adtype:adtypeStr x:x y:y objName:objNameStr width:width height:height scale:scale horizontal:horizontalStr vertical:verticalStr enableTest:enableTest];
    
    return adgni_temp;
}

void _loadADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp loadRequest];
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

void _changeLocationADG(void *adgni , float x , float y){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp changeLocation:x y:y];
}

void _changeLocationEasyADG(void *adgni , const char* horizontal , const char* vertical){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    NSString *horizontalStr = [NSString stringWithCString:horizontal encoding:NSUTF8StringEncoding];
    NSString *verticalStr = [NSString stringWithCString:vertical encoding:NSUTF8StringEncoding];
    [adgni_temp changeLocationEasy:horizontalStr vertical:verticalStr];
}

void _setBackgroundColorADG(void *adgni , int red , int green , int blue , int alpha){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp setBackgroundColorWithRed:red green:green blue:blue alpha:alpha];
}

void _finishADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp finish];
}

void *_initInterADG(void *adgni , const char* adid , const char* objName , int backgroundType , int closeButtonType , int span , bool isPercentage , bool isPreventAccidentClick, bool enableTest)
{
    NSString *adidStr = [NSString stringWithCString:adid encoding:NSUTF8StringEncoding];
    NSString *objNameStr = [NSString stringWithCString:objName encoding:NSUTF8StringEncoding];

    ADGNI *adgni_temp;
    
    if(adgni == NULL){
        adgni_temp = [[ADGNI alloc] init];
    }
    else{
        adgni_temp = (ADGNI *)adgni;
    }

    [adgni_temp setInterParams:UnityGetGLViewController() adid:adidStr objName:objNameStr backgroundType:backgroundType closeButtonType:closeButtonType span:span isPercentage:isPercentage isPreventAccidentClick:isPreventAccidentClick enableTest:enableTest];
    
    return adgni_temp;
}

void _loadInterADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp loadInter];
}

void _showInterADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp showInter];
}

void _dismissInterADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp dismissInter];
}

void _finishInterADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    [adgni_temp finishInter];
}

float _getNativeWidthADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    return [adgni_temp getNativeWidthADG];
}

float _getNativeHeightADG(void *adgni){
    ADGNI *adgni_temp = (ADGNI *)adgni;
    return [adgni_temp getNativeHeightADG];
}

void _addFANTestDeviceADG(const char* deviceHash){
    NSString *deviceHashStr = [NSString stringWithCString:deviceHash encoding:NSUTF8StringEncoding];
    [ADGNI addFANTestDevice:deviceHashStr];
}
