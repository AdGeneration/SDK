//
//  ADGLink.h
//  ADG
//
//  Copyright © 2015年 adgeneration. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ADGLink : NSObject

@property (nonatomic, strong, readonly) NSString *url;
@property (nonatomic, strong, readonly) NSArray *clicktrackers;
@property (nonatomic, strong, readonly) NSArray *postClicktrackers;
@property (nonatomic, strong, readonly) NSString *fallback;
@property (nonatomic, strong, readonly) NSObject *ext;

- (instancetype)initWithDictionary:(NSDictionary *)dict;

- (void)setTapEvent:(UIView *)view;

@end
