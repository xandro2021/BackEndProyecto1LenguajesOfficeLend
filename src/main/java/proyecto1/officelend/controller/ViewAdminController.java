package proyecto1.officelend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class ViewAdminController {

  @GetMapping("/dashboard")
  public String adminDashboard() {
    return "admin/dashboardAdmin";
  }

  @GetMapping("/catalogo")
  public String adminCatalogo() {
    return "admin/catalogoAdmin";
  }

  @GetMapping("/catalogo/nuevo")
  public String agregarEquipo(Model model) {
    model.addAttribute("modo", "agregar");
    return "admin/agregarItemAdmin";
  }

  @GetMapping("/catalogo/editar/{id}")
  public String editarEquipo(Model model) {
    model.addAttribute("modo", "editar");
    return "admin/agregarItemAdmin";
  }

  @GetMapping("/prestamos")
  public String gestionPrestamos() {
    return "admin/gestionPrestamosAdmin";
  }

  @GetMapping("/prestamos/detalle/{id}")
  public String verDetallePrestamo() {
    return "admin/verSolicitudAdmin";
  }
}