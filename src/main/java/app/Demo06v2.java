package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo06v2 {
	//LISTADO DE TODOS LOS USUARIOS
	public static void main(String[] args) {
		//Conexi�n
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		EntityManager em = fabrica.createEntityManager();
		
		//Listado para mostrar todos los usuarios --> select * from tb_usuarios
		List<Usuario> lstUsuario = em.createQuery("select u from Usuario u", Usuario.class).getResultList(); 
									//En vez de '*' colocamos una variable en este caso 'u'
									//el m�todo "createQuery" devuelve un objeto de tipo TypeQuery x lo que usamos este �ltimo m�todo
		
		//Mostramos la info de la lista
		
		/*Para realizar inner join, debemos hacerlo seg�n la relaci�n entre ambas tablas
		  tb_usuarios tendr� una relaci�n de "m a 1" con la tabla tb_tipos
		  En este caso, como trabajamos con la tabla USUARIOS, tendremos que crear un solo objeto de tipo "Tipo"
		  pero si es que trabajaramos con la tabla TIPOS, necesitariamos crear una lista de tipo "Usuarios ya que 
		  en Tipos los tiene muchos Usuarios*/
		System.out.println("Listado de Usuarios");
		for (Usuario us : lstUsuario) {
			System.out.println("C�digo...: " + us.getCodigo());
			System.out.println("Nombre...: " + us.getNombre() + " " + us.getApellido());
			System.out.println("Tipo.....: " + us.getTipo() + " - " + us.getObjTipo().getDescripcion());
			System.out.println("----------------------------------------------");
		}
		
		//Cerramos
		em.close();
	}
}

