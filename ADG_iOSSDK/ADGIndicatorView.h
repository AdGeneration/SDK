//
//  MasSspIndicatorView.h
//  medibaadSSPSDK_Sample
//
//  Created by    on 2013/08/27.
//  Copyright (c) 2013年 mediba.inc. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <QuartzCore/QuartzCore.h>

@interface ADGIndicatorView : UIView
{
    BOOL isValid_;
}
@property (nonatomic, readonly) BOOL isValid;
- (void) showIndicator;
- (void) hideIndicator;
@end
