#import <Foundation/Foundation.h>
#import <ADG/ADGInterstitial.h>
#import "InterstitialViewController.h"

@interface InterstitialViewController() <ADGInterstitialDelegate>
@property (nonatomic, strong) ADGInterstitial *interstitial;
@end

@implementation InterstitialViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _interstitial = [[ADGInterstitial alloc] init];
    _interstitial.delegate = self;
    [_interstitial setLocationId:@"18031"];
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    if (_interstitial) {
        [_interstitial dismiss];
    }
}

- (void)dealloc {
    if (_interstitial) {
        _interstitial.delegate = nil;
        _interstitial = nil;
    }
}

#pragma mark - IBAction

- (IBAction)preloadInterstitial:(id)sender {
    if (_interstitial) {
        [_interstitial preload];
    }
}

- (IBAction)showInterstitial:(id)sender {
    if (_interstitial) {
        [_interstitial show];
    }
}

#pragma mark - ADGInterstitialDelegate

- (void)ADGManagerViewControllerReceiveAd:(ADGManagerViewController *)adgManagerViewController {
    NSLog(@"ADGInterstitialReceiveAd");
}

- (void)ADGManagerViewControllerFailedToReceiveAd:(ADGManagerViewController *)adgManagerViewController
                                             code:(kADGErrorCode)code {
    NSString *str = [ADGConstants kADGErrorCodetoString:code];
    NSLog(@"ADGInterstitialFailedToReceiveAd (code: %@)", str);
    switch (code) {
        case kADGErrorCodeExceedLimit:
        case kADGErrorCodeNeedConnection:
        case kADGErrorCodeNoAd:
            break;
        default:
            [_interstitial preload];
            break;
    }
}

- (void)ADGInterstitialClose {
    NSLog(@"ADGInterstitialClose");
}

@end
