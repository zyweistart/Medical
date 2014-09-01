package com.start.medical;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.start.core.BaseActivity;
import com.start.medical.personal.LoginActivity;
import com.start.widget.SlidingLayout;

/**
 * 主界面
 * @author start
 *
 */
public class MainActivity extends BaseActivity{

	private SlidingLayout mSlidingLayout;
	
	private List<ImageView> imageViews;
	private int[] imageResId = new int[] {
			R.drawable.default_banner_1,
			R.drawable.default_banner_2,
			R.drawable.default_banner_3,
			R.drawable.default_banner_4,
			R.drawable.default_banner_5 };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSlidingLayout = (SlidingLayout) findViewById(R.id.main_slidingLayout);
		mSlidingLayout.setScrollEvent(findViewById(R.id.main_content_sv));
		
		//设置Left功能区
		LinearLayout more_item_1=(LinearLayout)findViewById(R.id.more_item_1);
		setSlidingLeftModule(more_item_1,1);
		LinearLayout more_item_2=(LinearLayout)findViewById(R.id.more_item_2);
		setSlidingLeftModule(more_item_2,2);
		LinearLayout more_item_3=(LinearLayout)findViewById(R.id.more_item_3);
		setSlidingLeftModule(more_item_3,3);
		LinearLayout more_item_4=(LinearLayout)findViewById(R.id.more_item_4);
		setSlidingLeftModule(more_item_4,4);
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
		
		imageViews = new ArrayList<ImageView>();
		for (int i = 0; i < imageResId.length; i++) {
			ImageView imageView = new ImageView(MainActivity.this);
			imageView.setImageResource(imageResId[i]);
			imageViews.add(imageView);
		}
		
		ViewPager bannerPager=(ViewPager)findViewById(R.id.main_banner);
		bannerPager.setOffscreenPageLimit(imageResId.length);
		BannerAdapter ba=new BannerAdapter(this);
		ba.setItemDatas(imageViews);
		bannerPager.setAdapter(ba);
		
	}

	/**
	 * 设置Left功能区
	 */
	private void setSlidingLeftModule(LinearLayout layout,int type){
		ImageView ic_more=(ImageView)layout.findViewById(R.id.ic_more);
		TextView txt_more=(TextView)layout.findViewById(R.id.txt_more);
		switch(type){
		case 1:
			ic_more.setBackgroundResource(R.drawable.ic_more_aboutus);
			txt_more.setText(R.string.personal_center);
			break;
		case 2:
			ic_more.setBackgroundResource(R.drawable.ic_more_change_password);
			txt_more.setText(R.string.change_password);
			break;
		case 3:
			ic_more.setBackgroundResource(R.drawable.ic_more_clear_cache);
			txt_more.setText(R.string.mainfunctiontxt1);
			break;
		case 4:
			ic_more.setBackgroundResource(R.drawable.ic_more_feedback);
			txt_more.setText(R.string.login);
			break;
		}
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
		}else if(v.getId()==R.id.more_item_1){
			
		}else if(v.getId()==R.id.more_item_2){
			
		}else if(v.getId()==R.id.more_item_3){
			
		}else if(v.getId()==R.id.more_item_4){
			
		}else if(v.getId()==R.id.main_function_1){
			//手机挂号
			Log.v(TAG,"手机挂号");
		}else if(v.getId()==R.id.main_function_2){
			//妇保中心
			Log.v(TAG,"妇保中心");
		}else if(v.getId()==R.id.main_function_3){
			//报告单
			Log.v(TAG,"报告单");
		}else if(v.getId()==R.id.main_function_4){
			//疫苗接种
			Log.v(TAG,"疫苗接种");
		}else if(v.getId()==R.id.main_function_5){
			//医院导航
			Log.v(TAG,"医院导航");
		}else if(v.getId()==R.id.main_function_6){
			//科室医生
			Log.v(TAG,"科室医生");
		}else if(v.getId()==R.id.main_function_7){
			//健康百科
			Log.v(TAG,"健康百科");
		}else if(v.getId()==R.id.main_function_8){
			//健康资讯
			Log.v(TAG,"健康资讯");
		}else if(v.getId()==R.id.btn_login){
			Intent intent=new Intent(this,LoginActivity.class);
			startActivity(intent);
		}
	}
		
}