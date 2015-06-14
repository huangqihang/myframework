package app.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.common.MIMEType;
import app.entity.Company;
import demo.datatable.repository.DataRepository;

@Controller
public class JsonController {

	// http://localhost/content.json
    @RequestMapping(value="/content", method=RequestMethod.GET, produces=MIMEType.JSON)
    @ResponseBody
    public List<Company> getContent() {
        return DataRepository.GetCompanies();
    }

}