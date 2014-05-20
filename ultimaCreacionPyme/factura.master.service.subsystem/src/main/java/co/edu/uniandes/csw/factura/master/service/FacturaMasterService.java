package co.edu.uniandes.csw.factura.master.service;

import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/FacturaMaster")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FacturaMasterService extends _FacturaMasterService {


    @POST
    @Path("/getItemsFactura")
    public List<ItemDTO> getItemsFactura( Long id) {
        return facturaLogicService.getMasterFactura(id).getListItem();
    }
}
