#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    // Banner
    float y = [UIScreen mainScreen].applicationFrame.origin.y;
    NSDictionary *adgparam = @{
                               @"locationid" : @"10723",
                               @"adtype" : @(kADG_AdType_Sp),
                               @"originx" : @(0),
                               @"originy" : @(y)
                               };
    _adg = [[ADGManagerViewController alloc]initWithAdParams :adgparam :self.view];
    _adg.delegate = self;
    [_adg loadRequest];
    
    // Interstitial
    _adgInter = [[ADGInterstitial alloc] init];
    _adgInter.delegate = self;
    [_adgInter setBackgroundType:2];
    [_adgInter setCloseButtonType:2];
    //[_adgInter setPreventAccidentClick:YES];
    //[_adgInter setSpan:50 isPercentage:YES];
    [_adgInter setLocationId:@"18031"];
}

- (void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
    if(_adg){
        [_adg resumeRefresh];
    }
}

- (void) dealloc {
    _adg.delegate = nil;
    [_adg release];
    _adg = nil;
    
    [super dealloc];
}

- (void) viewWillDisappear:(BOOL)animated{
    [_adgInter dismiss];
    [_adgInter setDelegate:nil];
    [_adgInter release];
    _adgInter = nil;
    
    [super viewWillDisappear:animated];
}



- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

# pragma mark -
# pragma mark IBAction

- (IBAction)TapLoad:(id)sender {
    [_adgInter preload];
}

- (IBAction)TapShow:(id)sender {
    [_adgInter show];
}

# pragma mark -
# pragma mark ADGManagerViewControllerDelegate

- (void)ADGManagerViewControllerReceiveAd:(ADGManagerViewController *)adgManagerViewController
{
    NSLog(@"%@", @"ADGManagerViewControllerReceiveAd");
}

- (void)ADGManagerViewControllerFailedToReceiveAd:(ADGManagerViewController *)adgManagerViewController
{
    NSLog(@"%@", @"ADGManagerViewControllerFailedToReceiveAd");
}

- (void)ADGInterstitialClose{
    NSLog(@"%@", @"ADGInterstitialClose");
}

- (void)ADGBrowserViewControllerShow
{
    NSLog(@"%@", @"ADGBrowserViewControllerShow");
}

- (void)ADGBrowserViewControllerClose
{
    NSLog(@"%@", @"ADGBrowserViewControllerClose");
}

- (void)ADGVideoViewAppear
{
    NSLog(@"%@", @"ADGVideoViewAppear");
}

- (void)ADGVideoViewDisappear
{
    NSLog(@"%@", @"ADGVideoViewDisappear");
}

@end
