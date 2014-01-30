#import <Foundation/Foundation.h>
#import <EventKit/EKEvent.h>
#import <EventKitUI/EKEventEditViewController.h>
#import <MessageUI/MFMessageComposeViewController.h>
#import <MediaPlayer/MPMoviePlayerController.h>
#import "ADGBrowserViewController.h"
#import "ADGVideoViewController.h"
#import "ADGUtil.h"

@interface ADGMraidController : NSObject <ADGBrowserViewControllerDelegate, MFMessageComposeViewControllerDelegate>
{
    id delegate_;
}
@property (nonatomic, assign) id delegate;
- (void) setDefaultRect:(CGRect)rect;
- (BOOL) processCommand:(NSURL*)url :(UIWebView*)wv;
@end

@protocol ADGMraidDelegate
- (void) ADGMraidShow;
- (void) ADGMraidHide;
- (void) ADGMraidClose:(BOOL)expandClose;
- (void) ADGMraidExpand;
- (void) ADGMraidResize:(CGRect)resizeRect;
- (void) ADGMraidPlayVideoAppear;
- (void) ADGMraidPlayVideoDisappear;
- (void) ADGMessageComposeViewController;
@end