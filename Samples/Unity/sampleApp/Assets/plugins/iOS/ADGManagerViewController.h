#import <EventKit/EKEvent.h>
#import <EventKitUI/EKEventEditViewController.h>
#import "ADGAdTruthManager.h"
#import "ADGAdWebView.h"
#import "ADGBrowserViewController.h"
#import "ADGConstants.h"

@interface ADGManagerViewController : UIViewController <ADGBrowserViewControllerDelegate, ADGAdWebViewDelegate, ADGAdTruthManagerDelegate>
{
    id delegate_;
}
@property (nonatomic, assign) id delegate;
- (id) initWithAdParams:(NSDictionary*)params :(UIView*)parentView;
- (id) initWithAdParams:(NSDictionary*)params adView:(UIView*)parentView;
- (void) setLocationid:(NSString *)locationid;
- (void) setAdType:(ADGAdType)type;
- (void) setAdOrigin:(CGPoint)origin;
- (void) setFrame:(CGRect)rect;
- (void) setBackGround:(UIColor*)color :(BOOL)opaque;
- (void) setBackGround:(UIColor*)color opaque:(BOOL)opaque;
- (void) loadRequest;
- (void) resumeRefresh;
- (void) pauseRefresh;
- (void) willRotateToInterfaceOrientation;
- (void) didRotateFromInterfaceOrientation;
- (void) setMiddleware:(ADGMiddleware)mw;
- (void) setAdScale:(float)scale;
- (void) setFillerRetry:(BOOL)retry;
@end

@protocol ADGManagerViewControllerDelegate
@optional
- (void)ADGManagerViewControllerReceiveAd:(ADGManagerViewController *)adgManagerViewController;
- (void)ADGManagerViewControllerFailedToReceiveAd:(ADGManagerViewController *)adgManagerViewController;
- (void)ADGManagerViewControllerOpenUrl:(ADGManagerViewController *)adgManagerViewController;
- (void)ADGManagerViewControllerNeedConnection:(ADGManagerViewController *)adgManagerViewController;
- (void)ADGVideoViewAppear;
- (void)ADGVideoViewDisappear;
- (void)ADGBrowserViewControllerShow;
- (void)ADGBrowserViewControllerClose;
- (void)ADGManagerViewControllerReceiveFiller:(ADGManagerViewController *)adgManagerViewController;
@end
