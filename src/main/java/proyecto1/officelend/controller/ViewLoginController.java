package proyecto1.officelend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewLoginController {

  @GetMapping("/")
  public String home() {
    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "index";
  }

  @GetMapping("/admin/catalogo")
  public String adminCatalogo() {
    return "admin/catalogoAdmin";
  }

  @GetMapping("/user/catalogo")
  public String userCatalogo() {
    return "usuario/catalogoUser";
  }
}