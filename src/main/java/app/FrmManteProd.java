package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCodigo;
	private JComboBox<String> cboCategorias;
	private JComboBox<String> cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 29, 89, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox<String>();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedores = new JLabel("Proveedor:");
		lblProveedores.setBounds(230, 106, 102, 14);
		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox<String>();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(324, 63, 89, 23);
		contentPane.add(btnBuscar);

		llenaCombo();
	}

	void llenaCombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		EntityManager em = fabrica.createEntityManager();
		
		//Combo 1. Obtener un listado de las categorías
		//Crear una lista de tipo Categoria
		List<Categoria> lstCategorias = em.createQuery("Select c from Categoria c", Categoria.class).getResultList();

		//Pasar el listado al objeto cboCategorias
		cboCategorias.addItem("Seleccione...");

		for(Categoria cs : lstCategorias) {
			cboCategorias.addItem(cs.getIdcategoria()  + " - " + cs.getDescripcion());
		}
		
		
		//Combo 2. Obtener un listado de los proveedores
		List<Proveedor> lstProveedores = em.createQuery("Select p from Proveedor p", Proveedor.class).getResultList(); 
		
		//Pasar el listado al cboProveedores
		cboProveedores.addItem("Seleccione...");
		
		for(Proveedor ps : lstProveedores) {
			cboProveedores.addItem(ps.getIdprovedor() + " - " + ps.getNombre_rs());
		}
	}

	void registrar() {
		//leer los campos
		String codigo = txtCodigo.getText();
		String nombre = txtDescripcion.getText();
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int idcategoria = cboCategorias.getSelectedIndex();
		int estado = 1;
		int idprovedor = cboProveedores.getSelectedIndex();

		//Validaciones
		
		//obj de nuevo producto
		Producto p = new Producto();
		p.setId_prod(codigo);
		p.setDes_prod(nombre);
		p.setStk_prod(stock);
		p.setPre_prod(precio);
		p.setIdcategoria(idcategoria);
		p.setEst_prod(estado);
		p.setIdprovedor(idprovedor);
		
		//guardar en la tabla --> registrar en la tabla
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		EntityManager em = fabrica.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			
			em.getTransaction().commit();
			
			//mostrar los mensajes de éxito o error
			aviso("Registro OK", "Aviso de Sistema", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			aviso("Error al Registrar", "Aviso de Sistema", JOptionPane.ERROR_MESSAGE);
		}
		
		em.close();
	}
	
	void aviso (String msg, String tit, int icono) {
		JOptionPane.showMessageDialog(this, msg, tit, icono);
	}

	void listado() {
		//OBtenemos la conex
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		EntityManager em = fabrica.createEntityManager();
		
		//Generar un listado
		List<Producto> lstProd = em.createQuery("Select p from Producto p ", Producto.class).getResultList();
		
		//Mostrar el listado en el txt Area
		for(Producto ps : lstProd) {
			imprimir("*********************************************");
			imprimir("Id Producto---: " + ps.getId_prod());
			imprimir("Descripción---: " + ps.getDes_prod());
			imprimir("Stock---------: " + ps.getStk_prod());
			imprimir("Precio--------: " + ps.getPre_prod());
			imprimir("Categoria-----: " + ps.getIdcategoria());
			imprimir("Estado--------: " + ps.getEst_prod());
			imprimir("Id Proveedor--: " + ps.getIdprovedor());
			imprimir("*********************************************");
		}
		
		//Cerrar
		em.close();
	}
	
	void imprimir(String txt) {
		txtSalida.append(txt + "\n");
	}
	
	String leerCodigo() {
		return txtCodigo.getText();
	}
	
	void buscar() {
		//leer codigo 
		String codigo = leerCodigo();
		
		//Establecer conexión
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mySQL");
		EntityManager em = fabrica.createEntityManager();
		//buscar un Producto con el código indicado
		Producto p = em.find(Producto.class, codigo);
		
		//Si existe, muestra en los campos los valores correspondientes
		if(p != null) {
			//":codigo" es un parámetro asignado, gracias al método .setParameter("[nombre del parámetro]", [valor])
			//p = em.createQuery("select p from Producto p where id_prod = :codigo", Producto.class).setParameter("codigo", codigo).getSingleResult();
			txtDescripcion.setText(p.getDes_prod());
			cboCategorias.setSelectedIndex(p.getIdcategoria());
			txtStock.setText(p.getStk_prod() + "");
			txtPrecio.setText(p.getPre_prod() + "");
		}
		else 
			aviso("Campo vacío", "Aviso de Sistema", JOptionPane.INFORMATION_MESSAGE);
		
		em.close();
	}
}
