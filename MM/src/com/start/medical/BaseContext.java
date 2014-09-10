package com.start.medical;

import start.core.AppContext;

import com.start.core.Constant;



/**
 * 
 * @author Start   
 * @Description: 全局application 
 * @ClassName: AppContext.java   
 * @date 2014年6月30日 上午9:08:15      
 * @说明  代码版权归 杭州反盗版中心有限公司 所有
 */
public class BaseContext extends AppContext {

	public Boolean isTestEnvironmental() {
		return Constant.ISSYSTEMTEST;
	}

	public String getServerURL() {
		return Constant.RESTURL;
	}

}
