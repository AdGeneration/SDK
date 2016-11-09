#import <Foundation/Foundation.h>
#import <ADG/ADGManagerViewController.h>
#import <ADG/ADGInterstitial.h>

@interface ADGConnectionObjC : NSObject<ADGManagerViewControllerDelegate>{
    ADGManagerViewController *adg_;
    ADGInterstitial *adgInter_;
    NSDictionary *adgparam_;
}

- (void)initADG:(NSString*)adid type:(NSString*)type x:(int)x y:(int)y width:(int)width height:(int)height scale:(double)scale horizontal:(NSString *)horizontal vertical:(NSString *)vertical func:(void (*)(const char * , const char *))func;
- (void)loadADG;
- (void)pauseADG;
- (void)resumeADG;
- (void)showADG;
- (void)hideADG;
- (void)changeLocationADG:(int)x y:(int)y;
- (void)changeLocationEasyADG:(NSString *)horizontal vertical:(NSString *)vertical;
- (void)setScaleADG:(float)scale;
- (void)initInterADG:(NSString*)adid backgroundType:(int)backgroundType closeButtonType:(int)closeButtonType span:(int)span isPercentage:(bool)isPercentage isPreventAccidentClick:(bool)isPreventAccidentClick func:(void (*)(const char * , const char *))func;
- (void)loadInterADG;
- (void)showInterADG;
- (void)dismissInterADG;
- (float)getNativeWidthADG;
- (float)getNativeHeightADG;

@property (nonatomic, retain) ADGManagerViewController *adg;
@property (nonatomic, retain) ADGInterstitial *adgInter;

@end
