#import <UIKit/UIKit.h>
#import "ADGManagerViewController.h"

@interface ViewController : UIViewController <ADGManagerViewControllerDelegate>
{
    ADGManagerViewController *adg_;
}

@property (nonatomic, retain) ADGManagerViewController *adg;
@end
