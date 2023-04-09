import java.awt.Color;
/**
 * Write a description of class TextView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TextView implements SimulatorView
{
    // instance variables - replace the example below with your own
    private FieldStats stats;

    /**
     * Constructor for objects of class TextView
     */
    public TextView()
    {
        // initialise instance variables
        stats = new FieldStats();
        System.out.println("Textual view of the Fox and Rabbit Simulation");
    }
    
    public boolean isViable(Field field)
    {
        return stats.isViable(field);
    }
    
    public void reset()
    {
        stats.reset();
    }
    
    public void setColor(Class<?> animalClass, Color color)
    {
        
    }
    
    public void showStatus(int step, Field field)
    {
        stats.reset();
        System.out.println("Foxes: " + stats.getPopulationCount(field, Fox.class) + "\nRabbits: " + stats.getPopulationCount(field, Rabbit.class));
    }
}
