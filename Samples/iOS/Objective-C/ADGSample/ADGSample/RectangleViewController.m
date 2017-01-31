#import <Foundation/Foundation.h>
#import <ADG/ADGManagerViewController.h>
#import "RectangleViewController.h"

@interface RectangleViewController() <ADGManagerViewControllerDelegate>
@property (nonatomic, strong) ADGManagerViewController *adg;
@end

@implementation RectangleViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    float originx = ([self.view bounds].size.width - 300) / 2;
    NSDictionary *adgparam = @{
                               @"locationid": @"18031", //管理画面から払い出された広告枠ID
                               @"adtype": @(kADG_AdType_Rect), //管理画面にて入力した枠サイズ(kADG_AdType_Sp：320x50, kADG_AdType_Large:320x100, kADG_AdType_Rect:300x250, kADG_AdType_Tablet:728x90, kADG_AdType_Free
                               @"originx": @(originx), //広告枠設置起点のx座標
                               @"originy": @(80)  //広告枠設置起点のy座標
                               };
    _adg = [[ADGManagerViewController alloc] initWithAdParams:adgparam adView:self.view];
    _adg.delegate = self;
    [_adg loadRequest];
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    if(_adg) {
        [_adg resumeRefresh];
    }
}

- (void)viewDidDisappear:(BOOL)animated {
    [super viewDidDisappear:animated];
    if (_adg) {
        [_adg pauseRefresh];
    }
}

- (void)dealloc {
    if (_adg) {
        _adg.delegate = nil;
        _adg = nil;
    }
}

#pragma mark - ADGManagerViewControllerDelegate

- (void)ADGManagerViewControllerReceiveAd:(ADGManagerViewController *)adgManagerViewController {
    NSLog(@"ADGManagerViewControllerReceiveAd");
}

- (void)ADGManagerViewControllerFailedToReceiveAd:(ADGManagerViewController *)adgManagerViewController
                                             code:(kADGErrorCode)code {
    NSString *str = [ADGConstants kADGErrorCodetoString:code];
    NSLog(@"ADGManagerViewControllerFailedToReceiveAd (code: %@)", str);
    switch (code) {
        case kADGErrorCodeExceedLimit:
        case kADGErrorCodeNeedConnection:
        case kADGErrorCodeNoAd:
            break;
        default:
            [adgManagerViewController loadRequest];
            break;
    }
}

@end
