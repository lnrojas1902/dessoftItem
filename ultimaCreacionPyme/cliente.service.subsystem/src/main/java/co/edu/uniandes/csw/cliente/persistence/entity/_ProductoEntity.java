
package co.edu.uniandes.csw.cliente.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _ProductoEntity {

	@Id
	@GeneratedValue(generator = "Producto")
	private Long id;
	private String name;
	private Integer costo;
	private Integer peso;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public Integer getCosto(){
		return costo;
	}
	
	public void setCosto(Integer costo){
		this.costo = costo;
	}
	public Integer getPeso(){
		return peso;
	}
	
	public void setPeso(Integer peso){
		this.peso = peso;
	}
}