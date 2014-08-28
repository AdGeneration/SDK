#import "ADGConstants.h"
#import "ADGBrowserViewController.h"
#import "ADGVideoViewController.h"
#import <EventKit/EKEvent.h>
#import <EventKitUI/EKEventEditViewController.h>
#import "ADGMraidController.h"
#import "ADGAdWebView.h"
#import "ADGAdTruthManager.h"

@interface ADGManagerViewController : UIViewController <ADGBrowserViewControllerDelegate, ADGMraidDelegate, ADGAdWebViewDelegate,ADGAdTruthManagerDelegate>
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
        
@end

@protocol ADGManagerViewControllerDelegate

@optional
- (void)ADGManagerViewControllerReceiveAd:(ADGManagerViewController *)adgManagerViewController;
- (void)ADGManagerViewControllerFailedToReceiveAd:(ADGManagerViewController *)adgManagerViewController;
- (void)ADGVideoViewAppear;
- (void)ADGVideoViewDisappear;
- (void)ADGBrowserViewControllerShow;
- (void)ADGBrowserViewControllerClose;
@end
