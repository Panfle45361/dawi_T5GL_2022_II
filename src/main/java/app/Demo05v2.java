package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05v2 {
	//ELIMINAR LóGICAMENTE --> cambiando lo ingresado
	public static void main(String[] args) {
		//Crear conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		
		//Crear manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		//Obtenemos el objeto Usuario
		Usuario u = em.find(Usuario.class, 10);
		
		
		//Validar su existencia
		if(u == null) {
			System.out.println("Usuario no encontrado");
		}
		else {
			//proceso --> cambiar el estado | 1: Habilitado , 2: Deshabilitado
			//seteamos el estado 
			u.setEstado(2);
			em.getTransaction().begin();
			em.merge(u);
			System.out.println("Usuario deshabilitado");
			em.getTransaction().commit();
		}
		
		//Por último cerramos
		em.close();
	}
}
