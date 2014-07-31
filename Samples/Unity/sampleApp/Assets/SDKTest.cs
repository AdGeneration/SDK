using UnityEngine;
using System.Collections;

public class SDKTest : MonoBehaviour {

	// Use this for initialization
	void Start () {
		ADGUnitySDK.IOSLocationID = "10723";
		ADGUnitySDK.AndroidLocationID = "10724";
		ADGUnitySDK.AdType = "SP";
		ADGUnitySDK.X = 0;
		ADGUnitySDK.Y = 0;
		ADGUnitySDK.Horizontal = "CENTER";
		ADGUnitySDK.Vertical = "TOP";
		ADGUnitySDK.Width = 0;
		ADGUnitySDK.Height = 0;
		ADGUnitySDK.initADG();
	}
	
	// Update is called once per frame
	void Update () {
	}
	
	void OnGUI()
	{
		if (GUI.Button(new Rect(10, 170, 100, 50), "show"))
		{
			ADGUnitySDK.showADG();
		}
		
		if (GUI.Button(new Rect(120, 170, 100, 50), "hide"))
		{
			ADGUnitySDK.hideADG();
		}
		
		if (GUI.Button(new Rect(10, 270, 100, 50), " Location1"))
		{
			
			if(Application.platform == RuntimePlatform.IPhonePlayer){
				ADGUnitySDK.changeLocationADG( 0 , 350);
			} 
			else if(Application.platform == RuntimePlatform.Android){
				ADGUnitySDK.changeLocationADG("LEFT" , "BOTTOM");
			}
		}
		
		if (GUI.Button(new Rect(120, 270, 100, 50), "Location2"))
		{
			
			if(Application.platform == RuntimePlatform.IPhonePlayer){
				ADGUnitySDK.changeLocationADG( 0 , 0);
			}
			else if(Application.platform == RuntimePlatform.Android){
				ADGUnitySDK.changeLocationADG("LEFT" , " TOP");
			}
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
