package com.example.cupcakestore.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.example.cupcakestore.service.CupcakeService;
import com.example.cupcakestore.model.Cupcake;
import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CupcakeService svc;
    public CartController(CupcakeService svc){ this.svc = svc; }

    @GetMapping
    public String viewCart(HttpSession session, Model m){
        Map<Long,Integer> cart = (Map<Long,Integer>) session.getAttribute("cart");
        if(cart==null) cart = new HashMap<>();
        List<Map<String,Object>> items = new ArrayList<>();
        for(var e : cart.entrySet()){
            svc.find(e.getKey()).ifPresent(c -> {
                Map<String,Object> it = new HashMap<>();
                it.put("cupcake", c);
                it.put("qty", e.getValue());
                items.add(it);
            });
        }
        m.addAttribute("items", items);
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id, @RequestParam(defaultValue = "1") int qty, HttpSession session){
        Map<Long,Integer> cart = (Map<Long,Integer>) session.getAttribute("cart");
        if(cart==null) cart = new HashMap<>();
        cart.put(id, cart.getOrDefault(id,0)+qty);
        session.setAttribute("cart", cart);
        return "redirect:/cupcakes";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable Long id, HttpSession session){
        Map<Long,Integer> cart = (Map<Long,Integer>) session.getAttribute("cart");
        if(cart!=null) cart.remove(id);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }
}
