package zoo_project;

import java.util.ArrayList;
import java.util.List;

public class Zone {
    String name;
    String path;
    List<Animal> animals = new ArrayList<>();
    // List<Linemap> linemaps = new ArrayList<>();

    public Zone(String name, String imgPath) {
        this.name = name;
        try {
            this.path = util.saveImage(imgPath);
        } catch (Exception e) {
            this.path = System.getProperty("user.dir") + "\\src\\zoo_project\\static\\" + imgPath;
        }
    }

    public String getName() {
        return this.name;
    }

    public void addAnimal(List<Animal> animals) {
        for (var animal : animals) {
            this.animals.add(animal);
            animal.setHabitat(this);
        }
    }

    // public void addLinemap(Linemap linemap) {
    // this.linemaps.add(linemap);
    // }

    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public String toString() {
        return name;
    }
}
