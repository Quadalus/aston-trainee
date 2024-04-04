package hw5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bikkul.helloworld.HelloWorld;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    private final HelloWorld helloWorld;

    public HelloWorldController(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    @GetMapping
    public String getHelloWorld() {
        return helloWorld.getHelloWorld();
    }
}
