using UnityEngine;
using System;
using System.Collections;
using System.Runtime.InteropServices;

public class ADGUnitySDK : MonoBehaviour {

	public interface Callback {
		void ADGReceiveAd(string str);
		void ADGFailedToReceiveAd(string str);
		void ADGBrowserShow(string str);
		void ADGBrowserClose(string str);
		void ADGVideoShow(string str);
		void ADGVideoDisappear(string str);
	}
	private Callback _callback;
	public Callback callback
	{
		set
		{
			_callback = value;
		}
	}


	public enum Horizontal : int {
		LEFT				= 1 << 0,
		CENTER_HORIZONTAL	= 1 << 1,
		RIGHT				= 1 << 2,
	}
	public enum Vertical : int {
		TOP				= 1 << 0,
		CENTER_VERTICAL	= 1 << 1,
		BOTTOM			= 1 << 2,
	}
	class SizeMake {
		public float width;
		public float height;
		public SizeMake(float width, float height) {
			this.width = width;
			this.height = height;
		}
	}
	public enum Size : int {
		SP_320x50		= 0,
		LARGE_320x100	= 1,
		RECT_300x250	= 2,
		TABLET_728x90	= 3,
		FREE			= 4, // アイコン広告用
	}

	[System.SerializableAttribute]
	public class Account {
		public Location android;
		public Location iOS;
	}
	[System.SerializableAttribute]
	public class Location {
		public string id;
		public Size size;
	}
	[System.SerializableAttribute]
	public class Margin {
		public float left;
		public float right;
		public float top;
		public float bottom;
        public Margin(Margin m) {
            left = m.left;
            right = m.right;
            top = m.top;
            bottom = m.bottom;
        }
	}
	[System.SerializableAttribute]
	public class Icon {
		public int count;
		public bool isText;
	}

	[SerializeField]
	Account account;
	[SerializeField]
	Icon icon;
	[SerializeField]
	Margin margin;
	[SerializeField]
	Vertical vertical;
	[SerializeField]
	Horizontal horizontal;


	#if UNITY_IPHONE
	private IntPtr adgni = IntPtr.Zero;
	[DllImport ("__Internal")]
	private static extern IntPtr _initADG (string adid , string adtype , float x , float y , string objName , int width , int height);
	[DllImport ("__Internal")]
	private static extern void _renewADG (IntPtr adgni , string adid , string adtype , float x , float y , string objName);
	[DllImport ("__Internal")]
	private static extern void _finishADG (IntPtr adgni);
	[DllImport ("__Internal")]
	private static extern void _resumeADG (IntPtr adgni);
	[DllImport ("__Internal")]
	private static extern void _pauseADG (IntPtr adgni);
	[DllImport ("__Internal")]
	private static extern void _showADG (IntPtr adgni);
	[DllImport ("__Internal")]
	private static extern void _hideADG (IntPtr adgni);
	[DllImport ("__Internal")]
	private static extern void _changeLocationADG (IntPtr adgni , int horizontal, int vertical, float left, float top, float right, float bottom);
	#elif UNITY_ANDROID
	private AndroidJavaObject androidPlugin;
	#endif
	
	private static bool isEditor{
		get{
			return Application.isEditor;
		}
	}

	private int width {
		get {
			return icon.isText?80*icon.count:57*icon.count;
		}
	}
	private int height {
		get {
			return icon.isText?80:57;
		}
	}

	private string ToSize(Size s) {
		switch(s) {
			case Size.SP_320x50:
				return "SP";
			case Size.LARGE_320x100:
				return "LARGE";
			case Size.RECT_300x250:
				return "RECT";
			case Size.TABLET_728x90:
				return "TABLET";
			default:
				return "FREE";
		}
	}
	private SizeMake getSizeMake(Size s) {
		switch(s) {
			case Size.SP_320x50:
				return new SizeMake(320, 50);
			case Size.LARGE_320x100:
				return new SizeMake(320, 100);
			case Size.RECT_300x250:
				return new SizeMake(300, 250);
			case Size.TABLET_728x90:
				return new SizeMake(728, 90);
			default:
				// TODO
				return new SizeMake(0, 0);
		}
	}

	public void initADG(){
		Debug.Log("initADG");
		GameObject gameObject = new GameObject("ADGForUnity");
		DontDestroyOnLoad(gameObject);//Makes the object target not be destroyed automatically when loading a new scene.
		gameObject.hideFlags = HideFlags.HideAndDontSave;//A combination of not shown in the hierarchy and not saved to to scenes.
#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
            if(adgni == IntPtr.Zero) {
			    adgni = _initADG(account.iOS.id , ToSize(account.iOS.size), 0, 0, this.gameObject.name, width, height);
            }
		}
#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin = new AndroidJavaObject("jp.cgate.unity.adgeneration.ADGNativeManager");
			androidPlugin.Call("initADG", account.android.id, (int)account.android.size, (int)horizontal, (int)vertical,  this.gameObject.name, width, height);
		}
#endif
	}
	
	public void finishADG(){
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
            if(adgni != IntPtr.Zero) {
			    _finishADG(adgni);
                adgni = IntPtr.Zero;
            }
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("finishADG");
		}
		#endif
	}

	public void resumeADG(){
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_resumeADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("resumeADG");
		}
		#endif
	}
	
	public void pauseADG(){
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_pauseADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("pauseADG");
		}
		#endif
	}
	
	public void showADG(){
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_resumeADG(adgni);
			_showADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("resumeADG");
			androidPlugin.Call("showADG");
		}
		#endif
	}
	
	public void hideADG(){
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_hideADG(adgni);
			_pauseADG(adgni);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("hideADG");
			androidPlugin.Call("pauseADG");
		}
		#endif
	}
	
	public void changeLocationADG(Horizontal horizontal, Vertical vertical, Margin margin) {
		#if UNITY_IPHONE
		if(Application.platform == RuntimePlatform.IPhonePlayer){
			_changeLocationADG(adgni, (int)horizontal, (int)vertical, margin.left, margin.top, margin.right, margin.bottom);
		}
		#elif UNITY_ANDROID
		if(Application.platform == RuntimePlatform.Android){
			androidPlugin.Call("changeLocationADG", (int)horizontal, (int)vertical, margin.left, margin.top, margin.right, margin.bottom);
		}
		#endif
	}
	public void changeLocationADG(Horizontal horizontal, Vertical vertical) {
		changeLocationADG(horizontal, vertical, margin);
	}

	public void setDefaultLocationADG() {
		changeLocationADG(horizontal, vertical, margin);
	}


	void Awake () {
		initADG();
	}

	// Use this for initialization
	void Start () {
		setDefaultLocationADG();
		hideADG();
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	//recieve mesasge
	void ADGReceiveAd(string str){
		if(_callback != null) {
			_callback.ADGReceiveAd(str);
		}
	}
	
	void ADGFailedToReceiveAd(string str){
		if(_callback != null) {
			_callback.ADGFailedToReceiveAd(str);
		}
	}
	
	void ADGBrowserShow(string str){
		if(_callback != null) {
			_callback.ADGBrowserShow(str);
		}
	}
	
	void ADGBrowserClose(string str){
		if(_callback != null) {
			_callback.ADGBrowserClose(str);
		}
	}
	
	void ADGVideoShow(string str){
		if(_callback != null) {
			_callback.ADGVideoShow(str);
		}
	}
	
	void ADGVideoDisappear(string str){
		if(_callback != null) {
			_callback.ADGVideoDisappear(str);
		}
	}
}
