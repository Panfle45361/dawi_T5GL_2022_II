package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {
	public static void main(String[] args) {
		//Conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		//crear manejador
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso --> Consulta | no es una transacción --> Select * from tb_*** where ***
		Usuario u = em.find(Usuario.class, 20); //Devuelve un objeto Usuario, en este caso con un id '20'
												//Si no el MANEJADOR no encuentra ningún objeto devuelve 'null'
		//Validar existencia del objeto
		if(u == null)
			System.out.println("Usuario no encontrado");
		else {
			System.out.println("Usuario encontrado");
			System.out.println(u);
		}

		em.close();
	}
}
