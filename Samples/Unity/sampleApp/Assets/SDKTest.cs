using UnityEngine;
using System.Collections;

public class SDKTest : MonoBehaviour, ADGUnitySDK.Callback {


	[SerializeField]
    ADGUnitySDK sdk_320x50;
	[SerializeField]
    ADGUnitySDK sdk_300x250;

	// Use this for initialization
	void Start () {
        sdk_320x50.callback = this;
        sdk_300x250.callback = this;
		// ADGUnitySDK.initADG();
	}
	
	// Update is called once per frame
	void Update () {
	}
	
	void OnGUI()
	{
		if (GUI.Button(new Rect(10, 170, 100, 50), "show"))
		{
            sdk_320x50.showADG();
            sdk_300x250.hideADG();
            Debug.Log("ad show");
			// ADGUnitySDK.showADG();
		}
		
		if (GUI.Button(new Rect(120, 170, 100, 50), "hide"))
		{
            sdk_320x50.hideADG();
            sdk_300x250.showADG();
			// ADGUnitySDK.hideADG();
		}
		
		if (GUI.Button(new Rect(10, 270, 100, 50), " Location1"))
		{
		    sdk_320x50.changeLocationADG(ADGUnitySDK.Horizontal.LEFT, ADGUnitySDK.Vertical.BOTTOM);	
            /*
			if(Application.platform == RuntimePlatform.IPhonePlayer){
				ADGUnitySDK.changeLocationADG( 0 , 350);
			} 
			else if(Application.platform == RuntimePlatform.Android){
				ADGUnitySDK.changeLocationADG("LEFT" , "BOTTOM");
			}
            */
		}
		
		if (GUI.Button(new Rect(120, 270, 100, 50), "Location2"))
		{
			
		    sdk_320x50.changeLocationADG(ADGUnitySDK.Horizontal.RIGHT, ADGUnitySDK.Vertical.TOP);
            /*
			if(Application.platform == RuntimePlatform.IPhonePlayer){
				ADGUnitySDK.changeLocationADG( 0 , 0);
			}
			else if(Application.platform == RuntimePlatform.Android){
				ADGUnitySDK.changeLocationADG("LEFT" , " TOP");
			}
            */
		}
	}
	
	// adgni callback message
	public void ADGReceiveAd(string str){
		Debug.Log(str);
	}
	
	public void ADGFailedToReceiveAd(string str){
		Debug.Log(str);
	}
	
	public void ADGBrowserShow(string str){
		Debug.Log(str);
	}
	
	public void ADGBrowserClose(string str){
		Debug.Log(str);
	}
	
	public void ADGVideoShow(string str){
		Debug.Log(str);
	}
	
	public void ADGVideoDisappear(string str){
		Debug.Log(str);
	}
}
