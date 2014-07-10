#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

@synthesize adg = adg_;

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    float y = [UIScreen mainScreen].applicationFrame.origin.y;
    NSDictionary *adgparam = @{
                               @"locationid" : @"10723",
                               @"adtype" : @(kADG_AdType_Sp),
                               @"originx" : @(0),
                               @"originy" : @(y)
                               };
    ADGManagerViewController *adgvc = [[ADGManagerViewController alloc]initWithAdParams :adgparam :self.view];
    self.adg = adgvc;
    [adgvc release];
    adg_.delegate = self;
    [adg_ loadRequest];
}

- (void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
    if(adg_){
        [adg_ resumeRefresh];
    }
}

- (void) dealloc {
    adg_.delegate = nil;
    [adg_ release];
    adg_ = nil;
    [super dealloc];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
