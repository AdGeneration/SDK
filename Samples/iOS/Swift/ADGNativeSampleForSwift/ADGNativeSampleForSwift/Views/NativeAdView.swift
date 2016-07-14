//
//  NativeAdView.swift
//  ADGNativeSampleForSwift
//
//  Created on 2016/06/10.
//  Copyright © 2016年 Supership. All rights reserved.
//

import UIKit

class NativeAdView: UIView {
    
    required init(coder aDecoder: NSCoder) {
        fatalError("not implemented")
    }
    
    init (adgManagerViewController: ADGManagerViewController, nativeAd: ADGNativeAd) {
        // 広告を貼り付けるViewを生成
        super.init(frame: CGRect(x: 0, y: 0, width: 300, height: 250))
        
        // アイコン
        if let adIcon = nativeAd.iconImage {
            if !adIcon.url.isEmpty {
                let iconImageView = UIImageView(frame: CGRect(x: 4, y: 6, width: 30, height: 30))
                let iconImageData = NSData(contentsOfURL: NSURL(string: adIcon.url)!)!
                iconImageView.image = UIImage(data: iconImageData)
                self.addSubview(iconImageView)
            }
        }
        
        // タイトル
        if let adTitle = nativeAd.title {
            if !adTitle.text.isEmpty {
                let titleLabel = UILabel(frame: CGRect(x: 38, y: 4, width: 258, height: 15))
                titleLabel.text = adTitle.text;
                titleLabel.numberOfLines = 1
                titleLabel.font = titleLabel.font.fontWithSize(13)
                self.addSubview(titleLabel)
            }
        }
        
        // 広告マーク
        let adMarkLabel = UILabel(frame: CGRect(x: 38, y: 22, width: 28, height: 14))
        adMarkLabel.text = "広告"
        adMarkLabel.font = adMarkLabel.font.fontWithSize(11)
        adMarkLabel.textColor = UIColor.lightGrayColor()
        self.addSubview(adMarkLabel)
        
        // 本文
        if let adDesc = nativeAd.desc {
            if !adDesc.value.isEmpty {
                let descLabel = UILabel(frame: CGRect(x: 4, y: 30, width: 296, height: 40))
                descLabel.text = adDesc.value
                descLabel.numberOfLines = 2
                descLabel.font = descLabel.font.fontWithSize(11)
                descLabel.textColor = UIColor.lightGrayColor()
                self.addSubview(descLabel)
            }
        }
        
        // 広告イメージ
        if let adImage = nativeAd.mainImage {
            if !adImage.url.isEmpty {
                let imageView = UIImageView(frame: CGRect(x: 0, y: 65, width: 300, height: 156))
                let imageData = NSData(contentsOfURL: NSURL(string: adImage.url)!)!
                imageView.image = UIImage(data: imageData)
                imageView.contentMode = UIViewContentMode.ScaleAspectFit
                imageView.clipsToBounds = true
                self.addSubview(imageView)
            }
        }
        
        // スポンサー
        if let adSponsored = nativeAd.sponsored {
            let sponsoredLabel = UILabel(frame: CGRect(x: 4, y: 226, width: 150, height: 20))
            if !adSponsored.value.isEmpty {
                sponsoredLabel.text = "sponsored by " + adSponsored.value
            } else {
                sponsoredLabel.text = "sponsored"
            }
            sponsoredLabel.numberOfLines = 1
            sponsoredLabel.font = sponsoredLabel.font.fontWithSize(10)
            sponsoredLabel.textColor = UIColor(red: 0.7, green: 0.7, blue: 0.7, alpha: 1.0)
            self.addSubview(sponsoredLabel)
        }
        
        // CTA
        if let adCTA = nativeAd.ctatext {
            if !adCTA.value.isEmpty {
                let actionButton = UIButton(frame: CGRect(x: 178, y: 223, width: 114, height: 25))
                actionButton.setTitle(adCTA.value, forState: UIControlState.Normal)
                actionButton.setTitleColor(UIColor(red: 0.12, green: 0.56, blue: 1.00, alpha: 1.0), forState: UIControlState.Normal)
                actionButton.titleLabel?.font = UIFont.boldSystemFontOfSize(14)
                actionButton.titleLabel?.adjustsFontSizeToFitWidth = true
                actionButton.titleEdgeInsets = UIEdgeInsets(top: 1.0, left: 1.0, bottom: 1.0, right: 1.0)
                actionButton.backgroundColor = UIColor.whiteColor()
                actionButton.layer.borderWidth = 1.0
                actionButton.layer.borderColor = UIColor(red: 0.12, green: 0.56, blue: 1.00, alpha: 1.0).CGColor
                actionButton.layer.cornerRadius = 5.0
                
                // ボタンへのタップ反応追加
                nativeAd.setTapEvent(actionButton)
                
                self.addSubview(actionButton)
            }
        }
        
        // Viewへのタップ制御やローテーション制御
        // 必ず記述してください
        adgManagerViewController.delegateViewManagement(self, nativeAd: nativeAd)
    }
}
