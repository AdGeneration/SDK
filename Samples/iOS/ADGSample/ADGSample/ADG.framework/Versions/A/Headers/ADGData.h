//
//  ADGData.h
//  ADG
//
//  Copyright © 2015年 adgeneration. All rights reserved.
//

@interface ADGData : NSObject

@property (nonatomic, strong, readonly) NSString *value;
@property (nonatomic, strong, readonly) NSString *label;
@property (nonatomic, strong, readonly) NSObject *ext;

- (instancetype)initWithDictionary:(NSDictionary *)dict;

@end
