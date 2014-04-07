package co.edu.uniandes.csw.pyme.service;

import co.edu.uniandes.csw.pyme.logic.dto.PymeDTO;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Pyme")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PymeService extends _PymeService {

    @GET
    @Path("/pymeDefault")
    public void pymeDefault(){
        
        PymeDTO a = pymeLogicService.getPyme(1L);
        
        if ( a == null){
            PymeDTO nueva = new PymeDTO();
            nueva.setDescripcion("Pyme Default");
            nueva.setId(1L);
            nueva.setIdCatalogo(1L);
            nueva.setName("Pyme Default");
            pymeLogicService.createPyme(nueva);
        }
    }

}