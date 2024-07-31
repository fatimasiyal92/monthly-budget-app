package com.springmvc.springmvchibernatesample.controller;

import com.springmvc.springmvchibernatesample.entity.Transaction;
import com.springmvc.springmvchibernatesample.service.TransactionService;
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
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/saveTransaction", method = RequestMethod.POST)
    public ModelAndView saveTransaction(@ModelAttribute("command") Transaction transaction,
                                        BindingResult result) {
        transactionService.addTransaction(transaction);
        return new ModelAndView("redirect:/addTransaction.html");
    }

    @RequestMapping(value = "/addTransaction", method = RequestMethod.GET)
    public ModelAndView addTransaction(@RequestParam(defaultValue = "0") int page,
                                       @ModelAttribute("command") Transaction transaction,
                                       BindingResult result) {
        int pageSize = 10;
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("transactions", transactionService.getAllTransactions());
        List<Transaction> transactionPages = transactionService.findPaginatedTransactions(page, pageSize);
        model.put("transactionPages", transactionPages);
        model.put("currentTransactionPage", page);
        model.put("totalTransactionPages", pageSize);
        model.put("transaction", new Transaction());
        return new ModelAndView("addTransaction", model);
    }

    @RequestMapping(value = "/deleteTransaction", method = RequestMethod.GET)
    public ModelAndView deleteTransaction(@RequestParam("transactionId") String transactionId,
                                          @ModelAttribute("command") Transaction transaction,
                                          BindingResult result) {
        transactionService.deleteTransaction(Integer.parseInt(transactionId));
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("transactions", transactionService.getAllTransactions());
        return new ModelAndView("addTransaction", model);
    }

    @RequestMapping(value = "/editTransaction", method = RequestMethod.GET)
    public ModelAndView editTransaction(@RequestParam("transactionId") String transactionId,
                                        @ModelAttribute("command") Transaction transaction,
                                        BindingResult result) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("transaction", transactionService.getTransaction(Integer.parseInt(transactionId)));
        model.put("transactions", transactionService.getAllTransactions());
        return new ModelAndView("addTransaction", model);
    }

    @RequestMapping(value="/transactions", method = RequestMethod.GET)
    public List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }
}