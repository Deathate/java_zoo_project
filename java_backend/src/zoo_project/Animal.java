package zoo_project;

public class Animal {
    String name;
    String habitat;
    String path;
    String content;

    public Animal(String name, String profilePath, String content) {
        this.name = name;
        try {
            this.path = util.saveImage(profilePath);
        } catch (Exception e) {
            this.path = System.getProperty("user.dir") + "\\src\\zoo_project\\static\\" + profilePath;
        }
        this.content = "        " + content;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public void setHabitat(Zone habitat) {
        this.habitat = habitat.name;
    }
}
