package hateoas.res.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import hateoas.domain.Greeting;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/greeting")
    public HttpEntity<Greeting> greeting(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
        greeting.add(linkTo(methodOn(GreetingController.class).greeting2(name)).withRel("www.google.com"));

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

    @RequestMapping("/greetingTwo")
    public HttpEntity<Greeting> greeting2(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}
