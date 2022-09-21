package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_categorias")
public class Categoria {
	@Id
	private int idcategoria;//idcategoria	int not null primary key,
	private String descripcion;//descripcion varchar(45)
}
