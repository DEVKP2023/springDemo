package demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	
	// URL which works: http://localhost:8080/app/hello
	@GetMapping("/hello")
	public String helloGFG()
    {
        return "Hello GeeksForGeeks";
    }
	
}
