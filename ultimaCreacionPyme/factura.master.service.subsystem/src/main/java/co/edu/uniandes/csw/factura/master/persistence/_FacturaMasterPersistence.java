package co.edu.uniandes.csw.factura.master.persistence;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaItemEntity;
import co.edu.uniandes.csw.item.persistence.converter.ItemConverter;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import co.edu.uniandes.csw.factura.master.persistence.api._IFacturaMasterPersistence;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _FacturaMasterPersistence implements _IFacturaMasterPersistence {

    @PersistenceContext(unitName = "FacturaMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IFacturaPersistence facturaPersistence;  

    public FacturaMasterDTO getFactura(Long facturaId) {
        FacturaMasterDTO facturaMasterDTO = new FacturaMasterDTO();
        FacturaDTO factura = facturaPersistence.getFactura(facturaId);
        facturaMasterDTO.setFacturaEntity(factura);
        facturaMasterDTO.setListItem(getItemListForFactura(facturaId));
        return facturaMasterDTO;
    }

    public FacturaItemEntity createFacturaItem(FacturaItemEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteFacturaItem(Long facturaId, Long itemId) {
        Query q = entityManager.createNamedQuery("FacturaItemEntity.deleteFacturaItem");
        q.setParameter("facturaId", facturaId);
        q.setParameter("itemId", itemId);
        q.executeUpdate();
    }

    public List<ItemDTO> getItemListForFactura(Long facturaId) {
        ArrayList<ItemDTO> resp = new ArrayList<ItemDTO>();
        Query q = entityManager.createNamedQuery("FacturaItemEntity.getItemListForFactura");
        q.setParameter("facturaId", facturaId);
        List<FacturaItemEntity> qResult =  q.getResultList();
        for (FacturaItemEntity facturaItemEntity : qResult) { 
            if(facturaItemEntity.getItemEntity()==null){
                entityManager.refresh(facturaItemEntity);
            }
            resp.add(ItemConverter.entity2PersistenceDTO(facturaItemEntity.getItemEntity()));
        }
        return resp;
    }

}
