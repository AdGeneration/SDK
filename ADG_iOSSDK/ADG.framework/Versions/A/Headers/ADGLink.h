//
//  ADGLink.h
//  ADG
//
//  Copyright © 2015年 adgeneration. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ADGLink : NSObject

@property (nonatomic, strong, readonly, nullable) NSString *url;
@property (nonatomic, strong, readonly, nullable) NSArray *clicktrackers;
@property (nonatomic, strong, readonly, nullable) NSArray *postClicktrackers;
@property (nonatomic, strong, readonly, nullable) NSString *fallback;
@property (nonatomic, strong, readonly, nullable) NSObject *ext;

- (nonnull instancetype)initWithDictionary:(nullable NSDictionary *)dict;

- (void)setTapEvent:(nullable UIView *)view;

@end
