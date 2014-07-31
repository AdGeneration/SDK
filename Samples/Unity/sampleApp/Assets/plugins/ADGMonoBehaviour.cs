using UnityEngine;
using System;
using System.Collections;
using System.Runtime.InteropServices;

public class ADGMonoBehaviour : MonoBehaviour {
	#if UNITY_IPHONE
	[DllImport ("__Internal")]
	protected static extern IntPtr _initADG (string adid , string adtype , float x , float y , string objName , int width , int height);
	[DllImport ("__Internal")]
	protected static extern void _renewADG (IntPtr adgni , string adid , string adtype , float x , float y , string objName);
	[DllImport ("__Internal")]
	protected static extern void _finishADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _resumeADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _pauseADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _showADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _hideADG (IntPtr adgni);
	[DllImport ("__Internal")]
	protected static extern void _changeLocationADG (IntPtr adgni , float x , float y);
	[DllImport ("__Internal")]
	protected static extern void _setBackgroundColorADG (IntPtr adgni , int red , int green , int blue , int alpha);
	#endif
}