#import <UIKit/UIKit.h>
#import <ADG/ADGManagerViewController.h>
#import <ADG/ADGInterstitial.h>

@interface ViewController : UIViewController <ADGManagerViewControllerDelegate , ADGInterstitialDelegate>

@property (nonatomic, retain) ADGManagerViewController *adg;
@property (nonatomic, retain) ADGInterstitial *adgInter;

- (IBAction)TapLoad:(id)sender;
- (IBAction)TapShow:(id)sender;
@end
