package at.spengergasse.legado.presentation.restapi;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class HelloWorldController {

    @GetMapping(path = "/hello/{identifier}") // muss ein get Request auf /hello/{identifier} sein
    public HelloWorld helloWorld(@PathVariable String identifier) {
       /* var helloWorld = new HelloWorld();
        helloWorld.setMessage("Hello World: "+ identifier);*/
        var helloWorld = new HelloWorld("Hello World: "+ identifier);
        return helloWorld;
    }


    //Jacoco zeigt mir an welche Bereichen getestet sind und welche noch getestet werden sollen
    @PostMapping("/xx")
    public HelloWorld newMessage(@RequestBody HelloWorld helloWorld){ return helloWorld; }

    /*public static class HelloWorld {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }*/
}



