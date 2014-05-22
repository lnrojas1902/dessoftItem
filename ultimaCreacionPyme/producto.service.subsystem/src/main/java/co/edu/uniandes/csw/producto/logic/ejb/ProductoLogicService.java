
package co.edu.uniandes.csw.producto.logic.ejb;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteAndItemsDTO;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.producto.logic.api.IProductoLogicService;
import java.util.List;

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

}