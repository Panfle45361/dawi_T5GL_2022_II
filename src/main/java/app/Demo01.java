package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {

	//registrar los datos de un nuevo usuario
	public static void main(String[] args) {
		/* SEMANA 1 : Clase 2*/
		//#1 Establecer conexión --> con la unidad de persistencia
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL"); //<--Permite crear un manejador de propiedades
		
		//#2 Crear el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		//proceso --> reg, act, eli : Transacciones
		em.getTransaction().begin(); //Empezar la transacción
		
		//objeto a grabar
		Usuario u = new Usuario(10, "Juan", "Perez", "JPerez", "123", "2000/10/05", 1, 1, null);
		em.persist(u);
		
		//Imprimir
		System.out.println("Grabación OK");
		
		//Confirmar transacción
		em.getTransaction().commit();
		
		//#3 Cerrar conexión
		em.close();
	}

}
