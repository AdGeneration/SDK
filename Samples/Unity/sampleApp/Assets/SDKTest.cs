using UnityEngine;
using System.Collections;

public class SDKTest : MonoBehaviour {

	// Use this for initialization
	void Start () {
		ADGUnitySDK.IOSLocationID = "10723";
		ADGUnitySDK.AndroidLocationID = "10724";
		ADGUnitySDK.AdType = "SP";
		ADGUnitySDK.IsIOSEasyPosition = true;
		ADGUnitySDK.X = 0;
		ADGUnitySDK.Y = 0;
		ADGUnitySDK.Horizontal = "CENTER";
		ADGUnitySDK.Vertical = "TOP";
		ADGUnitySDK.Width = 0;
		ADGUnitySDK.Height = 0;
		ADGUnitySDK.IOSInterLocationID = "18031";
		ADGUnitySDK.AndroidInterLocationID = "18031";
		ADGUnitySDK.BackgroundType = 2;
		ADGUnitySDK.CloseButtonType = 2;
		ADGUnitySDK.initADG();
		ADGUnitySDK.initInterADG();
	}
	
	// Update is called once per frame
	void Update () {
	}
	
	void OnGUI()
	{
		if (GUI.Button(new Rect(10, 170, 150, 50), "show"))
		{
			ADGUnitySDK.showADG();
		}
		
		if (GUI.Button(new Rect(170, 170, 150, 50), "hide"))
		{
			ADGUnitySDK.hideADG();
		}
		
		if (GUI.Button(new Rect(10, 270, 150, 50), " Location1"))
		{
			ADGUnitySDK.changeLocationADG("LEFT" , "BOTTOM");
		}
		
		if (GUI.Button(new Rect(170, 270, 150, 50), "Location2"))
		{
			ADGUnitySDK.changeLocationADG("LEFT" , " TOP");
		}
		if (GUI.Button(new Rect(10, 370, 150, 50), "load(Interstitial)"))
		{
			ADGUnitySDK.loadInterADG();
		}
		
		if (GUI.Button(new Rect(170, 370, 150, 50), "show(Interstitial)"))
		{
			ADGUnitySDK.showInterADG();
		}
	}
	
	//recieve mesasge
	void ADGReceiveAd(string str){
		Debug.Log(str);
	}
	
	void ADGFailedToReceiveAd(string str){
		Debug.Log(str);
	}
	
	void ADGBrowserShow(string str){
		Debug.Log(str);
	}
	
	void ADGBrowserClose(string str){
		Debug.Log(str);
	}
	
	void ADGVideoShow(string str){
		Debug.Log(str);
	}
	
	void ADGVideoDisappear(string str){
		Debug.Log(str);
	}
}
