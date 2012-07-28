package org.buddy.charity;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class ResultController {
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String showresult(Locale locale, Model model, HttpSession session) {
		Map<String, Object> map = model.asMap();
		return "result";
	}
}
