package co.edu.uniandes.csw.factura.master.persistence.converter;
import co.edu.uniandes.csw.factura.master.persistence.entity.FacturaItemEntity;
import co.edu.uniandes.csw.item.logic.dto.ItemDTO;
import co.edu.uniandes.csw.item.persistence.converter.ItemConverter;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.master.logic.dto.FacturaMasterDTO;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _FacturaMasterConverter {

    public static FacturaMasterDTO entity2PersistenceDTO(FacturaEntity facturaEntity 
    ,List<FacturaItemEntity> facturaItemEntity 
    ) {
        FacturaDTO facturaDTO = FacturaConverter.entity2PersistenceDTO(facturaEntity);
        ArrayList<ItemDTO> itemEntities = new ArrayList<ItemDTO>(facturaItemEntity.size());
        for (FacturaItemEntity facturaItem : facturaItemEntity) {
            itemEntities.add(ItemConverter.entity2PersistenceDTO(facturaItem.getItemEntity()));
        }
        FacturaMasterDTO respDTO  = new FacturaMasterDTO();
        respDTO.setFacturaEntity(facturaDTO);
        respDTO.setListItem(itemEntities);
        return respDTO;
    }

}