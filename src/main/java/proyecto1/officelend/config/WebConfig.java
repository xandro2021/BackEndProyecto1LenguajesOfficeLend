package proyecto1.officelend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  // Clase para crear las carpetas donde voy a subir las imagenes de los articulos
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String uploadDir = Paths.get("uploads/equipment/").toAbsolutePath().toString();
    registry.addResourceHandler("/uploads/equipment/**")
        .addResourceLocations("file:" + uploadDir + "/");
  }
}