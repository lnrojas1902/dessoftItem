
package co.edu.uniandes.csw.producto.logic.ejb;

import co.edu.uniandes.csw.producto.logic.dto.ClienteAndItemsDTO;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.producto.logic.api.IProductoLogicService;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import static co.edu.uniandes.csw.producto.logic.ejb._ProductoLogicService.URL_SERVICIO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;

@Default
@Stateless
@LocalBean
public class ProductoLogicService extends _ProductoLogicService implements IProductoLogicService {

    public void comprar(ClienteAndItemsDTO clienteAndItems) {
       
         persistance.comprar(clienteAndItems.getClienteEntity().getId(), clienteAndItems.getItems(), clienteAndItems.getDireccion()
                 , clienteAndItems.getMetodoPago(),getProductos());
//        
         try {
            //Se crea un cliente apache
            Client client = Client.create();
            /**
             * SE CONSUME EL SERVICIO REMOTO DE PRODUCTOS getProducto.
             * URL_SERVICIO tiene la ubicación de la aplicación de proveedores
             * (que se carga de service.properties)
             */
            WebResource webResource = client.resource(URL_SERVICIO + "/webresources/Producto/comprar");
            // Se instancia un nuevo object mapper para convertir el string JSON a un DTO
            ObjectMapper map = new ObjectMapper();
            //Se obtiene el string JSON por medio de get
            String resp = webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);
            //Se convierte el String a un DTO con ayuda de jackson y luego se retorna
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public ProductoDTO buscarProducto(ProductoDTO producto)throws Exception{
    
           List<ProductoDTO> pepe = getProductos();
           ProductoDTO resp = null;
           for(int i =0; i<pepe.size();i++){
               ProductoDTO pepa = pepe.get(i);
               if(pepa.getName().equalsIgnoreCase(producto.getName())){
                   resp = pepa;
               }
                   
           }
           if ( resp==null)
                throw new Exception ("No existe ese producto");
           
          return resp;       
    }
    
}