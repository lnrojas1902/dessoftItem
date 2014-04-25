
package co.edu.uniandes.csw.factura.persistence;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
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
                    if(f.getFechaDeRealizacion().compareTo(d.getFechaDeRealizacion())>0 )
                    {
                        e.add(f);
                    }
                }
                return e;
	}
}