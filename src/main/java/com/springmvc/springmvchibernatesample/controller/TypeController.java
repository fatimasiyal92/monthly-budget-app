package com.springmvc.springmvchibernatesample.controller;

import com.springmvc.springmvchibernatesample.entity.Type;
import com.springmvc.springmvchibernatesample.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TypeController {
	
	@Autowired
	private TypeService typeService;
	
	@RequestMapping(value = "/saveType", method = RequestMethod.POST)
	public ModelAndView saveType(@ModelAttribute("command") Type type,
								   BindingResult result) {
		typeService.addType(type);
		return new ModelAndView("redirect:/addType.html");
	}
	@RequestMapping(value = "/addType", method = RequestMethod.GET)
	public ModelAndView addType(@RequestParam(defaultValue = "0") int page,
								@ModelAttribute("command")  Type type,
								BindingResult result) {
		int pageSize = 10;
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("types",  typeService.getAllTypes());
		List<Type> typePages = typeService.findPaginatedTypes(page, pageSize);
		model.put("typePages",  typePages);
		model.put("currentTypePage", page);
		model.put("totalTypePages", pageSize);
		model.put("type",  new Type());
		return new ModelAndView("addType", model);
	}
	@RequestMapping(value = "/deleteType", method = RequestMethod.GET)
	public ModelAndView deleteType(@RequestParam("typeId")String typeId,
								   @ModelAttribute("command")  Type type,
			BindingResult result) {
		typeService.deleteType(Integer.parseInt(typeId));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("types",  typeService.getAllTypes());
		return new ModelAndView("addType", model);
	}
	@RequestMapping(value = "/editType", method = RequestMethod.GET)
	public ModelAndView editType(@RequestParam("typeId")String typeId, @ModelAttribute("command")  Type type,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("type",  typeService.getType(Integer.parseInt(typeId)));
		model.put("types",  typeService.getAllTypes());
		return new ModelAndView("addType", model);
	}
	@RequestMapping(value="/types", method = RequestMethod.GET)
	public List<Type> getTypes() {
		return typeService.getAllTypes();
	}
}
