
/**
 * Write a description of class PopulationGenerator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PopulationGenerator
{
    // instance variables - replace the example below with your own
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;
    
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
    }
    
    public List<Animal> populate(Random rand)
    {
        List<Animal> list = new ArrayList<>();
        field.clear();
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
            }
        }
        return list;
    }
    
}
