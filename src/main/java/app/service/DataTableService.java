package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Person;
import app.repository.DataTableDao;

@Service
public class DataTableService {
	
	@Autowired
	DataTableDao dao;
	
	public List<Person> getPersonList() {
		return dao.getPersonList();
	}
	
}
