package com.start.medical.health.wikipedial;

import android.view.View;
import android.view.ViewGroup;

import com.start.core.BaseActivity;
import com.start.core.BaseListAdapter;
import com.start.medical.R;

public class HealthWikipediaAdapter extends BaseListAdapter{

	public HealthWikipediaAdapter(BaseActivity activity) {
		super(activity);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holder;
		if (convertView == null) {
			convertView = mActivity.getLayoutInflater().inflate(R.layout.listitem_healthwikipedia, null);
			holder = new HolderView();
//			holder.title = (TextView) convertView.findViewById(R.id.listitem_sharerecord_manager_title);
			convertView.setTag(holder);
		} else {
			holder = (HolderView) convertView.getTag();
		}
//		Map<String,String> data=mItemDatas.get(position);
//		holder.title.setText(data.get("voucherType"));
//		holder.name.setText(data.get("debtorUserName"));
//		holder.money.setText(data.get("money"));
//		holder.type.setText(data.get("bizType"));
//		holder.time.setText(TimeUtils.customerTimeConvert(data.get("bizTime")));
//		holder.status.setText("分享渠道："+data.get("enjoyWay"));
//		holder.no.setText("保全号："+data.get("recordNo"));
//		holder.ntime.setText("分享时间："+TimeUtils.customerTimeConvert(data.get("enjoyTime")));
		return convertView;
	}
	
	private class HolderView {
//		private TextView title;
//		private TextView name;
//		private TextView money;
//		private TextView type;
//		private TextView time;
//		private TextView status;
//		private TextView no;
//		private TextView ntime;
	}
	
}