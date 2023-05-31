package zoo_project;

import java.io.*;
import java.net.*;

public class Client {
	Socket socket;
	String messageString;

	public Client(Socket s) {
		socket = s;
	}

	public void welcome() {
		System.out.println(" THE CLIENT" + " " +
				socket.getInetAddress() + ":" + socket.getPort() + " IS CONNECTED ");
	}

	public String getMessage() throws IOException {
		InputStream inputStream = socket.getInputStream();
		byte[] message = new byte[1024];
		int bytesRead = inputStream.read(message);
		messageString = new String(message, 0, bytesRead);
		return messageString;
	}

	public void sendMessage(String response) throws IOException {
		OutputStream outputStream = socket.getOutputStream();
		// System.out.print(response.getBytes("utf8").length);
		var byteResponse = response.getBytes("utf8");
		// outputStream.write((byte) byteResponse.length);
		// BigInteger bigInt = BigInteger.valueOf(byteResponse.length);
		// byte[] big2 =
		// ByteBuffer.allocate(4).put(BigInteger.valueOf(byteResponse.length).toByteArray()).array();
		// System.out.print(String.format("%8s",
		// System.out.println(byteResponse.length);
		outputStream.write(String.format("%8s", Integer.toString(byteResponse.length)).getBytes("utf8"));
		outputStream.write(byteResponse);
	}
}