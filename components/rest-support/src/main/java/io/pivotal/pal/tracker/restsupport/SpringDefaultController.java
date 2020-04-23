package io.pivotal.pal.tracker.restsupport;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringDefaultController {
	
	@Value("${default.message}")
	private String message;

    @GetMapping("/")
    public String defaultRoute() {
        return message;
    }
}
