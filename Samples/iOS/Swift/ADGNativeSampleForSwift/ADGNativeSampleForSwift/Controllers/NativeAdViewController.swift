//
//  NativeAdViewController.swift
//  ADGNativeSampleForSwift
//
//  Created on 2016/06/10.
//  Copyright © 2016年 Supership. All rights reserved.
//

import UIKit
import FBAudienceNetwork

class NativeAdViewController: UIViewController {
    
    @IBOutlet weak var adView: UIView!
    @IBOutlet weak var logTextView: UITextView!
    
    private var adg: ADGManagerViewController?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        if (adg == nil) {
            let adgParam = [
                "locationid": "32792",
                "adtype": String(ADGAdType.Free.rawValue),
                "originx": "0",
                "originy": "0",
                "w": "300",
                "h": "250"
            ]
            
            if let adgManagerViewController = ADGManagerViewController(adParams: adgParam, adView: adView) {
                adgManagerViewController.delegate = self
                adgManagerViewController.usePartsResponse = true
                adgManagerViewController.setFillerRetry(false)
                
                // == Facebook Audience Networkの場合の設定 ==
                // Facebook Audience Networkの広告表示には、
                // FBAudienceNetwork.frameworkとFBAudienceNetwork.frameworkに必要なframeworkを追加する必要があります。
                // 詳しくはマニュアルを参照してください。
                
                // 広告タップ時のアプリ内ブラウザ起動」「動画のインビュー判定」のため、最前面にあるUIRootViewControllerをセットしてください。
                adgManagerViewController.rootViewController = self
                
                // 実機でFANのテスト広告を表示する場合以下のメソッドを実行してください。
                // FBAdSettings.addTestDevice("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
                // == Facebook Audience Networkの場合の設定 ==
                
                adgManagerViewController.loadRequest()
                adg = adgManagerViewController
            }
        } else {
            adg?.resumeRefresh()
        }
    }
    
    override func viewDidDisappear(animated: Bool) {
        super.viewDidDisappear(animated)
        adg?.pauseRefresh()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func appendLog(log: String) {
        self.logTextView.text.appendContentsOf(Log.format(log))
    }
    
    @IBAction func didTapRefreshButton(sender: AnyObject) {
        adg?.loadRequest()
    }
}

extension NativeAdViewController: ADGManagerViewControllerDelegate {
    /**
     バナー広告の取得に成功した場合に呼び出されます
     */
    func ADGManagerViewControllerReceiveAd(adgManagerViewController: ADGManagerViewController!) {
        appendLog("バナー広告をロードしました")
    }
    
    /**
     ネイティブ広告の取得に成功した場合に呼び出されます
     - parameter adgManagerViewController: ADGManagerViewController
     - parameter mediationNativeAd: ネイティブ広告のインスタンス
     */
    func ADGManagerViewControllerReceiveAd(adgManagerViewController: ADGManagerViewController!, mediationNativeAd: AnyObject!) {
        appendLog("ネイティブ広告をロードしました")    

        if let nativeAd = mediationNativeAd as? ADGNativeAd {
            let rectAd = NativeAdView(adgManagerViewController: adgManagerViewController, nativeAd: nativeAd)
            adView.addSubview(rectAd)
        } else if let nativeAd = mediationNativeAd as? FBNativeAd {
            let rectAd = FBNativeAdView(adgManagerViewController: adgManagerViewController, nativeAd: nativeAd)
            adView.addSubview(rectAd)
        }
    }
    
    /**
     広告の取得に失敗した場合に呼び出されます
     - parameter adgManagerViewController: ADGManagerViewController
     - parameter code: エラーコード
     */
    func ADGManagerViewControllerFailedToReceiveAd(adgManagerViewController: ADGManagerViewController!, code: kADGErrorCode) {
        switch code {
        case .ADGErrorCodeNeedConnection:
            appendLog("エラーが発生しました ネットワーク接続不通")
        case .ADGErrorCodeNoAd:
            appendLog("エラーが発生しました レスポンス無し")
        case .ADGErrorCodeReceivedFiller:
            appendLog("エラーが発生しました 白板検知")
        case .ADGErrorCodeCommunicationError:
            appendLog("エラーが発生しました サーバ間通信エラー")
        case .ADGErrorCodeExceedLimit:
            appendLog("エラーが発生しました エラー多発")
        default:
            appendLog("エラーが発生しました 不明なエラー")
        }
        
        // 不通とエラー過多のとき以外はリトライしてください
        switch code {
        case .ADGErrorCodeNeedConnection, .ADGErrorCodeExceedLimit, .ADGErrorCodeNoAd:
            break
        default:
            adgManagerViewController.loadRequest()
        }
    }
}
