package com.example.cupcakestore.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.example.cupcakestore.service.CupcakeService;
import com.example.cupcakestore.service.OrderService;
import com.example.cupcakestore.service.UserService;
import com.example.cupcakestore.model.Order;
import com.example.cupcakestore.model.OrderItem;
import java.util.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final CupcakeService cupcakeService;
    private final OrderService orderService;
    private final UserService userService;
    public OrderController(CupcakeService c, OrderService o, UserService u){ this.cupcakeService=c; this.orderService=o; this.userService=u; }

    @PostMapping("/checkout")
    public String checkout(HttpSession session){
        Object uid = session.getAttribute("userId");
        if(uid==null) return "redirect:/login";
        Map<Long,Integer> cart = (Map<Long,Integer>) session.getAttribute("cart");
        if(cart==null || cart.isEmpty()) return "redirect:/cart";
        Order order = new Order();
        userService.findById((Long)uid).ifPresent(orderUser -> order.user = orderUser);
        for(var e : cart.entrySet()){
            cupcakeService.find(e.getKey()).ifPresent(c -> {
                OrderItem it = new OrderItem(c, e.getValue());
                order.items.add(it);
            });
        }
        orderService.createOrder(order);
        session.removeAttribute("cart");
        return "redirect:/orders/list";
    }

    @GetMapping("/list")
    public String list(HttpSession session, Model m){
        Object uid = session.getAttribute("userId");
        if(uid==null) return "redirect:/login";
        List<com.example.cupcakestore.model.Order> orders = orderService.findByUserId((Long)uid);
        m.addAttribute("orders", orders);
        return "orders/list";
    }
}
