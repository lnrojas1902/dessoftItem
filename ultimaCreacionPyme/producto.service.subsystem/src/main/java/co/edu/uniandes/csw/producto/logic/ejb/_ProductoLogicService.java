
package co.edu.uniandes.csw.producto.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.logic.api._IProductoLogicService;
import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

public abstract class _ProductoLogicService implements _IProductoLogicService {
        public static String URL_SERVICIO = "http://10.0.2.122:8080/producto.service.subsystem.web";
@Inject
protected IProductoPersistence persistance;
        
         @PostConstruct
    public void initService() {
        Properties prop = new Properties();
        OutputStream output = null;
        //Lee del archivo de propiedades de glassfish/domains/domain1/config
        try {
            File file = new File("services.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();
            URL_SERVICIO = properties.getProperty("url");
        } catch (Exception e) {
            //Sin no existe lo crea por defecto
            try {
                output = new FileOutputStream("services.properties");
                prop.setProperty("url", URL_SERVICIO);
                prop.store(output, null);
                output.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
 
        }
    }
        
public ProductoDTO createProducto(ProductoDTO producto){
return persistance.createProducto( producto); 
    }

public List<ProductoDTO> getProductos(){
try {
            Client client = Client.create();
            //SE CONSUME EL SERVICIO REMOTO DE PRODUCTOS getProductos
            WebResource webResource = client.resource(URL_SERVICIO + "/webresources/Producto");
            // Se instancia un nuevo object mapper para convertir el string JSON a un DTO
            ObjectMapper map = new ObjectMapper();
            //Se convierte el String a un DTO y luego se retorna
            String resp = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);
            //Se convierte a una lista de DTO con ayuda de jackson y se retrona.
            List<ProductoDTO> dto = map.readValue(resp, TypeFactory.defaultInstance().constructCollectionType(List.class, ProductoDTO.class));
            return dto;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null; 
//    return persistance.getProductos();
}

public ProductoDTO getProducto(Long id){
try {
            //Se crea un cliente apache
            Client client = Client.create();
            /**
             * SE CONSUME EL SERVICIO REMOTO DE PRODUCTOS getProducto.
             * URL_SERVICIO tiene la ubicación de la aplicación de proveedores
             * (que se carga de service.properties)
             */
            WebResource webResource = client.resource(URL_SERVICIO + "/webresources/Producto/"+ id);
            // Se instancia un nuevo object mapper para convertir el string JSON a un DTO
            ObjectMapper map = new ObjectMapper();
            //Se obtiene el string JSON por medio de get
            String resp = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);
            //Se convierte el String a un DTO con ayuda de jackson y luego se retorna
            ProductoDTO dto = map.readValue(resp, ProductoDTO.class);
            return dto;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
//    return persistance.getProducto(id);
}

public void deleteProducto(Long id){
   persistance.deleteProducto(id); 
}

public void updateProducto(ProductoDTO producto){
   persistance.updateProducto(producto); 
}
}