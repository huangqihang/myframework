package demo.dwr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import app.web.AppBaseController;

@Controller
@RequestMapping("/dwr2")
public class DWRController extends AppBaseController {

	@RequestMapping("{view}")
	public String execute(@PathVariable String view) {
		return htmlView("dwr", view);
	}

	@RequestMapping("{path}/{view}")
	public String simple(@PathVariable String path, @PathVariable String view) {
		return  htmlView("dwr/"+path, view);
	}

}
