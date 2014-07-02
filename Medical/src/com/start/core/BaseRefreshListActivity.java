package com.start.core;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Message;
import android.widget.BaseAdapter;

import com.start.core.Constant.Handle;
import com.start.utils.TimeUtils;
import com.start.widget.xlistview.XListView;
import com.start.widget.xlistview.XListView.IXListViewListener;

/**
 * 
 * @author Start   
 * @Description: 列表刷新基础类
 * @ClassName: BaseRefreshListActivity.java   
 * @date 2014年6月30日 上午9:17:12      
 * @说明  代码版权归 杭州反盗版中心有限公司 所有
 */
public abstract class BaseRefreshListActivity extends BaseActivity implements IXListViewListener {

	/**
	 * 当前页
	 */
	protected int mCurrentPage;
	
	protected XListView mListView;
	
	protected List<String> mItemDatas = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCurrentPage=0;
	}
	
	@Override
	public void onRefresh() {
		getHandlerContext().getHandler().sendEmptyMessage(Handle.LOAD_START_PULLDOWN_REFRESH_DATA);
	}

	@Override
	public void onLoadMore() {
		getHandlerContext().getHandler().sendEmptyMessage(Handle.LOAD_START_MORE_DATA);
	}
	
	/**
	 * 数据加载中
	 * @param HANDLER  加载的类型区分是下拉刷新还是上拉加载更多
	 */
	protected abstract void onLoading(final int HANDLER);
	
	/**
	 * 数据加载完成
	 */
	protected void onLoadDone() {
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime(TimeUtils.getSysTime());
	}
	
	@Override
	public void onProcessMessage(Message msg) {
		switch (msg.what) {
			case Handle.LOAD_INIT_DATA:
				//TODO:初始化本地缓存数据
				//如果缓存数据为空则加载网络数据
				if(mItemDatas.isEmpty()){
					mListView.startLoadMore();
				}
				break;
			case Handle.LOAD_START_PULLDOWN_REFRESH_DATA:
				mCurrentPage=1;
				onLoading(Handle.LOAD_END_PULLDOWN_REFRESH_DATA);
				break;
			case Handle.LOAD_START_MORE_DATA:
				mCurrentPage++;
				onLoading(Handle.LOAD_END_MORE_DATA);
				break;
			case Handle.LOAD_DATA_FAIL_CLEAR_DATA:
				mCurrentPage=0;
				this.mItemDatas.clear();
			case Handle.LOAD_END_PULLDOWN_REFRESH_DATA:
			case Handle.LOAD_END_MORE_DATA:
				onLoadDone();
				break;
			default:
				super.onProcessMessage(msg);
				break;
		}
	}
	
	public abstract class BaseListAdapter extends BaseAdapter{
		
		@Override
		public int getCount() {
			return mItemDatas.size();
		}

		@Override
		public Object getItem(int position) {
			return mItemDatas.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		
	}
	
}
