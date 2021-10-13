package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author 5442
 */
public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		final String HOST = "127.0.0.1";
		final int PORT = 4040;

		System.out.println("Client started.");

		try (Socket socket = new Socket(HOST, PORT);
				DataOutputStream ostream = new DataOutputStream(socket.getOutputStream());
				DataInputStream istream = new DataInputStream(socket.getInputStream());) {
			while (true) {
				System.out.println(istream.readUTF());
				
				TimeUnit.SECONDS.sleep(5);
				ostream.writeUTF("hora do client: " + getTime());
				ostream.flush();

			}
		}
	}

	private static String getTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		String stringTime = sdf.format(date);
		return stringTime;
	}

}