
package co.edu.uniandes.csw.factura.persistence;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.factura.persistence.entity.FacturaItemEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.persistence.Query;

@Default
@Stateless 
@LocalBean
public class FacturaPersistence extends _FacturaPersistence  implements IFacturaPersistence {
public List<FacturaDTO> getFacturasFecha(FacturaDTO d) 
        {
                ArrayList<FacturaDTO> e = new ArrayList<FacturaDTO>();
		//e.add(FacturaConverter.entity2PersistenceDTO(entityManager.find(FacturaEntity.class, d)));
                //if((FacturaConverter.entity2PersistenceDTO(entityManager.find(FacturaEntity.class, d))).getFechaDeRealizacion().compareTo(d)==0)
               // {
                 //   e.add((FacturaConverter.entity2PersistenceDTO(entityManager.find(FacturaEntity.class, d))));
                //}
                Query q = entityManager.createQuery("select u from FacturaEntity u");
		for(int i =0; i<FacturaConverter.entity2PersistenceDTOList(q.getResultList()).size(); i++ )
                {
                    FacturaDTO f = (FacturaDTO) FacturaConverter.entity2PersistenceDTOList(q.getResultList()).get(i);
                    if(f.getFechaDeRealizacion().compareTo(d.getFechaEsperadaEntrega()) >= 0
                            && f.getFechaDeRealizacion().compareTo(d.getFechaDeRealizacion())<= 0)
                    {
                        e.add(f);
                    }
                }
                return e;
	}

    public void actualizarFacturas() 
    {
        Query q = entityManager.createQuery("select u from FacturaItemEntity u");
        
        List <FacturaItemEntity> facturas = q.getResultList();
        for (int k = 0; k < getFacturas().size(); k++) {
            
        long id = getFacturas().get(k).getId();
           
        ArrayList<Integer> resp = new ArrayList<Integer>();
        
        for (int i = 0; i < facturas.size(); i++) {
            
            FacturaItemEntity actual = facturas.get(i);
            
            if ( actual.getFacturaId() == id){
                
                Long itmID = actual.getItemId();
                Query qEstadoItem = entityManager.createQuery("select u.estado from ItemEntity u where"
                        + " u.id="+itmID);
                
                String estadoAct = (String) qEstadoItem.getSingleResult();
                
                if(estadoAct.equalsIgnoreCase("En proceso"))
                {
                    resp.add(1);
                }
                else if(estadoAct.equalsIgnoreCase("Terminado"))
                {
                    resp.add(2);
                }
                else if(estadoAct.equalsIgnoreCase("En bodega"))
                {
                    resp.add(3);
                }
                else if(estadoAct.equalsIgnoreCase("En tramite"))
                {
                    resp.add(4);
                }
                else if(estadoAct.equalsIgnoreCase("Entregado"))
                {
                    resp.add(5);
                }
            }
        }
        int min = 5;
        for(int j = 0;j<resp.size();j++)
        {
            if(j == 0)
            {
                min = resp.get(0);
            }
            else
            {
                min = Math.min(min,resp.get(j));
            }
        }
        if(min == 5)
        {
            FacturaDTO factDto = getFactura(id);
            factDto.setEstado("Pedido entregado");
            updateFactura(factDto);
        }
        else if(min == 4)
        {
            FacturaDTO factDto = getFactura(id);
            factDto.setEstado("En transporte");
            updateFactura(factDto);
        }
        else if(min == 3)
        {
            FacturaDTO factDto = getFactura(id);
            factDto.setEstado("En bodega");
            updateFactura(factDto);
        }
        else if(min == 2)
        {
            FacturaDTO factDto = getFactura(id);
            factDto.setEstado("Fabricado");
            updateFactura(factDto);
        }
        else if(min == 1)
        {
            FacturaDTO factDto = getFactura(id);
            factDto.setEstado("En proceso de fabricación");
            updateFactura(factDto);
        }
        else
        {
            FacturaDTO factDto = getFactura(id);
            factDto.setEstado("???");
            updateFactura(factDto);
        }
        }
    }
    
    public void calificar(FacturaDTO fac) {
        
        System.out.print("Llegamos al fondo");
        Long idFactura = fac.getId();
        int calificacion = fac.getCalificacion();
        System.out.print("Estos son los datos que llegaron: idFactura: "+idFactura+"; calificacion: "+calificacion);

        
        FacturaDTO factura = getFactura(idFactura);
        System.out.print("Esta es el id de factura que se encontro a partir del id: " + factura.getId());
        
        factura.setCalificacion(calificacion);
        System.out.print("Ahora esta es la calificacion modificada: "+ factura.getCalificacion());
        updateFactura(factura);
        
    }
}