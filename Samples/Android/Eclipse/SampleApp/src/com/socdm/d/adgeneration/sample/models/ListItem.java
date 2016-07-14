package com.socdm.d.adgeneration.sample.models;

import android.app.Activity;

public class ListItem {
	private int mainId;
	private int subId;
	private Class<? extends Activity> klass;
	public ListItem(int mainId, int subId, Class<? extends Activity> klass) {
		this.setMainId(mainId);
		this.setSubId(subId);
		this.setKlass(klass);
	}
	public int getMainId() {
		return mainId;
	}
	public void setMainId(int mainId) {
		this.mainId = mainId;
	}
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public Class<? extends Activity> getKlass() {
		return klass;
	}
	public void setKlass(Class<? extends Activity> klass) {
		this.klass = klass;
	}
}
