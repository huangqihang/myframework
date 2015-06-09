package app.web;

import java.util.List;

import app.common.AppBase;
import demo.datatable.model.DataTableObject;


public abstract class AppBaseController extends AppBase {
	
	/**
	 * 
	 * @return 手动生成DataTable插件需要的Json字符串
	 */
	public <T> String toDataTableJsonTree(List<T> datas) {
		DataTableObject<T> dataTableObject = new DataTableObject<T>();
		dataTableObject.setAaData(datas);
		String json = toJsonTree(dataTableObject);
		return json;
	}
	
	/**
	 * 由spring自动生成json串返回
	 * @param datas
	 * @return
	 */
	public <T> DataTableObject<T> toDataTableObject(List<T> datas) {
		DataTableObject<T> dataTableObject = new DataTableObject<T>();
		dataTableObject.setAaData(datas);
		return dataTableObject;
	}
	
	
	
}
