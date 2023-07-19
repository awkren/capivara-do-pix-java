package pix.capivara.capivaradopix;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Server {

  @GetMapping("/")
  public String getHome() {
    return "kkkkk get";
  }

}
