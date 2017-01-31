//
//  NativeAdViewController.m
//  ADGNativeSample
//
//  Copyright © 2016年 supership. All rights reserved.
//

#import "ADGFBNativeAdView.h"
#import "LogUtil.h"
#import "NativeAdVIew.h"
#import "NativeAdViewController.h"

@interface NativeAdViewController () {
    NSMutableArray *_outputs;
    UIView *_adView;
}
@property (nonatomic, weak) IBOutlet UITextView *textView;

@end

@implementation NativeAdViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.navigationItem.rightBarButtonItem =
        [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemRefresh
                                                      target:self
                                                      action:@selector(reload)];
    _adView = [[UIView alloc] initWithFrame:CGRectMake(10, 100, 300, 250)];
    [self.view addSubview:_adView];
}

- (void)reload {
    if (_adg) {
        [_adg loadRequest];
    }
}

// ログを追加します。
- (void)appendLog:(NSString *)log {
    if (!_outputs) {
        _outputs = [NSMutableArray new];
        [self.textView setContentOffset:CGPointZero animated:false];
    }
    [_outputs insertObject:[LogUtil format:log] atIndex:0];
    self.textView.text = [_outputs componentsJoinedByString:@"\n"];
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    if (!_adg) {
        NSDictionary *adgparam = @{
            @"locationid" : @"32792",
            @"adtype" : @(kADG_AdType_Free),
            @"originx" : @(0),
            @"originy" : @(0),
            @"w" : @(300),
            @"h" : @(250)
        };
        self.adg = [[ADGManagerViewController alloc] initWithAdParams:adgparam adView:_adView];
        _adg.delegate = self;
        _adg.rootViewController = self;
        [_adg setFillerRetry:NO];
        [_adg setUsePartsResponse:YES];
        [_adg loadRequest];
    } else {
        [_adg resumeRefresh];
    }
}

- (void)viewDidDisappear:(BOOL)animated {
    [super viewDidDisappear:animated];
    if (_adg) {
        [_adg pauseRefresh];
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (void)dealloc {
    _adg.delegate = nil;
    _adg.rootViewController = nil;
    _adg = nil;
    _nativeAd = nil;
}

#pragma mark - ADG Delegate

- (void)ADGManagerViewControllerReceiveAd:(ADGManagerViewController *)adgManagerViewController {
    [self appendLog:@"バナー広告をロードしました"];
}

- (void)ADGManagerViewControllerReceiveAd:(ADGManagerViewController *)adgManagerViewController
                        mediationNativeAd:(id)mediationNativeAd {
    [self appendLog:@"ネイティブ広告をロードしました"];

    if ([mediationNativeAd isKindOfClass:[ADGNativeAd class]]) {
        ADGNativeAd *nativeAd = (ADGNativeAd *)mediationNativeAd;

        // インフォメーションアイコンのデフォルト表示OFF
        adgManagerViewController.informationIconViewDefault = false;

        UIView *nativeAdView = [[NativeAdView alloc] createAdView:nativeAd];
        [_adg delegateViewManagement:nativeAdView nativeAd:nativeAd];
        [_adView addSubview:nativeAdView];
    }

    if ([mediationNativeAd isKindOfClass:[FBNativeAd class]]) {
        if (self.nativeAd) {
            [self.nativeAd unregisterView];
        }
        FBNativeAd *nativeAd = (FBNativeAd *)mediationNativeAd;
        self.nativeAd = nativeAd;
        UIView *nativeAdView = [[ADGFBNativeAdView alloc] createFBNativeAdView:self
                                                   adgManagerViewController:adgManagerViewController
                                                                   nativeAd:nativeAd];
        // ViewのADGManagerViewControllerクラスインスタンスへのセット（ローテーション時等の破棄制御並びに表示のため）
        [adgManagerViewController delegateViewManagement:nativeAdView];
        [_adView addSubview:nativeAdView];
    }
}

// エラー時のリトライは特段の理由がない限り必ず記述するようにしてください。
- (void)ADGManagerViewControllerFailedToReceiveAd:(ADGManagerViewController *)adgManagerViewController
                                             code:(kADGErrorCode)code {
    [self appendLog:@"エラーが発生しました"];
    // 不通とエラー過多のとき以外はリトライ
    switch (code) {
        case kADGErrorCodeNeedConnection:
            [self appendLog:@"ネットワーク接続不通"];
            break;
        case kADGErrorCodeNoAd:
            [self appendLog:@"レスポンス無し"];
            break;
        case kADGErrorCodeReceivedFiller:
            [self appendLog:@"白板検知"];
            [adgManagerViewController loadRequest];
            break;
        case kADGErrorCodeCommunicationError:
            [self appendLog:@"サーバ間通信エラー"];
            [adgManagerViewController loadRequest];
            break;
        case kADGErrorCodeExceedLimit:
            [self appendLog:@"エラー多発"];
            break;
        case kADGErrorCodeUnknown:
            [self appendLog:@"不明なエラー"];
            [adgManagerViewController loadRequest];
            break;
        default:
            [adgManagerViewController loadRequest];
            break;
    }
}

// 広告がタップされた際に呼び出されます。
- (void)ADGManagerViewControllerOpenUrl:(ADGManagerViewController *)adgManagerViewController {
    [self appendLog:@"広告がタップされました"];
}

@end
