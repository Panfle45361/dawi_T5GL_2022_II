package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data //--> Creará constructor lleno y vacío, setters y getters,y toString
@Table(name = "tb_tipos")
public class Tipo {
	@Id
	private int idTipo; 
	private String descripcion;
}
//Cuando el nombre del atributo es igual que al de la tabla en BD no es necesario la anotación @Column
