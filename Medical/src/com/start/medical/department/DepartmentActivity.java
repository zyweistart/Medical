package com.start.medical.department;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.start.core.AppException;
import com.start.core.BaseActivity;
import com.start.core.Constant;
import com.start.core.Constant.Handler;
import com.start.medical.R;
import com.start.service.HttpServer;
import com.start.service.RefreshListServer;
import com.start.service.RefreshListServer.RefreshListServerListener;
import com.start.service.Response;
import com.start.service.UIRunnable;
import com.start.service.User;
import com.start.widget.xlistview.XListView;

/**
 * 科室医生
 * @author start
 *
 */
public class DepartmentActivity extends BaseActivity implements RefreshListServerListener {
	
	private XListView mListView;
	private RefreshListServer mRefreshListServer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_department);
		setMainHeadTitle(getString(R.string.mainfunctiontxt6));
		mListView = (XListView) findViewById(R.id.xlv_listview);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				if(mRefreshListServer.getBaseListAdapter().getCount()>=position){
					Map<String,String> data=mRefreshListServer.getBaseListAdapter().getItem(position-1);
					Bundle bundle=new Bundle();
					bundle.putString(DoctorsActivity.RECORDNO, data.get(DoctorsActivity.RECORDNO));
					Intent intent=new Intent(getAppContext(),DoctorsActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
				}else{
					mRefreshListServer.getCurrentListView().startLoadMore();
				}
			}
		});
		mRefreshListServer = new RefreshListServer(this, mListView,new DepartmentAdapter(this));
		mRefreshListServer.setCacheTag(TAG);
		mRefreshListServer.setListTag("deptlist");
		mRefreshListServer.setInfoTag("deptinfo");
		mRefreshListServer.setRefreshListServerListener(this);

		mRefreshListServer.getHandlerContext().getHandler().sendEmptyMessage(Handler.LOAD_INIT_DATA);
	}

	@Override
	public void onLoading(final int HANDLER) {
		HttpServer hServer = new HttpServer(Constant.URL.hisdeptQuery,mRefreshListServer.getHandlerContext());
		Map<String,String> headers=new HashMap<String,String>();
		headers.put("sign", User.COMMON_ACCESSKEY_LOCAL);
		hServer.setHeaders(headers);
		Map<String, String> params = new HashMap<String, String>();
		params.put("accessid", User.COMMON_ACCESSID_LOCAL);
		params.put("currentpage",String.valueOf(mRefreshListServer.getCurrentPage() + 1));
		params.put("pagesize", String.valueOf(Constant.PAGESIZE));
		params.put("name", Constant.EMPTYSTR);
		hServer.setParams(params);
		hServer.get(new UIRunnable() {

			@Override
			public void run(Response response) throws AppException {
				mRefreshListServer.resolve(response);
				mRefreshListServer.getHandlerContext().getHandler().sendEmptyMessage(HANDLER);
			}

		}, false);
	}
	
}