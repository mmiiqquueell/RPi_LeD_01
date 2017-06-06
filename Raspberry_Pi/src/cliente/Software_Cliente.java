package cliente;

/** MCC-VIDEOS: Licencia Creative Commons
 * Reconocimiento – NoComercial – CompartirIgual [CC](by-nc-sa): 
 * No se permite un uso comercial de la obra original ni de 
 * las posibles obras derivadas, la distribución de las cuales 
 * se debe hacer con una licencia igual a la que regula la obra 
 * original.**/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Software_Cliente extends JFrame implements ActionListener {

	private JButton btnR, btnY, btnG, btnIP;
	private JTextArea txtLog;
	private JLabel lblCamera, lblIsOnline;
	private JTextField ip;
	private int re = 0, yello = 0, gree = 0, isOnline = 0;
	private String Binario;
	// private JLabel led000 = new JLabel(new
	// ImageIcon(getClass().getResource("/imagenes/0001.jpg")));
	// private JLabel led001 = new JLabel(new
	// ImageIcon(getClass().getResource("/imagenes/0002.jpg")));
	// private JLabel led010 = new JLabel(new
	// ImageIcon(getClass().getResource("/imagenes/0003.jpg")));
	// private JLabel led011 = new JLabel(new
	// ImageIcon(getClass().getResource("/imagenes/0004.jpg")));
	// private JLabel led100 = new JLabel(new
	// ImageIcon(getClass().getResource("/imagenes/0005.jpg")));
	// private JLabel led101 = new JLabel(new
	// ImageIcon(getClass().getResource("/imagenes/0006.jpg")));
	// private JLabel led110 = new JLabel(new
	// ImageIcon(getClass().getResource("/imagenes/0007.jpg")));
	// private JLabel led111 = new JLabel(new
	// ImageIcon(getClass().getResource("/imagenes/0008.jpg")));
	private String direccionIP;

	public static void main(String[] args) {
		new Software_Cliente();

	}

	public Software_Cliente() {
		super("Led controler");
		setSize(600, 250); // setSize(600, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);

		JLabel lblInformation = new JLabel(
				"Led controler: Press any button for turn on or off one light. Be patient, the server take some time...");
		lblInformation.setPreferredSize(new Dimension(580, 20));
		JPanel panelButton = new JPanel();
		panelButton.setPreferredSize(new Dimension(255, 120));
		btnR = new JButton("RED LED");
		btnR.setPreferredSize(new Dimension(250, 35));
		btnY = new JButton("YELLOW LED");
		btnY.setPreferredSize(new Dimension(250, 35));
		btnG = new JButton("GREEN LED");
		btnG.setPreferredSize(new Dimension(250, 35));
		JPanel panelArea = new JPanel();
		panelArea.setPreferredSize(new Dimension(255, 120));
		txtLog = new JTextArea(6, 20);
		JScrollPane splog = new JScrollPane(txtLog);
		splog.setPreferredSize(new Dimension(255, 116));

		ip = new JTextField("127.0.0.1", 37);
		btnIP = new JButton("Connectar");

		btnR.setPreferredSize(new Dimension(250, 35));
		lblCamera = new JLabel("CAM: disabled");
		lblIsOnline = new JLabel("Server is OFFLine", SwingConstants.CENTER);
		lblIsOnline.setPreferredSize(new Dimension(150, 20));

		// led000.setVisible(true);
		// led001.setVisible(false);
		// led010.setVisible(false);
		// led011.setVisible(false);
		// led100.setVisible(false);
		// led101.setVisible(false);
		// led110.setVisible(false);
		// led111.setVisible(false);

		add(lblInformation);
		panelButton.add(btnR);
		panelButton.add(btnY);
		panelButton.add(btnG);
		add(panelButton);
		panelArea.add(splog);
		add(panelArea);
		add(ip);
		add(btnIP);
		add(lblCamera);
		add(lblIsOnline);
		// add(led000);
		// add(led001);
		// add(led010);
		// add(led011);
		// add(led100);
		// add(led101);
		// add(led110);
		// add(led111);

		btnR.setOpaque(true);
		btnR.setFocusable(false);
		btnR.setBackground(new Color(255, 200, 200));

		btnY.setOpaque(true);
		btnY.setFocusable(false);
		btnY.setBackground(new Color(255, 200, 200));

		btnG.setOpaque(true);
		btnG.setFocusable(false);
		btnG.setBackground(new Color(255, 200, 200));

		btnR.addActionListener(this);
		btnY.addActionListener(this);
		btnG.addActionListener(this);
		btnIP.addActionListener(this);

		lblIsOnline.setOpaque(true);
		lblIsOnline.setFocusable(false);
		lblIsOnline.setBackground(new Color(255, 0, 0));

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object boton = arg0.getSource();

		if (boton == btnIP) {
			isOnline = 1;
			direccionIP = ip.getText();
			txtLog.append("\n\nConectando a " + direccionIP);
		}
		if (isOnline == 0) {
			lblIsOnline.setBackground(new Color(255, 0, 0));
			lblIsOnline.setText("Server is OFFLine");
			txtLog.append("\nEl servidor no está conectado.\n");
		}
		if (isOnline == 1) {

			lblIsOnline.setBackground(new Color(0, 255, 0));
			lblIsOnline.setText("Server is ONLine");

			// // // // // // // // // // // // // // // //
			if (re == 0 && boton == btnR) {

				btnR.setBackground(new Color(200, 255, 200));
				re = 1;

			} else if (re == 1 && boton == btnR) {
				btnR.setBackground(new Color(255, 200, 200));
				re = 0;
			}

			// // // // // // // // // // // // // // // //
			if (yello == 0 && boton == btnY) {
				btnY.setBackground(new Color(200, 255, 200));
				yello = 1;
			} else if (yello == 1 && boton == btnY) {
				btnY.setBackground(new Color(255, 200, 200));
				yello = 0;
			}

			// // // // // // // // // // // // // // // //
			if (gree == 0 && boton == btnG) {
				btnG.setBackground(new Color(200, 255, 200));
				gree = 1;
			} else if (gree == 1 && boton == btnG) {
				btnG.setBackground(new Color(255, 200, 200));
				gree = 0;
			}

			Binario = String.valueOf(re) + String.valueOf(yello) + String.valueOf(gree);

			// led000.setVisible(false);
			// led001.setVisible(false);
			// led010.setVisible(false);
			// led011.setVisible(false);
			// led100.setVisible(false);
			// led101.setVisible(false);
			// led110.setVisible(false);
			// led111.setVisible(false);
			//
			// if (Binario.equals("000")) {
			// led000.setVisible(true);
			// } else if (Binario.equals("001")) {
			// led001.setVisible(true);
			// } else if (Binario.equals("010")) {
			// led010.setVisible(true);
			// } else if (Binario.equals("011")) {
			// led011.setVisible(true);
			// } else if (Binario.equals("100")) {
			// led100.setVisible(true);
			// } else if (Binario.equals("101")) {
			// led101.setVisible(true);
			// } else if (Binario.equals("110")) {
			// led110.setVisible(true);
			// } else if (Binario.equals("111")) {
			// led111.setVisible(true);
			// }

			enviarMensaje(Binario);

			txtLog.append("\n\nC: Petición Code #" + Binario + "\nS: Respuesta Code #" + Binario + " aceptada.");
		}
	}

	private void enviarMensaje(String mensaje) {

		PrintStream salida = null;
		Socket socket = null;

		try {

			socket = new Socket();
			socket.connect(new InetSocketAddress(direccionIP, 5000), 1000);
			salida = new PrintStream(socket.getOutputStream());
			salida.println(mensaje);

		} catch (Exception e) {
			isOnline = 0;
			txtLog.append("\nEl servidor no está conectado.");
			lblIsOnline.setBackground(new Color(255, 0, 0));
			lblIsOnline.setText("Server is OFFLine");
		} finally {
			salida.close();
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Se ha producido un error.");

			}
		}

	}

}
