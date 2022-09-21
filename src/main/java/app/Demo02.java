package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {
	public static void main(String[] args) {
		//conexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		//Crear manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		//proceso --> reg, act, eli : Transacciones
		em.getTransaction().begin();
		
		//Objeto a actualizar
		//Usuario u = new Usuario(10, "Juan", "Torres", "JTorres", "123", "2000/10/05", 2, 1);
		Usuario u = new Usuario(10, "Juan Carlos", "Perez Lopez", "JPerezl", "j789", "2000/10/05", 2, 1, null);
		//Grabar
		//em.persist(u); //<-- SE USA PARA CREATE
		em.merge(u); //<-- Hace una BUSQUEDA, si existe actualiza | sino registra
		
		//Imprimir
		System.out.println("Usuario Actualizado");
		
		//Confirmamos transac
		em.getTransaction().commit();
		
		//Cerramos
		em.close();
	}
}
