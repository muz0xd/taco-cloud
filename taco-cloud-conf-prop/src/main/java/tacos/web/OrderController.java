package tacos.web;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	  
	private OrderRepository orderRepo;
	
	private OrderProps props;

	public OrderController(OrderRepository orderRepo, OrderProps props) {
		this.orderRepo = orderRepo;
		this.props = props;
	}
  
	@GetMapping("/current")
	public String orderForm(@AuthenticationPrincipal User user, 
	        @ModelAttribute Order order) {
	    if (order.getName() == null) {
	        order.setName(user.getFullname());
	    }
	    if (order.getStreet() == null) {
	        order.setStreet(user.getStreet());
	    }
	    if (order.getCity() == null) {
	        order.setCity(user.getCity());
	    }
	    if (order.getState() == null) {
	        order.setState(user.getState());
	    }
	    if (order.getZip() == null) {
	        order.setZip(user.getZip());
	    }
	      
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, 
			SessionStatus sessionStatus,
			@AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		
		order.setUser(user);
    
		orderRepo.save(order);
		sessionStatus.setComplete();
    
		return "redirect:/";
	}

}
