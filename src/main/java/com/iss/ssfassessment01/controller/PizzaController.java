package com.iss.ssfassessment01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.iss.ssfassessment01.model.Contact;
import com.iss.ssfassessment01.model.PizzaOrder;
import com.iss.ssfassessment01.service.PizzaService;

import jakarta.validation.Valid;

@Controller
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;
    
    @GetMapping(path="/")
    public String contactForm(Model model){
        model.addAttribute("pizzaorder", new PizzaOrder());
        return "index";
    }

    @PostMapping("/pizza")
    public String savePizza(@Valid PizzaOrder pizzaOrder, BindingResult result, 
    Model model){
        if(result.hasErrors()){
            return "index";
        }
        model.addAttribute("pizzaorder", pizzaOrder);
        return "pizza";
    }

    @PostMapping("/pizza/order")
    public String saveContact(@Valid Contact contact, BindingResult result, 
    Model model){
        if(result.hasErrors()){
            return "pizza";
        }
        pizzaService.save(contact);
        model.addAttribute("contacts", contact);
        return "order";
    }

    @GetMapping(path="/{orderid}")
    public String getContactInfoById(Model model, 
            @PathVariable(value="orderid") String orderid){
        Contact ctc = pizzaService.findById(orderid);
        ctc.setOrderid(orderid);
        model.addAttribute("contact", ctc);
        // if (ctc == null) {
        //     return ResponseEntity
        //             .status(HttpStatus.NOT_FOUND)
        //             .contentType(MediaType.APPLICATION_JSON)
        //             .body("");
        // }
        return "showContact";
    }
}
