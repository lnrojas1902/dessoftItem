
package co.edu.uniandes.csw.cliente.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _ClienteEntity {

	@Id
	@GeneratedValue(generator = "Cliente")
	private Long id;
	private String name;
	private String docId;
	private String tipo;
	private String password;

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
	public String getDocId(){
		return docId;
	}
	
	public void setDocId(String docId){
		this.docId = docId;
	}
	public String getTipo(){
		return tipo;
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}