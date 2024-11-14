package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.ApiResponse.ApiResponse;
import com.example.bankmanagementsystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank/management")
public class BankManagementController {

    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer) {
        customers.add(customer);

        return new ApiResponse("Customer Added");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer) {
        customers.set(index, customer);

        return new ApiResponse("Customer Updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        customers.remove(index);

        return new ApiResponse("Customer Deleted");
    }

    @PutMapping("/deposit/{amount}/{id}")
    public ApiResponse deposit(@PathVariable double amount, @PathVariable String id) {
        //customer.setBalance(customer.getBalance() + amount);
        for (Customer c : customers) {
            if (c.getId().equals(id)) {
                c.setBalance(c.getBalance() + amount);
                return new ApiResponse("Deposit Successful");
            }
        }
        return new ApiResponse("Deposit unSuccessful");
    }


   @PutMapping("/withdraw/{amount}/{id}")
    public ApiResponse withdraw(@PathVariable double amount, @PathVariable String id) {
        for (Customer c : customers) {
            if (c.getId().equals(id))
                if (c.getBalance() >= amount) {
                    c.setBalance(c.getBalance() - amount);
                    return new ApiResponse("Withdrawal Successful");
                }
        }
        return new ApiResponse("Withdrawal Unsuccessful");
    }


}
