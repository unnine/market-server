package com.market.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/view/customer", method = RequestMethod.GET)
public class CustomerViewController {

    @RequestMapping("/sign-up")
    public String signUpPage() {
        return "member/CustomerSignUp";
    }

}