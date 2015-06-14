package demo.datatable.web;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.common.MIMEType;
import app.entity.Company;
import app.entity.Person;
import app.web.AppBaseController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import demo.datatable.model.DataTableObject;
import demo.datatable.model.JQueryDataTableParamModel;
import demo.datatable.repository.DataRepository;
import demo.datatable.service.DataTableService;
import demo.datatable.util.DataTablesParamUtility;

@Controller
@RequestMapping("/data")
public class DataTableController extends AppBaseController {
	
	@Autowired
	DataTableService service;

	/**
	 * 测试-返回JSON
	 */
	@RequestMapping(value="test/{id}", method=RequestMethod.GET, produces=MIMEType.JSON)
	@ResponseBody
	public DataTableObject<Person> execute(@PathVariable String id) {
		
		List<Person> persons = service.getPersonList();
		
		String json = toDataTableJsonTree(persons);
		System.out.println(json);
		
		DataTableObject<Person> dataTableObject = toDataTableObject(persons);
		
		return dataTableObject;
	}
	
	/**
	 * 1次性加载所有数据到客户端，分页操作在客户端进行处理
	 * 好处：客户端只请求1次，减少请求服务端的次数
	 * 适用场景：数据量不大的时候，直接将数据都放在客户端进行分页处理
	 * 
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(Model model) {
		
		model.addAttribute("companies", DataRepository.GetCompanies());
		
		return jsplView("datatable", "index");
	}
	
	
	@RequestMapping(value="plugins", method=RequestMethod.GET)
	public String plugins(Model model) {
		
		model.addAttribute("companies", DataRepository.GetCompanies());
		
		return jsplView("datatable", "plugins");
	}
	
	@RequestMapping(value="indexHtml", method=RequestMethod.GET)
	public String indexHtml() {
		return htmlView("datatable", "index");
	}
	
	@RequestMapping(value="objectsHtml", method=RequestMethod.GET)
	public String objectsHtml() {
		return htmlView("datatable", "objects");
	}
	
	
	
	/**
	 * huge amount of data, using DataTables in AJAX mode 
	 * 返回矩阵类型JSON串-{[],[]}
	 */
	@RequestMapping(value="companyGsonMatrix", method=RequestMethod.GET, 
			produces=MIMEType.JSON)
	@ResponseBody
	public void companyGsonMatrix(HttpServletRequest request, HttpServletResponse response) {
		try {

			JQueryDataTableParamModel param = DataTablesParamUtility.getParam(request);
			
			String sEcho = param.sEcho;
			int iTotalRecords; // total number of records (unfiltered)
			int iTotalDisplayRecords; //value will be set when code filters companies by keyword
			JsonArray data = new JsonArray(); //data that will be shown in the table

			iTotalRecords = DataRepository.GetCompanies().size();
			List<Company> companies = new LinkedList<Company>();
			for(Company c : DataRepository.GetCompanies()){
				if(	c.getName().toLowerCase().contains(param.sSearch.toLowerCase())
					||
					c.getAddress().toLowerCase().contains(param.sSearch.toLowerCase())
					||
					c.getTown().toLowerCase().contains(param.sSearch.toLowerCase()))
				{
					companies.add(c); // add company that matches given search criterion
				}
			}
			iTotalDisplayRecords = companies.size(); // number of companies that match search criterion should be returned
			
			final int sortColumnIndex = param.iSortColumnIndex;
			final int sortDirection = param.sSortDirection.equals("asc") ? 1 : -1;
			
			Collections.sort(companies, new Comparator<Company>(){
				@Override
				public int compare(Company c1, Company c2) {	
					switch(sortColumnIndex){
					case 0:
						return c1.getName().compareTo(c2.getName()) * sortDirection;
					case 1:
						return c1.getAddress().compareTo(c2.getAddress()) * sortDirection;
					case 2:
						return c1.getTown().compareTo(c2.getTown()) * sortDirection;
					}
					return 0;
				}
			});
			
			if(companies.size()< param.iDisplayStart + param.iDisplayLength) {
				companies = companies.subList(param.iDisplayStart, companies.size());
			} else {
				companies = companies.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
			}
		
			try {
				JsonObject jsonResponse = new JsonObject();			
				jsonResponse.addProperty("sEcho", sEcho);
				jsonResponse.addProperty("iTotalRecords", iTotalRecords);
				jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
				
				for(Company c : companies){
					JsonArray row = new JsonArray();
					row.add(new JsonPrimitive(c.getName()));
					row.add(new JsonPrimitive(c.getAddress()));
					row.add(new JsonPrimitive(c.getTown()));
					data.add(row);
				}
				jsonResponse.add("aaData", data);
				
				response.setContentType("application/Json");
				response.getWriter().print(jsonResponse.toString());
				
				logger.info(jsonResponse.toString());
				
			} catch (JsonIOException e) {
				e.printStackTrace();
				response.setContentType("text/html");
				response.getWriter().print(e.getMessage());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 返回名值对的JSON串-key:value
	 */
	@RequestMapping(value="companyGsonObjects", method=RequestMethod.GET, 
			produces=MIMEType.JSON)
	@ResponseBody
	public void companyGsonObjects(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			JQueryDataTableParamModel param = DataTablesParamUtility.getParam(request);
			
			String sEcho = param.sEcho;
			int iTotalRecords; // total number of records (unfiltered)
			int iTotalDisplayRecords; //value will be set when code filters companies by keyword

			iTotalRecords = DataRepository.GetCompanies().size();
			List<Company> companies = new LinkedList<Company>();
			for(Company c : DataRepository.GetCompanies()){
				if(	c.getName().toLowerCase().contains(param.sSearch.toLowerCase())
					||
					c.getAddress().toLowerCase().contains(param.sSearch.toLowerCase())
					||
					c.getTown().toLowerCase().contains(param.sSearch.toLowerCase()))
				{
					companies.add(c); // add company that matches given search criterion
				}
			}
			iTotalDisplayRecords = companies.size();// number of companies that match search criterion should be returned
			
			final int sortColumnIndex = param.iSortColumnIndex;
			final int sortDirection = param.sSortDirection.equals("asc") ? -1 : 1;
			
			Collections.sort(companies, new Comparator<Company>(){
				@Override
				public int compare(Company c1, Company c2) {	
					switch(sortColumnIndex){
					case 0:
						return c1.getName().compareTo(c2.getName()) * sortDirection;
					case 1:
						return c1.getAddress().compareTo(c2.getAddress()) * sortDirection;
					case 2:
						return c1.getTown().compareTo(c2.getTown()) * sortDirection;
					}
					return 0;
				}
			});
			
			if(companies.size()< param.iDisplayStart + param.iDisplayLength) {
				companies = companies.subList(param.iDisplayStart, companies.size());
			} else {
				companies = companies.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
			}
		
			try {	
				JsonObject jsonResponse = new JsonObject();		
				jsonResponse.addProperty("sEcho", sEcho);
				jsonResponse.addProperty("iTotalRecords", iTotalRecords);
				jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);			
				Gson gson = new Gson();
				jsonResponse.add("aaData", gson.toJsonTree(companies));
				
				response.setContentType("application/Json");
				response.getWriter().print(jsonResponse.toString());
				
				logger.info(jsonResponse.toString());
			} catch (JsonIOException e) {
				e.printStackTrace();
				response.setContentType("text/html");
				response.getWriter().print(e.getMessage());
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
