package com.start.medical.health.records;

import java.util.HashMap;
import java.util.Map;

import start.core.AppConstant;
import start.core.AppConstant.Handler;
import start.core.AppException;
import start.service.HttpServer;
import start.service.RefreshListServer;
import start.service.RefreshListServer.RefreshListServerListener;
import start.service.Response;
import start.service.UIRunnable;
import start.service.User;
import start.widget.xlistview.XListView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.start.core.BaseActivity;
import com.start.core.Constant;
import com.start.medical.R;
import com.start.medical.health.wikipedial.HealthWikipediaAdapter;
import com.start.medical.health.wikipedial.HealthWikipediaDetailActivity;

/**
 * 健康档案
 * @author start
 *
 */
public class HealthRecordsActivity extends BaseActivity implements RefreshListServerListener {
	
	private XListView mListView;
	private RefreshListServer mRefreshListServer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_healthwikipedia);
		mListView = (XListView) findViewById(R.id.xlv_listview);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//				Map<String,String> data=mRefreshListServer.getBaseListAdapter().getItem(position-1);
				Bundle bundle=new Bundle();
				Intent intent=new Intent(getAppContext(),HealthWikipediaDetailActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		mRefreshListServer = new RefreshListServer(this, mListView,new HealthWikipediaAdapter(this));
		mRefreshListServer.setCacheTag(TAG);
		mRefreshListServer.setListTag("dislist");
		mRefreshListServer.setInfoTag("disinfo");
		mRefreshListServer.setRefreshListServerListener(this);

		mRefreshListServer.getHandlerContext().getHandler().sendEmptyMessage(Handler.LOAD_INIT_DATA);
	}

	@Override
	public void onLoading(final int HANDLER) {
		HttpServer hServer = new HttpServer(Constant.URL.htwikidisQuery,mRefreshListServer.getHandlerContext());
		Map<String,String> headers=new HashMap<String,String>();
		headers.put("sign", User.COMMON_ACCESSKEY_LOCAL);
		hServer.setHeaders(headers);
		Map<String, String> params = new HashMap<String, String>();
		params.put("accessid", User.COMMON_ACCESSID_LOCAL);
		params.put("currentpage",String.valueOf(mRefreshListServer.getCurrentPage() + 1));
		params.put("pagesize", String.valueOf(AppConstant.PAGESIZE));
		params.put("name", AppConstant.EMPTYSTR);
		params.put("overview", AppConstant.EMPTYSTR);
		params.put("dept", AppConstant.EMPTYSTR);
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

