package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	public static void main(String[] args) {
		//Crear conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		
		//Crear manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		//Comenzamos la transacción
		em.getTransaction().begin();
		
		//Objeto a eliminar
		Usuario u = new Usuario();
		u.setCodigo(20); //Seteamos un código xq es lo único que necesitamos
		
		//especificamos su tipo
		em.remove(u); //Método remover tambien hace busqueda, solo elimina si encuentra el objeto completo
						// No basta con solo el código
		
		System.out.println("Usuario eliminado");
		
		em.getTransaction().commit();
		
		//Por último cerramos
		em.close();
	}
}
