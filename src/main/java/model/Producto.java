package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_productos")
public class Producto {
	@Id
	private String id_prod;//id_prod char(5) not null,
	private String des_prod;//des_prod varchar(45) not null,
	private int stk_prod;//stk_prod int,
	private double pre_prod;//pre_prod decimal(8,2) not null,
	private int idcategoria;//idcategoria int,
	private int est_prod;//est_prod boolean,
	private int idprovedor;//idprovedor int,
	@ManyToOne
	@JoinColumn(name = "idcategoria", insertable = false, updatable = false)
	private Categoria objCat;
	@ManyToOne
	@JoinColumn(name = "idprovedor", insertable = false, updatable = false)
	private Proveedor objProv;
	//primary key (id_prod), 
	//constraint fk_categoria foreign key (idcategoria) references tb_categorias(idcategoria),
	//constraint fk_proveedor foreign key (idprovedor) references tb_proveedor(idprovedor)
}
