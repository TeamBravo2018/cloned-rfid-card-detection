package com.cit.restapi.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by odziea on 11/12/2018.
 */
@Controller
@RequestMapping("/")
public class SwaggerController
{
    @GetMapping("/")
    public String swagger()
    {
        return "redirect:/swagger-ui.html";
    }

}