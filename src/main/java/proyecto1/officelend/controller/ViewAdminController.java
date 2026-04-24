package proyecto1.officelend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
  public String agregarEquipo() {
    return "admin/agregarItemAdmin";
  }

  @GetMapping("/prestamos")
  public String gestionPrestamos() {
    return "admin/gestionPrestamosAdmin";
  }

  @GetMapping("/prestamos/detalle/{id}")
  public String verDetallePrestamo(@PathVariable int id, Model model) {
    model.addAttribute("prestamoId", id);
    return "admin/verSolicitudAdmin";
  }

}