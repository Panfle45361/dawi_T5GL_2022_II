package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo06 {
	//LISTADO DE TODOS LOS USUARIOS
	public static void main(String[] args) {
		//Conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		EntityManager em = fabrica.createEntityManager();
		
		//Listado para mostrar todos los usuarios --> select * from tb_usuarios
		List<Usuario> lstUsuario = em.createQuery("select u from Usuario u", Usuario.class).getResultList(); 
									//En vez de '*' colocamos una variable en este caso 'u'
									//el método "createQuery" devuelve un objeto de tipo TypeQuery x lo que usamos este último método
		
		//Mostramos la info de la lista
		for (Usuario us : lstUsuario) {
			System.out.println(us);
		}
		
		//Cerramos
		em.close();
	}
}
