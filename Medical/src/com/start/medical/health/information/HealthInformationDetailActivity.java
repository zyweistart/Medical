package com.start.medical.health.information;

import android.os.Bundle;
import android.text.TextUtils;

import com.start.core.BaseActivity;
import com.start.medical.R;

/**
 * 健康资讯-详情
 * @author start
 *
 */
public class HealthInformationDetailActivity extends BaseActivity {
	
	public static final String RECORDNO="recordno";
	
	private String recordno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_healthinformation_detail);
		Bundle bundle=getIntent().getExtras();
		if(bundle!=null){
			recordno=bundle.getString(RECORDNO);
		}
		
		if(TextUtils.isEmpty(recordno)){
			finish();
		}
		
	}
	
}
