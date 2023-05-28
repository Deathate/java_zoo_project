package zoo_project;

public class TicketInfo {
    public enum TicketType {
        ENTRANCE,
        EXPERIENCE
    };

    String content;
    String name;
    Zone zone;
    String path;
    int price;
    TicketType kind;
    String qrcode;
    String type;
    boolean isEmpty;

    public TicketInfo(String name, String content, Zone zone, String imgPath, int price, TicketType kind,
            String qrcode, String type) {
        this.name = name;
        this.content = content;
        // this.zone = zone;
        setPath(imgPath);
        this.price = price;
        this.kind = kind;
        try {
            this.qrcode = util.saveImage(qrcode);
        } catch (Exception e) {
            this.qrcode = System.getProperty("user.dir") + "\\src\\zoo_project\\static\\" + qrcode;
        }
        this.type = type;
    }

    public void setPath(String path) {
        try {
            this.path = util.saveImage(path);
        } catch (Exception e) {
            if (path != null && !path.isBlank()) {
                this.path = System.getProperty("user.dir") + "\\src\\zoo_project\\static\\" + path;
            } else {
                this.path = "";
                this.isEmpty = true;
            }
        }
    }
}
