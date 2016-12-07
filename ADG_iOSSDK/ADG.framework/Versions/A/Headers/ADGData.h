//
//  ADGData.h
//  ADG
//
//  Copyright © 2015年 adgeneration. All rights reserved.
//

@interface ADGData : NSObject

@property (nonatomic, strong, readonly, nullable) NSString *value;
@property (nonatomic, strong, readonly, nullable) NSString *label;
@property (nonatomic, strong, readonly, nullable) NSObject *ext;

- (nonnull instancetype)initWithDictionary:(nullable NSDictionary *)dict;

@end
