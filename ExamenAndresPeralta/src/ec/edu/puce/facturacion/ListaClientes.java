package ec.edu.puce.facturacion;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private int indiceCliente=-1;

	public ListaClientes(Dialog owner, boolean modal, JLabel lblCedula, JLabel lblNombres, JLabel lblApellidos, JLabel lblTelefono, JLabel lblEmail, JLabel lblDireccion) {
		super(owner, modal);
		setBounds(100, 100, 572, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 416, 208);
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
				"Cedula", "Nombres", "Apellidos", "Tel\u00E9fono", "Email", "Direcci\u00F3n"
			}
		));
		scrollPane.setViewportView(table);
		model = (DefaultTableModel) table.getModel();
		
		if (!FrmCliente.getClientes().isEmpty()) {
			agregarClientes();
		}
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						enviarCliente(lblCedula,  lblNombres,  lblApellidos,  lblTelefono,  lblEmail,  lblDireccion);
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
	
	private void agregarClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(FrmCliente.getClientes());
		
		for (Cliente cliente : clientes) {
			Object[] fila=new Object[6];
			fila[0]= cliente.getCedula();
			fila[1]= cliente.getNombres();
			fila[2]= cliente.getApellidos();
			fila[3]= cliente.getTelefono();
			fila[4]= cliente.getEmail();
			fila[5]= cliente.getDireccion();
			this.model.addRow(fila);
			
			JButton btnCliente = new JButton("Elegir");
			btnCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionarCliente(clientes.indexOf(cliente));
				}
			});
			btnCliente.setBounds(440, 30+(clientes.indexOf(cliente)*18), 89, 17);
			this.contentPanel.add(btnCliente);
		}
	}
	
	private void seleccionarCliente(int i) {
		this.indiceCliente = i;
	}
	
	private void enviarCliente(JLabel lblCedula, JLabel lblNombres, JLabel lblApellidos, JLabel lblTelefono, JLabel lblEmail, JLabel lblDireccion) {
		if (this.indiceCliente==-1) {
			JOptionPane.showMessageDialog(null, "Seleccione un cliente", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		lblCedula.setText("Cédula: "+FrmCliente.getClientes().get(indiceCliente).getCedula());
		lblNombres.setText("Nombres: "+FrmCliente.getClientes().get(indiceCliente).getNombres());
		lblApellidos.setText("Apellidos: "+FrmCliente.getClientes().get(indiceCliente).getApellidos());
		lblTelefono.setText("Teléfono: "+FrmCliente.getClientes().get(indiceCliente).getTelefono());
		lblEmail.setText("Email: "+FrmCliente.getClientes().get(indiceCliente).getEmail());
		lblDireccion.setText("Dirección: "+FrmCliente.getClientes().get(indiceCliente).getDireccion());
		dispose();
	}
	
}
