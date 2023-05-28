package zoo_project;

public class Linemap {
    Zone source;
    Zone destination;
    String path;

    public Linemap(Zone source, Zone destination, String path) {
        this.source = source;
        this.destination = destination;
        try {
            this.path = util.saveImage(path);
        } catch (Exception e) {
            this.path = System.getProperty("user.dir") + "\\src\\zoo_project\\static\\" + path;
        }
    }
}
