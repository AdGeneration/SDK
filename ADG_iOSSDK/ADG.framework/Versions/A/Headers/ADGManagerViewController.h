#import <UIKit/UIKit.h>
#import "ADGAdWebView.h"
#import "ADGConstants.h"

@interface ADGManagerViewController : UIViewController <ADGAdWebViewDelegate> {
    id delegate_;
}
@property (nonatomic, strong) NSString *locationid;
@property (nonatomic, assign) id delegate;
@property (nonatomic, assign) UIViewController *rootViewController;
@property (nonatomic, assign) BOOL adgAdView;
@property (nonatomic, assign) BOOL closeOriginInter;
@property (nonatomic, assign) BOOL preventAccidentClick;
@property (nonatomic, assign) BOOL usePartsResponse;
- (id)initWithAdParams:(NSDictionary *)params adView:(UIView *)parentView;
- (void)setDelegate:(id)delegate failedLimit:(int)failedLimit;
- (void)setAdType:(ADGAdType)type;
- (void)setAdOrigin:(CGPoint)origin;
- (void)setAdScale:(float)scale;
- (void)setFrame:(CGRect)rect;
- (void)setFlexibleWidth:(float)percentage;
- (void)setBackGround:(UIColor *)color opaque:(BOOL)opaque;
- (void)updateView;
- (void)loadRequest;
- (void)resumeRefresh;
- (void)pauseRefresh;
- (void)setMiddleware:(ADGMiddleware)mw;
- (void)setFillerRetry:(BOOL)retry;
- (void)delegateViewManagement:(UIView *)view;
- (void)delegateViewManagement:(UIView *)view nativeAd:(id)nativeAd;

- (void)finishMediation;
- (void)waitShowing;
- (void)show;
- (void)setLat:(double)lat;
- (void)setLon:(double)lon;
- (void)addMediationNativeAdView:(UIView *)mediationNativeAdView;
- (void)setEnableSound:(BOOL)enableSound;
- (void)setEnableTestMode:(BOOL)isTest;
- (void)setFillerLimit:(int)limit;

- (void)setPreLoad:(BOOL)preLoad;
- (void)stopAutomaticLoad;
- (void)setDivideShowing:(BOOL)devide;

- (BOOL)isReadyForInterstitial;

// deprecated methods
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wmissing-selector-name"
- (id)initWithAdParams:(NSDictionary *)params :(UIView *)parentView __attribute__((deprecated));
- (NSString *)getLocationid __attribute__((deprecated));
- (void)setBackGround:(UIColor *)color :(BOOL)opaque __attribute__((deprecated));
#pragma clang diagnostic pop
@end

@protocol ADGManagerViewControllerDelegate
@optional
- (void)ADGManagerViewControllerReceiveAd:(ADGManagerViewController *)adgManagerViewController;
- (void)ADGManagerViewControllerReceiveAd:(ADGManagerViewController *)adgManagerViewController
                        mediationNativeAd:(id)mediationNativeAd;
- (void)ADGManagerViewControllerFailedToReceiveAd:(ADGManagerViewController *)adgManagerViewController
                                             code:(kADGErrorCode)code;
- (void)ADGManagerViewControllerOpenUrl:(ADGManagerViewController *)adgManagerViewController;
- (void)ADGManagerViewControllerFinishImpression:(ADGManagerViewController *)adgManagerViewController;
- (void)ADGManagerViewControllerFailInImpression:(ADGManagerViewController *)adgManagerViewController;

// deprecated delegates
- (void)ADGManagerViewControllerFailedToReceiveAd:(ADGManagerViewController *)adgManagerViewController
    __attribute__((deprecated));
- (void)ADGManagerViewControllerReceiveFiller:(ADGManagerViewController *)adgManagerViewController
    __attribute__((deprecated));
- (void)ADGManagerViewControllerNeedConnection:(ADGManagerViewController *)adgManagerViewController
    __attribute__((deprecated));
- (void)ADGVideoViewAppear __attribute__((deprecated));
- (void)ADGVideoViewDisappear __attribute__((deprecated));
- (void)ADGBrowserViewControllerShow __attribute__((deprecated));
- (void)ADGBrowserViewControllerClose __attribute__((deprecated));
@end
