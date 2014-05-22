
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
        System.out.println ("clitnte: " + clienteAndItems.getClienteEntity().getName() );
         for (int i = 0; i < clienteAndItems.items.size(); i++) {
            System.out.println ("\n ProdcutoId "+ i+ ": "+ clienteAndItems.getItems().get(i).getProductoId());  
            System.out.println ("\t\t cantidad "+ i+ ": "+ clienteAndItems.getItems().get(i).getCantidad()); 
        }  
         persistance.comprar(clienteAndItems.getClienteEntity().getId(), clienteAndItems.getItems(), clienteAndItems.getDireccion(), clienteAndItems.getMetodoPago());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ProductoDTO buscarProducto(String nombre){
    try {
        
           List<ProductoDTO> pepe = getProductos();
           ProductoDTO resp = null;
           for(int i =0; i<pepe.size();i++){
               ProductoDTO pepa = pepe.get(i);
               if(pepa.getName()== nombre){
                   resp = pepa;
               }
                   
           }
            
            return resp;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
}
    
}