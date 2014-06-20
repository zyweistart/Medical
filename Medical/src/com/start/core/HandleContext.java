package com.start.core;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Handle容器处理类
 * @author start
 *
 */
public class HandleContext {
	
	private Context mContext;
	private HandleContextListener mListener;
	
	public HandleContext(Context context){
		this.mContext=context;
	}

	public void setListener(HandleContextListener mListener) {
		this.mListener = mListener;
	}

	public void makeTextShort(String text) {
		if(!TextUtils.isEmpty(text)){
			sendMessage(Toast.LENGTH_SHORT,text);
		}
	}

	public void makeTextLong(String text) {
		if(!TextUtils.isEmpty(text)){
			sendMessage(Toast.LENGTH_LONG,text);
		}
	}
	
	public void sendMessage(int what, Object obj) {
		Message msg = new Message();
		msg.what = what;
		msg.obj = obj;
		sendMessage(msg);
	}
	
	public void sendMessage(Message msg) {
		handler.sendMessage(msg);
	}

	public void sendEmptyMessage(int what) {
		handler.sendEmptyMessage(what);
	}
	
	public Handler handler = new Handler() {
		
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case Toast.LENGTH_SHORT:
				Toast.makeText(mContext, String.valueOf(msg.obj), Toast.LENGTH_SHORT).show();
				break;
			case Toast.LENGTH_LONG:
				Toast.makeText(mContext, String.valueOf(msg.obj), Toast.LENGTH_LONG).show();
				break;
			default:
				if(mListener!=null){
					mListener.onProcessMessage(msg);
				}
				break;
			}
		}
		
	};

	public interface HandleContextListener{
		
		public void onProcessMessage(Message msg);
		
	}
	
}