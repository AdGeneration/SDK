//
//  ADGTitle.h
//  ADG
//
//  Copyright © 2015年 adgeneration. All rights reserved.
//

@interface ADGTitle : NSObject

@property (nonatomic, strong, readonly, nullable) NSString *text;
@property (nonatomic, strong, readonly, nullable) NSObject *ext;

- (nonnull instancetype)initWithDictionary:(nullable NSDictionary *)dict;

@end
