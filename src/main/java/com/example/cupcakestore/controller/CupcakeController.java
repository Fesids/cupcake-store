package com.example.cupcakestore.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.cupcakestore.service.CupcakeService;
import com.example.cupcakestore.model.Cupcake;
import java.math.BigDecimal;

@Controller
@RequestMapping("/cupcakes")
public class CupcakeController {
    private final CupcakeService svc;
    public CupcakeController(CupcakeService svc){ this.svc = svc; }

    @GetMapping
    public String list(Model m){
        m.addAttribute("cupcakes", svc.listAll());
        return "cupcakes/list";
    }

    @GetMapping("/new")
    public String form(Model m){
        m.addAttribute("cupcake", new Cupcake());
        return "cupcakes/form";
    }

    @PostMapping
    public String save(@RequestParam String name,
                       @RequestParam(required=false) String description,
                       @RequestParam String price){
        Cupcake c = new Cupcake();
        c.name = name;
        c.description = description;
        c.price = new BigDecimal(price);
        svc.save(c);
        return "redirect:/cupcakes";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model m){
        var oc = svc.find(id);
        if(oc.isEmpty()) return "redirect:/cupcakes";
        m.addAttribute("cupcake", oc.get());
        return "cupcakes/view";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model m){
        var oc = svc.find(id);
        if(oc.isEmpty()) return "redirect:/cupcakes";
        m.addAttribute("cupcake", oc.get());
        return "cupcakes/form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        svc.delete(id);
        return "redirect:/cupcakes";
    }
}
