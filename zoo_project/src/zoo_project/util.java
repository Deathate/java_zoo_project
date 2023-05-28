package zoo_project;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

import javax.imageio.ImageIO;

public class util {
    public static String image2Byte(String path) throws IOException {
        File imageFile = new File(path);
        // Create a BufferedImage object from the JPG image.
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        // Convert the BufferedImage object to a byte[] array.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, path.substring(path.length() - 3), baos);
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public static String saveImage(String url) throws IOException {
        URL link = new URL(url);
        BufferedImage bufferedImage = ImageIO.read(link);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String[] seg = url.toString().split("/");
        String name = seg[seg.length - 1];
        ImageIO.write(bufferedImage, name.substring(name.length() - 3), baos);
        String filePath = System.getProperty("user.dir") + "\\src\\zoo_project\\static\\" + name;
        FileOutputStream fos = new FileOutputStream(
                filePath);
        fos.write(baos.toByteArray());
        fos.close();
        return filePath;
    }
}
