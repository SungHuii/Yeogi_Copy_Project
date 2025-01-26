package copy.project.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ReactRestController {

   @GetMapping("/api/test")
   public String hello() {
      return "테스트입니다.";
   }
}
