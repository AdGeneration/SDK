package com.example.sampleapp;

//AdGeneration packages
import com.socdm.d.adgeneration.ADG;
import com.socdm.d.adgeneration.ADG.AdFrameSize;
import com.socdm.d.adgeneration.ADGListener;

import android.widget.RelativeLayout;
import android.view.Gravity;
import android.os.Bundle;
import android.app.Activity;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity {
	
	private ADG adg;
	static private RelativeLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        layout = new RelativeLayout(this);
        setContentView(layout ,  new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.TOP|Gravity.CENTER);
        
    	adg = new ADG(this);
    	adg.setLocationId("10724");//�L���g�ԍ�
    	adg.setAdFrameSize(AdFrameSize.SP);//�L���g�T�C�Y
    	adg.setAdListener(new AdListener());//�C�x���g�󂯎��ݒ�
		layout.addView(adg);
	}
	
    @Override
    protected void onResume() {
        adg.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        adg.stop();
        super.onPause();
    }
    
    //�C�x���g�擾�p���X�i�[�N���X
    class AdListener extends ADGListener {
        @Override
        public void onReceiveAd() {
           //�L���擾
        }
        @Override
        public void onFailedToReceiveAd() {
            //�L���擾���s
        }
        @Override
        public void onInternalBrowserOpen() {
            //�A�v�����u���E�U�I�[�v��
        }
        @Override
        public void onInternalBrowserClose() {
            //�A�v�����u���E�U�N���[�Y
        }
        @Override
        public void onVideoPlayerStart() {
            //����v���[���[�J�n
        }
        @Override
        public void onVideoPlayerEnd() {
            //����v���[���[�I��
        }
    }
}
