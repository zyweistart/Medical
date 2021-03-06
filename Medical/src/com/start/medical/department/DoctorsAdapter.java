package com.start.medical.department;

import java.util.Map;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.start.core.BaseActivity;
import com.start.core.BaseListAdapter;
import com.start.medical.R;

public class DoctorsAdapter extends BaseListAdapter{

	public DoctorsAdapter(BaseActivity activity) {
		super(activity);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holder;
		if (convertView == null) {
			convertView = mActivity.getLayoutInflater().inflate(R.layout.listitem_doctor, null);
			holder = new HolderView();
			holder.listitem_iv_header = (ImageView) convertView.findViewById(R.id.listitem_iv_header);
			holder.listitem_main_title = (TextView) convertView.findViewById(R.id.listitem_main_title);
			holder.listitem_child_title = (TextView) convertView.findViewById(R.id.listitem_child_title);
			convertView.setTag(holder);
		} else {
			holder = (HolderView) convertView.getTag();
		}
		Map<String,String> data=mItemDatas.get(position);
		holder.listitem_iv_header.setBackground(mActivity.getResources().getDrawable(R.drawable.ic_expression_success));
		holder.listitem_main_title.setText(data.get("name"));
		holder.listitem_child_title.setText(data.get("desc"));
		return convertView;
	}
	
	private class HolderView {
		private ImageView listitem_iv_header;
		private TextView listitem_main_title;
		private TextView listitem_child_title;
	}
	
}