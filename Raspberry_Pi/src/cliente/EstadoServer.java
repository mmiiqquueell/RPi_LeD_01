package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketAddress;

public class EstadoServer implements Runnable {

	private Software_Cliente cliente;
	private Socket socketServidor;
	private String isOnline = "0";

	public EstadoServer() {
	}

	public EstadoServer(Socket socketCliente, Software_Cliente cliente) {
		this.socketServidor = socketCliente;
		this.cliente = cliente;
	}

	@Override
	public void run() {

		// Creamos buffer //

		BufferedReader buffer = null;
		isOnline = "0";
		try {
			buffer = new BufferedReader(new InputStreamReader(socketServidor.getInputStream()));

			SocketAddress ipServer = socketServidor.getRemoteSocketAddress();
			String ip = ipServer.toString();
			String[] campos = ip.split(":");
			ip = campos[0].substring(1);
			cliente.enLinea(isOnline);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getIsOnline() {
		return isOnline;
	}

}