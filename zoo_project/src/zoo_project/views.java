package zoo_project;

import java.io.IOException;

public class views {

	public static void main(String[] args) throws IOException {
		Server mailboxServer = new Server(5080);
		new models();
		while (true) {
			Client c = mailboxServer.newEntry();
			c.welcome();
			c.sendMessage(router.dispatch(c.getMessage()));
			// System.out.println(c.getMessage());
			// String path = "C:/Users/aaron/Desktop/download.jpg";
			// Mail mail = new Mail();
			// mail.setImage(path);
			// mail.name = "bamboo";
			// c.sendMessage(mail);
		}
	}

}
