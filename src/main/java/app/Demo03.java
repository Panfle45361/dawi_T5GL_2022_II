package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	public static void main(String[] args) {
		//Crear conexi�n
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		
		//Crear manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		//Comenzamos la transacci�n
		em.getTransaction().begin();
		
		//Objeto a eliminar
		Usuario u = new Usuario();
		u.setCodigo(20); //Seteamos un c�digo xq es lo �nico que necesitamos
		
		//especificamos su tipo
		em.remove(u); //M�todo remover tambien hace busqueda, solo elimina si encuentra el objeto completo
						// No basta con solo el c�digo
		
		System.out.println("Usuario eliminado");
		
		em.getTransaction().commit();
		
		//Por �ltimo cerramos
		em.close();
	}
}
