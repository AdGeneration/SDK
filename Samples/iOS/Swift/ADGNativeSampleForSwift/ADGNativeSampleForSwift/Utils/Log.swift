//
//  Log.swift
//  ADGNativeSampleForSwift
//
//  Created on 2016/06/13.
//  Copyright © 2016年 Supership. All rights reserved.
//

import Foundation

class Log {
    class func format(log: String) -> String {
        let dateFormatter = NSDateFormatter()
        dateFormatter.locale = NSLocale(localeIdentifier: "ja_JP")
        dateFormatter.dateFormat = "HH:mm:ss.SSS"
        return dateFormatter.stringFromDate(NSDate()) + " " + log + "\n"
    }
}