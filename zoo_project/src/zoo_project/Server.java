package zoo_project;

import java.io.*;
import java.net.*;

public class Server {
	ServerSocket Server;

	public Server(int port) throws IOException {
		Server = new ServerSocket(port);
		System.out.println(String.format("TCPServer Waiting for client on port %d",port));
	}

	public Client newEntry() throws IOException {
		Socket connected = Server.accept();
		return new Client(connected);
	}
}
