import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Ventana {

	private JFrame frmRegistro;
	private JTextField textField;
	private JButton btnNewButton;
	public JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frmRegistro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}
	public void habilitar() {
		btnNewButton.setEnabled(true);
	}
	public void deshabilitar() {
		btnNewButton.setEnabled(false);
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistro = new JFrame();
		frmRegistro.getContentPane().setSize(new Dimension(100, 100));
		frmRegistro.getContentPane().setPreferredSize(new Dimension(100, 100));
		frmRegistro.setVisible(true);
		frmRegistro.setTitle("Registro");
		frmRegistro.setPreferredSize(new Dimension(100, 100));
		frmRegistro.setResizable(false);
		frmRegistro.setSize(new Dimension(100, 100));
		frmRegistro.setBounds(0, 0, 350, 300);
		frmRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmRegistro.getContentPane().setLayout(springLayout);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if(textField.getText().length()==0) {
					deshabilitar();
				}else {
					habilitar();
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, textField, 10, SpringLayout.NORTH, frmRegistro.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 10, SpringLayout.WEST, frmRegistro.getContentPane());
		textField.setAlignmentY(5.0f);
		textField.setAlignmentX(5.0f);
		frmRegistro.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Ingresar");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String productos = textField.getText();
				mysql conexion = new mysql();
				Connection conn = null;
				Statement stm = null;
				ResultSet rs = null;
				try {
					conn = conexion.conectar();
					stm = conn.createStatement();
					rs = stm.executeQuery("SELECT nombre FROM productos");
					Boolean control = false;
					
					while(rs.next()) {
						String nombre = rs.getString(1);
						System.out.println(nombre + "database");
						if(nombre.equals(productos)) {
							control = true;
						}
					}
					System.out.println(control);
					if(!control) {
						String sql = "insert into productos (nombre)"+"values(?)";
						PreparedStatement ps = conn.prepareCall(sql);
						ps.setString(1, productos);
						ps.executeUpdate();
					}
					textField.setText("");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, frmRegistro.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -217, SpringLayout.SOUTH, frmRegistro.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, textField, -6, SpringLayout.WEST, btnNewButton);
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, -139, SpringLayout.EAST, frmRegistro.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, frmRegistro.getContentPane());
		frmRegistro.getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 6, SpringLayout.SOUTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, frmRegistro.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -182, SpringLayout.SOUTH, frmRegistro.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 90, SpringLayout.EAST, frmRegistro.getContentPane());
		frmRegistro.getContentPane().add(lblNewLabel);
		
	}
}