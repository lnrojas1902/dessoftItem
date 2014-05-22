package co.edu.uniandes.csw.producto.service;

import co.edu.uniandes.csw.producto.logic.dto.ClienteAndItemsDTO;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Producto")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductoService extends _ProductoService {
    
    @POST
    @Path("/listar")
    public List<ProductoDTO> getProductosA(){
	return productoLogicService.getProductos();
    }
    
    @POST
    @Path("/getProductoId")
    public ProductoDTO getProductoId(Long id){
	return productoLogicService.getProducto(id);
    }
    
   
    @POST
    @Path("/comprar")
    public void confirmarCompra(ClienteAndItemsDTO clienteAndItems) {
        this.productoLogicService.comprar(clienteAndItems);
        
    }

    @POST
    @Path("/buscarProducto")
    public ProductoDTO buscarProducto(String nombre) {
        return this.productoLogicService.buscarProducto(nombre);
        
    }
    

    
}