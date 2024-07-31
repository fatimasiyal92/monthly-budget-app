package com.springmvc.springmvchibernatesample.controller;

import com.springmvc.springmvchibernatesample.entity.TransactionDetail;
import com.springmvc.springmvchibernatesample.service.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TransactionDetailController {

    @Autowired
    private TransactionDetailService transactionDetailService;

//    @RequestMapping(value = "/saveTransactionDetail", method = RequestMethod.POST)
//    public ModelAndView saveTransactionDetail(@ModelAttribute("command") TransactionDetail transactionDetail,
//                                              BindingResult result) {
//
//        transactionDetailService.addTransactionDetail(transactionDetail);
//        return new ModelAndView("redirect:/addTransactionDetail.html");
//    }

//    @RequestMapping(value = "/saveTransactionDetail", method = RequestMethod.POST)
//    public ModelAndView saveTransactionDetail(@ModelAttribute("command") TransactionDetail transactionDetail,
//                                              BindingResult result) {
//        System.out.println("Received TransactionDetail: " + transactionDetail);
//        System.out.println("Transaction Date: " + transactionDetail.getTransactionDate());
//        transactionDetail.setTransactionDate(LocalDate.of(2022, 7, 1));
//        transactionDetailService.addTransactionDetail(transactionDetail);
//        return new ModelAndView("redirect:/addTransactionDetail.html");
//    }

    @RequestMapping(value = "/saveTransactionDetail", method = RequestMethod.POST)
    public ModelAndView saveTransactionDetail(@ModelAttribute("command") TransactionDetail transactionDetail,
                                              BindingResult result) {
        System.out.println("Received TransactionDetail: " + transactionDetail);

        // Set the transaction date to the current date
//        LocalDate currentDate = LocalDate.now();
//        transactionDetail.setTransactionDate(currentDate);

        System.out.println("Transaction Date: " + transactionDetail.getTransactionDate());

        transactionDetailService.addTransactionDetail(transactionDetail);
        return new ModelAndView("redirect:/addTransactionDetail.html");
    }


    @RequestMapping(value = "/addTransactionDetail", method = RequestMethod.GET)
    public ModelAndView addTransactionDetail(@RequestParam(defaultValue = "0") int page,
                                             @ModelAttribute("command") TransactionDetail transactionDetail,
                                             BindingResult result) {
        int pageSize = 10;
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("transactionDetails", transactionDetailService.getAllTransactionDetails());
        List<TransactionDetail> transactionDetailPages = transactionDetailService.findPaginatedTransactionDetails(page, pageSize);
        model.put("transactionDetailPages", transactionDetailPages);
        model.put("currentTransactionDetailPage", page);
        model.put("totalTransactionDetailPages", pageSize);
        model.put("transactionDetail", new TransactionDetail());
        return new ModelAndView("addTransactionDetail", model);
    }

    @RequestMapping(value = "/deleteTransactionDetail", method = RequestMethod.GET)
    public ModelAndView deleteTransactionDetail(@RequestParam("transactionDetailId") String transactionDetailId,
                                                @ModelAttribute("command") TransactionDetail transactionDetail,
                                                BindingResult result) {
        transactionDetailService.deleteTransactionDetail(Integer.parseInt(transactionDetailId));
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("transactionDetails", transactionDetailService.getAllTransactionDetails());
        return new ModelAndView("addTransactionDetail", model);
    }

    @RequestMapping(value = "/editTransactionDetail", method = RequestMethod.GET)
    public ModelAndView editTransactionDetail(@RequestParam("transactionDetailId") String transactionDetailId,
                                              @ModelAttribute("command") TransactionDetail transactionDetail,
                                              BindingResult result) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("transactionDetail", transactionDetailService.getTransactionDetail(Integer.parseInt(transactionDetailId)));
        model.put("transactionDetails", transactionDetailService.getAllTransactionDetails());
        return new ModelAndView("addTransactionDetail", model);
    }

    @RequestMapping(value="/transactionDetails", method = RequestMethod.GET)
    public List<TransactionDetail> getTransactionDetails() {
        return transactionDetailService.getAllTransactionDetails();
    }
}