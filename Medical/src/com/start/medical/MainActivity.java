package com.start.medical;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.start.core.BaseActivity;
import com.start.widget.SlidingLayout;

/**
 * 主界面
 * @author start
 *
 */
public class MainActivity extends BaseActivity implements OnClickListener{

	private SlidingLayout mSlidingLayout;
	
	private ScrollView mMainContentSV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSlidingLayout = (SlidingLayout) findViewById(R.id.main_slidingLayout);
		mMainContentSV = (ScrollView) findViewById(R.id.main_content_sv_function);
		mSlidingLayout.setScrollEvent(mMainContentSV);
		
		//设置主功能区
		LinearLayout main_function_1=(LinearLayout)findViewById(R.id.main_function_1);
		setMainFunctionModule(main_function_1, 1);
		LinearLayout main_function_2=(LinearLayout)findViewById(R.id.main_function_2);
		setMainFunctionModule(main_function_2, 2);
		LinearLayout main_function_3=(LinearLayout)findViewById(R.id.main_function_3);
		setMainFunctionModule(main_function_3, 3);
		LinearLayout main_function_4=(LinearLayout)findViewById(R.id.main_function_4);
		setMainFunctionModule(main_function_4, 4);
		LinearLayout main_function_5=(LinearLayout)findViewById(R.id.main_function_5);
		setMainFunctionModule(main_function_5, 5);
		LinearLayout main_function_6=(LinearLayout)findViewById(R.id.main_function_6);
		setMainFunctionModule(main_function_6, 6);
		LinearLayout main_function_7=(LinearLayout)findViewById(R.id.main_function_7);
		setMainFunctionModule(main_function_7, 7);
		LinearLayout main_function_8=(LinearLayout)findViewById(R.id.main_function_8);
		setMainFunctionModule(main_function_8, 8);
		
	}

	/**
	 * 主体功能设置
	 */
	private void setMainFunctionModule(LinearLayout layout,int type){
		ImageView ic_main_function=(ImageView)layout.findViewById(R.id.ic_main_function);
		TextView txt_main_function=(TextView)layout.findViewById(R.id.txt_main_function);
		TextView txt_child_main_function=(TextView)layout.findViewById(R.id.txt_child_main_function);
		switch(type){
		case 1:
			ic_main_function.setBackgroundResource(R.drawable.ic_main_function_1);
			txt_main_function.setText(R.string.mainfunctiontxt1);
			txt_main_function.setTextColor(getResources().getColor(R.color.main_function_1));
			txt_child_main_function.setText(R.string.mainfunctionchiltxt1);
			break;
		case 2:
			ic_main_function.setBackgroundResource(R.drawable.ic_main_function_2);
			txt_main_function.setText(R.string.mainfunctiontxt2);
			txt_main_function.setTextColor(getResources().getColor(R.color.main_function_2));
			txt_child_main_function.setText(R.string.mainfunctionchiltxt2);
			break;
		case 3:
			ic_main_function.setBackgroundResource(R.drawable.ic_main_function_3);
			txt_main_function.setText(R.string.mainfunctiontxt3);
			txt_main_function.setTextColor(getResources().getColor(R.color.main_function_3));
			txt_child_main_function.setText(R.string.mainfunctionchiltxt3);
			break;
		case 4:
			ic_main_function.setBackgroundResource(R.drawable.ic_main_function_4);
			txt_main_function.setText(R.string.mainfunctiontxt4);
			txt_main_function.setTextColor(getResources().getColor(R.color.main_function_4));
			txt_child_main_function.setText(R.string.mainfunctionchiltxt4);
			break;
		case 5:
			ic_main_function.setBackgroundResource(R.drawable.ic_main_function_5);
			txt_main_function.setText(R.string.mainfunctiontxt5);
			txt_main_function.setTextColor(getResources().getColor(R.color.main_function_5));
			txt_child_main_function.setText(R.string.mainfunctionchiltxt5);
			break;
		case 6:
			ic_main_function.setBackgroundResource(R.drawable.ic_main_function_6);
			txt_main_function.setText(R.string.mainfunctiontxt6);
			txt_main_function.setTextColor(getResources().getColor(R.color.main_function_6));
			txt_child_main_function.setText(R.string.mainfunctionchiltxt6);
			break;
		case 7:
			ic_main_function.setBackgroundResource(R.drawable.ic_main_function_7);
			txt_main_function.setText(R.string.mainfunctiontxt7);
			txt_main_function.setTextColor(getResources().getColor(R.color.main_function_7));
			txt_child_main_function.setText(R.string.mainfunctionchiltxt7);
			break;
		case 8:
			ic_main_function.setBackgroundResource(R.drawable.ic_main_function_8);
			txt_main_function.setText(R.string.mainfunctiontxt8);
			txt_main_function.setTextColor(getResources().getColor(R.color.main_function_8));
			txt_child_main_function.setText(R.string.mainfunctionchiltxt8);
			break;
		}
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
			
		}else if(v.getId()==R.id.main_function_1){
			//
		}else if(v.getId()==R.id.main_function_2){
			//
		}else if(v.getId()==R.id.main_function_3){
			//
		}else if(v.getId()==R.id.main_function_4){
			//
		}else if(v.getId()==R.id.main_function_5){
			//
		}else if(v.getId()==R.id.main_function_6){
			//
		}else if(v.getId()==R.id.main_function_7){
			//
		}else if(v.getId()==R.id.main_function_8){
			//
		}
	}
	
}