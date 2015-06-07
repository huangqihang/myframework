package app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/data")
public class DataTableController {

	@RequestMapping(value="test", method=RequestMethod.GET)
	public String execute() {
		return "datatable";
	}
	
	
}
