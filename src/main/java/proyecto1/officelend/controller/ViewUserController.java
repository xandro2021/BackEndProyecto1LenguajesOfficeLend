package proyecto1.officelend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ViewUserController {

    @GetMapping("/catalogo")
    public String userCatalogo() {
        return "usuario/catalogoUser";
    }

    @GetMapping("/historial")
    public String userHistorial() {
        return "usuario/historialSolicitudesUser";
    }

    @GetMapping("/solicitud")
    public String userSolicitud() {
        return "usuario/solicitudPrestamoUser";
    }

}
