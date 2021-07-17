package io.cloud.gateway;

import org.springframework.web.bind.annotation.RequestMapping;

public class FallbackController {

	@RequestMapping("/empDeatilsFallBack")
	public String empDeatilsFallBack() {
		return "Order Service is taking too long to respond or is down. Please try again later";
	}

	
}
