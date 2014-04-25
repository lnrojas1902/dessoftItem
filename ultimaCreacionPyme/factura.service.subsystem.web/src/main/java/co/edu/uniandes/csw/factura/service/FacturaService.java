package co.edu.uniandes.csw.factura.service;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Factura")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FacturaService extends _FacturaService {


    @POST
    @Path("/facturasCliente")    
    public List<FacturaDTO> darFacturasCliente(Long id){
        
        ArrayList <FacturaDTO> resp = new ArrayList();
        
        for (int i = 0; i < getFacturas().size(); i++) {
            
            if ( getFacturas().get(i).getClienteId()==id)
                resp.add(getFacturas().get(i));
        }
        
        return resp;
    }
    @POST
    @Path("/search")
    public List<FacturaDTO> buscarFacturaFecha(FacturaDTO user){
        return facturaLogicService.getFacturasFecha(user);
    }
}