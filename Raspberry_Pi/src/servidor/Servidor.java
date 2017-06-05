package servidor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class Servidor extends JFrame {

	private JTextArea txtArea;
	private JLabel lblVerde;
	private JLabel lblAmarillo;
	private JLabel lblRojo;
	private String mensaje;

	public Servidor() {
		super("Servidor - Casa Domótica");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);

		// JPanels //
		JPanel panelSuperior = new JPanel();
		JPanel panelInferior = new JPanel();

		TitledBorder tbIndicadores = BorderFactory.createTitledBorder("Indicadores");
		tbIndicadores.setTitleJustification(TitledBorder.CENTER);
		panelSuperior.setBorder(tbIndicadores);

		TitledBorder tbMensaje = BorderFactory.createTitledBorder("Mensaje");
		tbMensaje.setTitleJustification(TitledBorder.CENTER);
		panelInferior.setBorder(tbMensaje);

		panelSuperior.setPreferredSize(new Dimension(490, 100));
		panelInferior.setPreferredSize(new Dimension(490, 260));

		// JLabels //
		lblVerde = new JLabel("LED VERDE", SwingConstants.CENTER);
		lblAmarillo = new JLabel("LED AMARILLO", SwingConstants.CENTER);
		lblRojo = new JLabel("LED ROJO", SwingConstants.CENTER);

		lblVerde.setPreferredSize(new Dimension(150, 65));
		lblAmarillo.setPreferredSize(new Dimension(150, 65));
		lblRojo.setPreferredSize(new Dimension(150, 65));

		lblVerde.setBackground(new Color(255, 200, 200));
		lblAmarillo.setBackground(new Color(255, 200, 200));
		lblRojo.setBackground(new Color(255, 200, 200));

		lblVerde.setOpaque(true);
		lblAmarillo.setOpaque(true);
		lblRojo.setOpaque(true);

		// JScrollPane + JTextArea //
		txtArea = new JTextArea(14, 43);
		JScrollPane sclArea = new JScrollPane(txtArea);

		// Add objects //

		panelSuperior.add(lblVerde);
		panelSuperior.add(lblAmarillo);
		panelSuperior.add(lblRojo);
		panelInferior.add(sclArea, BorderLayout.CENTER);

		add(panelSuperior);
		add(panelInferior);

		setVisible(true);

		iniciarServicio();
	}

	private void iniciarServicio() {
		ManejadorPeticiones mp1 = new ManejadorPeticiones();
		ServerSocket socketServidor = null;
		try {
			socketServidor = new ServerSocket(5000);

			Socket socketCliente;
			while (true) {

				socketCliente = socketServidor.accept();

				// Nuevo Hilo //
				Thread hilo = new Thread(new ManejadorPeticiones(socketCliente, this));
				hilo.start();
				// cambiar color //

				mensaje = mp1.getMensaje();

				Thread.sleep(10);
			}
		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
		}
	}

	public JTextArea getTxtMensajes() {
		return txtArea;
	}

	public void cambiarColor(String mensaje) {
		if (mensaje.equals("000")) {
			lblVerde.setBackground(new Color(255, 200, 200));
			lblAmarillo.setBackground(new Color(255, 200, 200));
			lblRojo.setBackground(new Color(255, 200, 200));
		} else if (mensaje.equals("001")) {
			lblVerde.setBackground(new Color(200, 255, 200));
			lblAmarillo.setBackground(new Color(255, 200, 200));
			lblRojo.setBackground(new Color(255, 200, 200));
		} else if (mensaje.equals("010")) {
			lblVerde.setBackground(new Color(255, 200, 200));
			lblAmarillo.setBackground(new Color(200, 255, 200));
			lblRojo.setBackground(new Color(255, 200, 200));
		} else if (mensaje.equals("011")) {
			lblVerde.setBackground(new Color(200, 255, 200));
			lblAmarillo.setBackground(new Color(200, 255, 200));
			lblRojo.setBackground(new Color(255, 200, 200));
		} else if (mensaje.equals("100")) {
			lblVerde.setBackground(new Color(255, 200, 200));
			lblAmarillo.setBackground(new Color(255, 200, 200));
			lblRojo.setBackground(new Color(200, 255, 200));
		} else if (mensaje.equals("101")) {
			lblVerde.setBackground(new Color(200, 255, 200));
			lblAmarillo.setBackground(new Color(255, 200, 200));
			lblRojo.setBackground(new Color(200, 255, 200));
		} else if (mensaje.equals("110")) {
			lblVerde.setBackground(new Color(255, 200, 200));
			lblAmarillo.setBackground(new Color(200, 255, 200));
			lblRojo.setBackground(new Color(200, 255, 200));
		} else if (mensaje.equals("111")) {
			lblVerde.setBackground(new Color(200, 255, 200));
			lblAmarillo.setBackground(new Color(200, 255, 200));
			lblRojo.setBackground(new Color(200, 255, 200));
		}
	}

	public void setTxtMensajes(JTextArea txtMensajes) {
		this.txtArea = txtMensajes;
	}

	public static void main(String[] args) {
		new Servidor();
	}

}
