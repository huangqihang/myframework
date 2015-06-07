package app.web.datatable;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.model.Company;
import app.model.DataTableObject;
import app.model.JQueryDataTableParamModel;
import app.model.Person;
import app.repository.DataRepository;
import app.service.DataTableService;
import app.util.DataTablesParamUtility;
import app.web.AppBaseController;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

@Controller
@RequestMapping("/data")
public class DataTableController extends AppBaseController {
	
	@Autowired
	DataTableService service;

	@RequestMapping(value="test/{id}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public DataTableObject<Person> execute(@PathVariable String id) {
		
		List<Person> persons = service.getPersonList();
		
		String json = toDataTableJson(persons);
		System.out.println(json);
		
		DataTableObject<Person> dataTableObject = toDataTableObject(persons);
		
		return dataTableObject;
	}
	
	
	@RequestMapping(value="companyGsonMatrix", method=RequestMethod.GET, 
			produces="application/json")
	@ResponseBody
	public void companyGsonMatrix(HttpServletRequest request, HttpServletResponse response) {
		//http://www.codeproject.com/Articles/359750/jQuery-DataTables-in-Java-Web-Applications
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
