package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	public static void main(String[] args) {
		//Crear conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		
		//Crear manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		//Obtenemos el objeto Usuario
		Usuario u = em.find(Usuario.class, 20);
		
		
		//Validar su existencia
		if(u == null) {
			System.out.println("Usuario no encontrado");
		}
		else {
			em.getTransaction().begin();
			em.remove(u);
			System.out.println("Usuario eliminado");
			em.getTransaction().commit();
		}
		
		//Por último cerramos
		em.close();
	}
}
