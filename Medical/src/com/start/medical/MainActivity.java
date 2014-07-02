package com.start.medical;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.start.core.AppException;
import com.start.core.BaseRefreshListActivity;
import com.start.core.Constant;
import com.start.core.Constant.Handle;
import com.start.service.HttpServer;
import com.start.service.Response;
import com.start.service.UIRunnable;
import com.start.widget.SlidingLayout;
import com.start.widget.xlistview.XListView;

/**
 * 主界面
 * @author start
 *
 */
public class MainActivity extends BaseRefreshListActivity implements OnClickListener{

	private SlidingLayout mSlidingLayout;
	
	private MainAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSlidingLayout = (SlidingLayout) findViewById(R.id.main_slidingLayout);
		mListView = (XListView) findViewById(R.id.xlv_content_list);
		mListView.setXListViewListener(this);
		mListView.setPullRefreshEnable(true);
		mListView.setPullLoadEnable(true);
		mListView.setAdapter(mAdapter);
		mSlidingLayout.setScrollEvent(mListView);
		//加载数据
		getHandlerContext().getHandler().sendEmptyMessage(Handle.LOAD_INIT_DATA);
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
	
	@Override
	protected void onLoading(final int HANDLER){
		HttpServer hServer=new HttpServer(Constant.URL.userpatientList, getHandlerContext());
		Map<String,String> params=new HashMap<String,String>();
		params.put("accessid", Constant.ACCESSID);
		hServer.setParams(params);
		hServer.get(new UIRunnable() {
			
			@Override
			public void run(Response response) throws AppException {
				//如果为下拉刷新则清除数据
				if(HANDLER==Handle.LOAD_END_PULLDOWN_REFRESH_DATA){
					mItemDatas.clear();
				}
				
				//TODO:解析数据
				for (int i = 0; i != 5; ++i) {
					mItemDatas.add("refresh cnt " + (i));
				}
				
				getHandlerContext().getHandler().sendEmptyMessage(HANDLER);
			}
			
		},false);
	}
	
	@Override
	protected void onLoadDone() {
		if(mAdapter==null){
			mAdapter = new MainAdapter();
			mListView.setAdapter(mAdapter);
		}else{
			mAdapter.notifyDataSetChanged();
		}
		super.onLoadDone();
	}
	
	private class HolderView {
		private TextView title;
	}
	
	private class MainAdapter extends BaseListAdapter{

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			HolderView holder;
			if (convertView == null) {
				holder = new HolderView();
				convertView = getLayoutInflater().inflate(R.layout.list_item_certificate, null);
				holder.title = (TextView) convertView.findViewById(R.id.list_item_certificate_title);
				convertView.setTag(holder);
			} else {
				holder = (HolderView) convertView.getTag();
			}
			holder.title.setText(mItemDatas.get(position));
			return convertView;
		}
		
	}
	
}