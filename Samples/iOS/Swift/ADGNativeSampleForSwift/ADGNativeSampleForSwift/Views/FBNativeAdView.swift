//
//  FBNativeAdView.swift
//  ADGNativeSampleForSwift
//
//  Created on 2016/06/13.
//  Copyright © 2016年 Supership. All rights reserved.
//

import UIKit
import FBAudienceNetwork

class FBNativeAdView: UIView {
    
    var clickableViews: [UIView] = []
    
    required init(coder aDecoder: NSCoder) {
        fatalError("not implemented")
    }
    
    init(adgManagerViewController: ADGManagerViewController, nativeAd: FBNativeAd) {
        // 広告を貼り付けるViewを生成
        super.init(frame: CGRect(x: 0, y: 0, width: 300, height: 250))
        
        // アイコン
        if let adIcon = nativeAd.icon {
            let iconImageView = UIImageView(frame: CGRect(x: 4, y: 6, width: 30, height: 30))
            let iconImageData = NSData(contentsOfURL: adIcon.url)!
            iconImageView.image = UIImage(data: iconImageData)
            self.addSubview(iconImageView)
        }
        
        // タイトル
        if let adTitle = nativeAd.title {
            let titleLabel = UILabel(frame: CGRect(x: 38, y: 4, width: 258, height: 15))
            titleLabel.text = adTitle;
            titleLabel.numberOfLines = 1
            titleLabel.font = titleLabel.font.fontWithSize(13)
            self.addSubview(titleLabel)
            clickableViews.append(titleLabel)
        }
        
        // 広告マーク
        let adMarkLabel = UILabel(frame: CGRect(x: 38, y: 22, width: 28, height: 14))
        adMarkLabel.text = "広告"
        adMarkLabel.font = adMarkLabel.font.fontWithSize(11)
        adMarkLabel.textColor = UIColor.lightGrayColor()
        self.addSubview(adMarkLabel)
        
        // 本文
        if let adDesc = nativeAd.body {
            let descLabel = UILabel(frame: CGRect(x: 4, y: 30, width: 296, height: 40))
            descLabel.text = adDesc
            descLabel.numberOfLines = 2
            descLabel.font = descLabel.font.fontWithSize(11)
            descLabel.textColor = UIColor.lightGrayColor()
            self.addSubview(descLabel)
        }
        
        // 広告イメージ
        let adMedia = FBMediaView(frame: CGRect(x: 0, y: 65, width: 300, height: 156))
        adMedia.nativeAd = nativeAd
        adMedia.clipsToBounds = true
        self.addSubview(adMedia)
        clickableViews.append(adMedia)
        
        // Ad Choices(FANの広告オプトアウトへの導線です)
        let adChoices = FBAdChoicesView(nativeAd: nativeAd, expandable: true)
        adChoices.backgroundShown = false
        adMedia.addSubview(adChoices)
        adChoices.updateFrameFromSuperview(UIRectCorner.TopRight)
        
        /*
         静止画のみのコード例
         静止画のみのときはnativeAd.coverImage.urlを元にUIImageを生成する
         */
//        if let adImage = nativeAd.coverImage {
//            let imageView = UIImageView(frame: CGRect(x: 0, y: 65, width: 300, height: 156))
//            imageView.clipsToBounds = true
//            let imageData = NSData(contentsOfURL: adImage.url)!
//            let image = UIImage(data: imageData)
//            imageView.image = image
//            self.addSubview(imageView)
//            clickableViews.append(imageView)
//
//            let adChoices = FBAdChoicesView(nativeAd: nativeAd)
//            adChoices.backgroundShown = true
//            imageView.addSubview(adChoices)
//            adChoices.updateFrameFromSuperview(UIRectCorner.TopRight)
//        }
        
        // social context
        if let adSocial = nativeAd.socialContext {
            let socialLabel = UILabel(frame: CGRect(x: 4, y: 226, width: 150, height: 20))
            socialLabel.text = adSocial
            socialLabel.numberOfLines = 1
            socialLabel.font = socialLabel.font.fontWithSize(10)
            socialLabel.textColor = UIColor(red: 0.7, green: 0.7, blue: 0.7, alpha: 1.0)
            self.addSubview(socialLabel)
            clickableViews.append(socialLabel)
        }
        
        // CTA
        if let adCTA = nativeAd.callToAction {
            let actionButton = UIButton(frame: CGRect(x: 178, y: 223, width: 114, height: 25))
            actionButton.setTitle(adCTA, forState: UIControlState.Normal)
            actionButton.setTitleColor(UIColor(red: 0.12, green: 0.56, blue: 1.00, alpha: 1.0), forState: UIControlState.Normal)
            actionButton.titleLabel?.font = UIFont.boldSystemFontOfSize(14)
            actionButton.titleLabel?.adjustsFontSizeToFitWidth = true
            actionButton.titleEdgeInsets = UIEdgeInsets(top: 1.0, left: 1.0, bottom: 1.0, right: 1.0)
            actionButton.backgroundColor = UIColor.whiteColor()
            actionButton.layer.borderWidth = 1.0
            actionButton.layer.borderColor = UIColor(red: 0.12, green: 0.56, blue: 1.00, alpha: 1.0).CGColor
            actionButton.layer.cornerRadius = 5.0
            
            self.addSubview(actionButton)
            clickableViews.append(actionButton)
        }
        
        // クリック領域の指定。
        // 詳細はリファレンスのregisterViewForInteractionを参照。
        // https://developers.facebook.com/docs/reference/ios/current/class/FBNativeAd/
        nativeAd.registerViewForInteraction(self, withViewController: adgManagerViewController, withClickableViews: clickableViews)
        
        // ViewのADGManagerViewControllerクラスインスタンスへのセット（ローテーション時等の破棄制御並びに表示のため）
        adgManagerViewController.delegateViewManagement(self)
    }
}
