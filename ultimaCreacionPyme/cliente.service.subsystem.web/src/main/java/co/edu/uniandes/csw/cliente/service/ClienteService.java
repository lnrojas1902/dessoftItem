package co.edu.uniandes.csw.cliente.service;

import co.edu.uniandes.csw.cliente.logic.dto.ClienteDTO;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Cliente")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteService extends _ClienteService {

    
    @POST
    @Path("/search")
    public List<ClienteDTO> searchCliente(ClienteDTO cliente){
        return this.clienteLogicService.searchCliente(cliente);
    }
    
    @POST
    @Path("/comprar")
    public void comprar(Long id) {
        this.clienteLogicService.comprar(id);
        
    }
    
    @POST
    @Path("/verificarExisteCliente")
    public void existeCliente(ClienteDTO cliente)throws Exception {
      
        if ( this.clienteLogicService.existeCliente(cliente))
            throw new Exception("Ya existe un cliente con el mismo ID");
        
    }
    
    @POST
    @Path("/login")
    public ClienteDTO loginCliente(ClienteDTO cliente) throws Exception{
        return this.clienteLogicService.loginCliente(cliente);
    }
}