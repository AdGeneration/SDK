#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

@interface ADGMillennialWrapper : UIViewController
{
    id delegate_;
}
@property (nonatomic, assign) id delegate;
- (void) loadRequest:(NSString*)apid;
- (void) loadRequest:(NSString*)apid locManager:(CLLocationManager*)locManager;
@end
