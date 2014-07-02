package com.start.medical;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridLayout;

import com.start.core.BaseActivity;
import com.start.widget.SlidingLayout;

/**
 * 主界面
 * @author start
 *
 */
public class MainActivity extends BaseActivity implements OnClickListener{

	private SlidingLayout mSlidingLayout;
	
	private GridLayout mGridLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSlidingLayout = (SlidingLayout) findViewById(R.id.main_slidingLayout);
		mGridLayout = (GridLayout) findViewById(R.id.gl_layout);
		mSlidingLayout.setScrollEvent(mGridLayout);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.module_top_left){
			if (mSlidingLayout.isLeftLayoutVisible()) {
				mSlidingLayout.scrollToRightLayout();
			} else {
				mSlidingLayout.scrollToLeftLayout();
			}
		}else if(v.getId()==R.id.module_top_right){
			
		}
	}
	
}