
package co.edu.uniandes.csw.producto.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _ProductoDTO {

	private Long id;
	private String name;
	private Integer costo;
	private Integer peso;
        
        
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCosto() {
		return costo;
	}
 
	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	public Integer getPeso() {
		return peso;
	}
 
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
}