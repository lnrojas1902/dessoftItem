package co.edu.uniandes.csw.cliente.logic.dto;

import co.edu.uniandes.csw.item.logic.dto.ItemDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClienteAndItemsDTO {

 
    protected ClienteDTO clienteEntity;
    protected Long id;
    protected String direccion;
    protected String metodoPago;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ClienteDTO getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteDTO clienteEntity) {
        this.clienteEntity = clienteEntity;
    }
    public String getDireccion()
    {
        return direccion;
    }
    public void setDireccion( String direccion)
    {
        this.direccion = direccion;
    }
    public String getMetodoPago()
    {
        return metodoPago;
    }
    public void setMetodoPago( String metodoPago)
    {
        this.metodoPago = metodoPago;
    }
    
    public List<ItemDTO> items;	
	
    public List<ItemDTO> getItems(){ return items; };
    public void setCreateItem(List<ItemDTO> items){ this.items=items; };
	
	
}

