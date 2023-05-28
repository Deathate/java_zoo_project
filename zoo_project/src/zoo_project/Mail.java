package zoo_project;

import java.io.*;
import java.util.Base64;
import java.util.List;
import com.google.gson.Gson;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Mail {
	public String name;
	public String habitat;
	public Object status;
	public Object body;
	public String zone;
	public Integer price;
	public String id;
	String image_format;
	String image;
	public String image_url;
	public List<Ticket> tickets;
	public List<Animal> animals;

	public String toJson() {
		return new Gson().toJson(this);
	}

	public void setImage(String path) throws IOException {
		File imageFile = new File(path);
		// Create a BufferedImage object from the JPG image.
		BufferedImage bufferedImage = ImageIO.read(imageFile);
		// Convert the BufferedImage object to a byte[] array.
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, path.substring(path.length() - 3), baos);
		Base64.Encoder base64 = Base64.getEncoder();
		this.image_format = path.substring(path.length() - 3);
		this.image = base64.encodeToString(baos.toByteArray());
	}
}
