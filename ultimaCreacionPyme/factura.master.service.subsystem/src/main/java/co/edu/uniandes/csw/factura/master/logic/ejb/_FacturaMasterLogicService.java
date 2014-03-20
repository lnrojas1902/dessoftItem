package co.edu.uniandes.csw.factura.master.logic.ejb;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.api.IItemPersistence;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.master.logic.api._IFacturaMasterLogicService;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import co.edu.uniandes.csw.factura.master.persistence.api.IFacturaMasterPersistence;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaItemEntity;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import javax.inject.Inject;

public abstract class _FacturaMasterLogicService implements _IFacturaMasterLogicService {

    @Inject
    protected IFacturaPersistence facturaPersistance;
    @Inject
    protected IFacturaMasterPersistence facturaMasterPersistance;
    @Inject
    protected IItemPersistence itemPersistance;

    public FacturaMasterDTO createMasterFactura(FacturaMasterDTO factura) {
        FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(factura.getFacturaEntity());
        if (factura.getCreateItem() != null) {
            for (ItemDTO itemDTO : factura.getCreateItem()) {
                ItemDTO persistedItemDTO = itemPersistance.createItem(itemDTO);
                FacturaItemEntity facturaItemEntity = new FacturaItemEntity(persistedFacturaDTO.getId(), persistedItemDTO.getId());
                facturaMasterPersistance.createFacturaItem(facturaItemEntity);
            }
        }
        return factura;
    }

    public FacturaMasterDTO getMasterFactura(Long id) {
        return facturaMasterPersistance.getFactura(id);
    }

    public void deleteMasterFactura(Long id) {
        facturaPersistance.deleteFactura(id);
    }

    public void updateMasterFactura(FacturaMasterDTO factura) {
        facturaPersistance.updateFactura(factura.getFacturaEntity());

        //---- FOR RELATIONSHIP
        // persist new item
        if (factura.getCreateItem() != null) {
            for (ItemDTO itemDTO : factura.getCreateItem()) {
                ItemDTO persistedItemDTO = itemPersistance.createItem(itemDTO);
                FacturaItemEntity facturaItemEntity = new FacturaItemEntity(factura.getFacturaEntity().getId(), persistedItemDTO.getId());
                facturaMasterPersistance.createFacturaItem(facturaItemEntity);
            }
        }
        // update item
        if (factura.getUpdateItem() != null) {
            for (ItemDTO itemDTO : factura.getUpdateItem()) {
                itemPersistance.updateItem(itemDTO);
            }
        }
        // delete item
        if (factura.getDeleteItem() != null) {
            for (ItemDTO itemDTO : factura.getDeleteItem()) {
                facturaMasterPersistance.deleteFacturaItem(factura.getFacturaEntity().getId(), itemDTO.getId());
                itemPersistance.deleteItem(itemDTO.getId());
            }
        }
    }
}
