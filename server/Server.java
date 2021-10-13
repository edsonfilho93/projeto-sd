package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	public static void main(String[] args) throws IOException {
		final int PORT = 4040;

		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server started...");

		while (true) {
			Socket clientSocket = serverSocket.accept();
			System.out.println("Clients connected...");

			Thread t = new Thread() {
				public void run() {
					try (DataOutputStream ostream = new DataOutputStream(clientSocket.getOutputStream());
							DataInputStream istream = new DataInputStream(clientSocket.getInputStream());

					) {
						while (true) {
							ostream.writeUTF("hora do server: " + getTime());
							ostream.flush();

							System.out.println(istream.readUTF());
						}
					} catch (IOException e) {
					}
				}
			};
			t.start();
		}
	}

	private static String getTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		String stringTime = sdf.format(date);
		return stringTime;
	}
}