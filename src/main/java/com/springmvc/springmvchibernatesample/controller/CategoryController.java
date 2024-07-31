package com.springmvc.springmvchibernatesample.controller;

import com.springmvc.springmvchibernatesample.entity.Category;
import com.springmvc.springmvchibernatesample.service.CategoryService;
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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public ModelAndView saveCategory(@ModelAttribute("command") Category category,
                                     BindingResult result) {
        categoryService.addCategory(category);
        return new ModelAndView("redirect:/addCategory.html");
    }
    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public ModelAndView addCategory(@RequestParam(defaultValue = "0") int page,
                                    @ModelAttribute("command")  Category category,
                                    BindingResult result) {
        int pageSize = 10;
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("categories",  categoryService.getAllCategories());
        List<Category> categoryPages = categoryService.findPaginatedCategories(page, pageSize);
        model.put("categoryPages",  categoryPages);
        model.put("currentCategoryPage", page);
        model.put("totalCategoryPages", pageSize);
        model.put("category",  new Category());
        return new ModelAndView("addCategory", model);
    }
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@RequestParam("categoryId")String categoryId,
                                       @ModelAttribute("command")  Category category,
                                       BindingResult result) {
        categoryService.deleteCategory(Integer.parseInt(categoryId));
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("categories",  categoryService.getAllCategories());
        return new ModelAndView("addCategory", model);
    }
    @RequestMapping(value = "/editCategory", method = RequestMethod.GET)
    public ModelAndView editCategory(@RequestParam("categoryId")String categoryId, @ModelAttribute("command")  Category category,
                                     BindingResult result) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("category",  categoryService.getCategory(Integer.parseInt(categoryId)));
        model.put("categories",  categoryService.getAllCategories());
        return new ModelAndView("addCategory", model);
    }
    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }
}