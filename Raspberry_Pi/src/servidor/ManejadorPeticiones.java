package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ManejadorPeticiones implements Runnable {

	private Socket socketCliente;
	private Servidor ventana;
	private static String mensaje = "000", ip, isOnline = "0";

	public ManejadorPeticiones() {
	}

	public ManejadorPeticiones(Socket socketCliente, Servidor ventana) {
		this.socketCliente = socketCliente;
		this.ventana = ventana;
	}

	@Override
	public void run() {
		ventana.getTxtMensajes().append("Atendiendo petición: \n");

		// Creamos buffer //

		BufferedReader buffer = null;
		PrintStream salida = null;
		Socket socket = null;
		mensaje = "000";
		try {
			buffer = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

			mensaje = buffer.readLine();

			SocketAddress ipCliente = socketCliente.getRemoteSocketAddress();
			ip = ipCliente.toString();
			String[] campos = ip.split(":");
			ip = campos[0].substring(1);

			ventana.getTxtMensajes().append(ip + ": " + mensaje + "\n\n");
			ventana.cambiarColor(mensaje);

			socket = new Socket();
			socket.connect(new InetSocketAddress(ip, 5001), 1000);

			salida = new PrintStream(socket.getOutputStream());
			salida.println(isOnline);
			salida.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public String getMensaje() {
		return mensaje;
	}

}
