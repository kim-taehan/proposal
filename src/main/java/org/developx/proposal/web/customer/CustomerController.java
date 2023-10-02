package org.developx.proposal.web.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.math.raw.Mod;
import org.developx.proposal.domain.customer.data.enums.CustomerType;
import org.developx.proposal.domain.customer.data.response.FindCustomersResponse;
import org.developx.proposal.domain.customer.service.CustomerService;
import org.developx.proposal.domain.project.data.enums.DocumentType;
import org.developx.proposal.domain.user.data.UserForm;
import org.developx.proposal.web.customer.data.CreateCustomerForm;
import org.developx.proposal.web.customer.data.CustomersForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("")
    public String customers(Model model, CustomersForm customersForm) {
        Page<FindCustomersResponse> customers = customerService.findCustomers(
                customersForm.toFindCustomerRequest(),
                Pageable.ofSize(10)
        );

        model.addAttribute("customerTypes", CustomerType.values());
        model.addAttribute("customers", customers);
        return "customers/customers";
    }

    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("createCustomerForm", CreateCustomerForm.getInstance());
        model.addAttribute("customerTypes", CustomerType.values());
        return "customers/createCustomerForm";
    }

    @PostMapping("new")
    public String create(@Valid CreateCustomerForm createCustomerForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("customerTypes", CustomerType.values());
            return "customers/createCustomerForm";
        }
        customerService.createCustomer(createCustomerForm.toCreateCustomerRequest());
        return "redirect:/customers";
    }
}
