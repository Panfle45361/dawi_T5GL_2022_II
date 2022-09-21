package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	private JButton btnIngresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("Usuario ");
		lblUsuario.setFont(new Font("Anime Ace", Font.PLAIN, 12));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(10, 34, 113, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Contrase\u00F1a ");
		lblClave.setFont(new Font("Anime Ace", Font.PLAIN, 12));
		lblClave.setHorizontalAlignment(SwingConstants.CENTER);
		lblClave.setBounds(10, 77, 113, 14);
		contentPane.add(lblClave);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(133, 34, 136, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(133, 77, 136, 20);
		contentPane.add(txtClave);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresar();
			}
		});
		btnIngresar.setBounds(350, 31, 89, 23);
		contentPane.add(btnIngresar);
	}
	
	void ingresar() {
		//Leer los campos
		String usuario = leerUsuario();
		String clave = leerClave();
		
		//Obtener el Usuario según los campos de Usuario y Clave
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		EntityManager em = fabrica.createEntityManager();
		//Usuario u = em.find(Usuario.class, 20); BUSCA SOLO POR EL ID (PK)
		//TypedQuery guardará la ejecución de una consulta SQL
		Usuario u = null;
		try {
			TypedQuery<Usuario> consulta = em.createQuery("select u from Usuario u where u.usuario = :usuario AND u.clave = :clave", Usuario.class);
			consulta.setParameter("usuario", usuario);
			consulta.setParameter("clave", clave);
			u = consulta.getSingleResult(); //Si no devuelve resultados, no devuelve "null" sino, lanza una excepcion
			aviso("Bienvenido", "Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			u = null;
			//Mostrar los mensajes de éxito o error respectivo
			aviso("Usuario no existe", "Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
		}

		em.close();
	}
	
	private String leerUsuario() {
		return txtUsuario.getText();
	}
	
	private String leerClave() {
		return String.valueOf(txtClave.getPassword());
	}
	
	void aviso (String msg, String tit, int icono) {
		JOptionPane.showMessageDialog(this, msg, tit, icono);
	}
}
