package io.cloud.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

	@RequestMapping("/empDeatilsFallBack")
	public String empDeatilsFallBack() {
		return "Order Service is taking too long to respond or is down. Please try again later";
	}

	@RequestMapping("/empInfoFallBack")
	public String empInfoFallBack() {
		return "Order Service is taking too long to respond or is down. Please try again later";
	}
	
	@RequestMapping("/empSalaryFallBack")
	public String empSalaryFallBack() {
		return "Order Service is taking too long to respond or is down. Please try again later";
	}
	
}
