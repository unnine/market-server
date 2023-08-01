package com.market.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/view/seller", method = RequestMethod.GET)
public class SellerViewController {

    @RequestMapping(value = "/sign-up")
    public String signUpPage() {
        return "member/SellerSignUp";
    }

}