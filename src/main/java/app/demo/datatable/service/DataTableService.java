package app.demo.datatable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.demo.datatable.repository.DataTableDao;
import app.model.Person;

@Service
public class DataTableService {
	
	@Autowired
	DataTableDao dao;
	
	public List<Person> getPersonList() {
		return dao.getPersonList();
	}
	
}
