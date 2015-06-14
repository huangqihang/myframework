package app.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.entity.Person;
import app.entity.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 任何类都基类
 * 提供所有业务类都需要的基础功能：日志
 * 
 * MyBaseAction extends Base
 * 
 * MyBaseService extends Base
 * 
 * MyBaseDao extends Base
 */
public abstract class AppBase {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String currentMethod() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	public String toJsonTree(Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return  gson.toJson(obj);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T newInstance(Class<T> clazz) {
		T t = null;
		try {
			String className = clazz.getName();
			t = (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
}
