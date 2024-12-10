package com.javaweb.controller.admin;
import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.statusType;
import com.javaweb.enums.transactionType;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.impl.CustomerService;
import com.javaweb.service.impl.TransactionService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController(value="customerControllerOfAdmin")
public class CustomerController {
    @Autowired
    private MessageUtils messageUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionService transactionService;
    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView customerList(@ModelAttribute("modelSearch") CustomerSearchRequest customerSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("staffs", userService.getListStaff());
        mav.addObject("modelSearch", customerSearchRequest);
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            customerSearchRequest.setStaffId(staffId);
            mav.addObject("customers", customerService.findCustomers(customerSearchRequest));
        } else {
            mav.addObject("customers", customerService.findCustomers(customerSearchRequest));
        }
        CustomerSearchResponse model = new CustomerSearchResponse();
        DisplayTagUtils.of(request, model);
        List<CustomerSearchResponse> customerDTOs = customerService.getAllCustomers(customerSearchRequest, PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(customerDTOs);
        model.setTotalItems(customerService.findCustomers(customerSearchRequest).size());
        mav.addObject("model", model);
        initMessageResponse(mav, request);
        return mav;
    }
    //add a customer
    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView addCustomer(@ModelAttribute("customerEdit") CustomerDTO customerDTO){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("status", statusType.statusType());
        return mav;
    }
    //update a customer
    @RequestMapping(value = "/admin/customer-edit-{id}", method = RequestMethod.GET)
    public ModelAndView updateCustomer(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO model = customerService.findByID(id);
        mav.addObject("transactionType", transactionType.transactionType());
        mav.addObject("status", statusType.statusType());
        mav.addObject("customerEdit",model);
        mav.addObject("CSKH",transactionService.findByCode("CSKH",id));
        mav.addObject("DDX",transactionService.findByCode("DDX",id));
        return mav;
    }
    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtil.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
