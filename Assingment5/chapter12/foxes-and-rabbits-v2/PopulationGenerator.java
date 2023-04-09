import java.awt.Color;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

// Your other imports here

/**
 * Write a description of class PopulationGenerator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PopulationGenerator
{
    private Field field;
    private SimulatorView view;
    
    /**
     * Constructor for objects of class PopulationGenerator
     */
    public PopulationGenerator(Field field, SimulatorView view)
    {
        this.field = field;
        this.view = view;
        setColors();
    }

    private void setColors()
    {
        view.setColor(Rabbit.class, Color.ORANGE);
        view.setColor(Fox.class, Color.BLUE);
        view.setColor(MountainLion.class, Color.GREEN);
    }
    
    public List<Animal> populate(double FOX_CREATION_PROBABILITY, double RABBIT_CREATION_PROBABILITY, double MOUNTAINLION_CREATION_PROBABILITY)
    {
        List<Animal> list = new ArrayList<>();
        field.clear();
        Random rand = Randomizer.getRandom();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Location location = new Location(row, col);
                
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Fox fox = new Fox(true, field, location);
                    list.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Rabbit rabbit = new Rabbit(true, field, location);
                    list.add(rabbit);
                }
                else if(rand.nextDouble() <= MOUNTAINLION_CREATION_PROBABILITY) {
                    MountainLion mountainlion = new MountainLion(true, field, location);
                    list.add(mountainlion);
                }
            }
        }
        return list;
    }
    
}
