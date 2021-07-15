package ci.gstoreplus.dashboard.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import ci.gstoreplus.entity.client.Hello;
import ci.gstoreplus.entity.client.User;



@Controller
public class WebController {

	@MessageMapping("/hello")
	@SendTo("/topic/hi")
	public Hello greeting(User user) throws Exception {
		return new Hello("Hi, " + user.getName() + "!");
	}
}
