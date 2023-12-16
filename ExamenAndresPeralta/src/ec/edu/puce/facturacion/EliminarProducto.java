package ec.edu.puce.facturacion;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultTextUI;

public class EliminarProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel modelLocal;
	private JTable table;
	//private DefaultTableModel referenceModel;
	private int indiceProducto = -1;


	
	public EliminarProducto(Dialog owner, boolean modal, DefaultTableModel model, ArrayList<Producto> productos, JLabel lblSubtotal, JLabel lblIVA, JLabel lblTotal) {
		super(owner, modal);
		setBounds(100, 100, 481, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 348, 208);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Descipci\u00F3n", "Precio", "Cantidad", "Total"
			}
		));
		scrollPane.setViewportView(table);
		modelLocal = (DefaultTableModel) table.getModel();
		
		if (!FrmProducto.getProductos().isEmpty()) {
			agregarProducto(productos);
		}
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eliminarProducto(model, productos, lblSubtotal, lblIVA, lblTotal);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void agregarProducto(ArrayList<Producto> productos) {
		for (Producto producto : productos) {
			Object[] fila=new Object[5];
			fila[0]= producto.getIdProducto();
			fila[1]= producto.getDescripcion();
			fila[2]= producto.getPrecio();
			fila[3]= producto.getCantidad();
			fila[4]= producto.getTotal();
			this.modelLocal.addRow(fila);
			
			JButton btnProducto = new JButton("Elegir");
			btnProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionarProducto(productos.indexOf(producto));
				}
			});
			btnProducto.setBounds(370, 30+(productos.indexOf(producto)*18), 89, 17);
			this.contentPanel.add(btnProducto);
		}	
	}
	
	private void seleccionarProducto(int i) {
		this.indiceProducto = i;
	}
	
	private void eliminarProducto(DefaultTableModel model, ArrayList<Producto> productos, JLabel lblSubtotal, JLabel lblIVA, JLabel lblTotal) {
		if (this.indiceProducto==-1) {
			JOptionPane.showMessageDialog(null, "Seleccione un producto", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		model.removeRow(indiceProducto);
		for (Producto producto: FrmProducto.getProductos()) {
			if (producto.getIdProducto() == productos.get(indiceProducto).getIdProducto()) {
				producto.setCantidad(producto.getCantidad()+productos.get(indiceProducto).getCantidad());
			}
		}
		productos.remove(indiceProducto);
		double total = 0;
		for (Producto productoSum : productos) {
			total += productoSum.getTotal();
		}
		Double iva = total*0.12;
		String ivaS = String.format("%.2f", iva);
		iva = Double.parseDouble(ivaS);
		lblSubtotal.setText("Subtotal: "+total);
		lblIVA.setText("IVA: "+ivaS);
		lblTotal.setText("Total: "+(total+iva));
		dispose();
	}

}
