package ec.edu.puce.facturacion;



import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmProducto extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtIdProducto;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private static ArrayList<Producto> productos = new ArrayList<Producto>();
	private JButton btnGuardar;
	private JTable table;
	private DefaultTableModel model;

	
	public FrmProducto() {
		setTitle("Agregar Productos");
		setBounds(100, 100, 450, 500);
		getContentPane().setLayout(null);
		
		JLabel lblIdProducto = new JLabel("Id");
		lblIdProducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdProducto.setBounds(39, 26, 118, 14);
		getContentPane().add(lblIdProducto);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescripcion.setBounds(39, 65, 88, 14);
		getContentPane().add(lblDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrecio.setBounds(39, 104, 88, 14);
		getContentPane().add(lblPrecio);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCantidad.setBounds(39, 143, 88, 14);
		getContentPane().add(lblCantidad);
		
		txtIdProducto = new JTextField();
		txtIdProducto.setBounds(119, 25, 260, 20);
		getContentPane().add(txtIdProducto);
		txtIdProducto.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(119, 64, 260, 20);
		getContentPane().add(txtDescripcion);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(119, 103, 260, 20);
		getContentPane().add(txtPrecio);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(119, 142, 260, 20);
		getContentPane().add(txtCantidad);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnNuevo.setBounds(45, 190, 90, 23);
		getContentPane().add(btnNuevo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearProducto();
			}
		});
		btnGuardar.setBounds(180, 190, 90, 23);
		getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});
		btnCancelar.setBounds(315, 190, 89, 23);
		getContentPane().add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 224, 359, 218);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(192, 192, 192));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Descripci\u00F3n", "Precio", "Cantidad", "Total"
			}
		));
		scrollPane.setViewportView(table);
		model = (DefaultTableModel) table.getModel();

	}
	
	private void limpiarCampos() {
		this.txtIdProducto.setText("");
		this.txtDescripcion.setText("");
		this.txtPrecio.setText("");
		this.txtCantidad.setText("");
	}
	
	private void cerrarVentana() {
		this.setVisible(false);
	}
	
	private void crearProducto() {
		Producto producto = new Producto(
				this.txtIdProducto.getText(),
				this.txtDescripcion.getText(),
				Double.parseDouble(this.txtPrecio.getText()) ,
				Integer.parseInt(this.txtCantidad.getText()) 
				);
		productos.add(producto);
		agregarFila();
	}
	
	private void agregarFila() {
		Object[] fila=new Object[5];
		fila[0]= productos.get(productos.size()-1).getIdProducto();
		fila[1]= productos.get(productos.size()-1).getDescripcion();
		fila[2]= productos.get(productos.size()-1).getPrecio();
		fila[3]= productos.get(productos.size()-1).getCantidad();
		fila[4]= productos.get(productos.size()-1).getTotal();
		this.model.addRow(fila);
	}
	
	public static ArrayList<Producto> getProductos(){
		return productos;
	}

}
