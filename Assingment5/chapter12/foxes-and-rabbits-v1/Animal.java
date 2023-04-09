
/**
 * Write a description of class Animal here.
 *
 * @author Eric Wallace 
 * @version 1
 */
public abstract class Animal
{
    // instance variables - replace the example below with your own
    private boolean alive;
    private Location location;
    private Field field;

    /**
     * Constructor for objects of class Animal
     */
    public Animal(Field field, Location location)
    {
        // initialise instance variables
        alive = true;
        this.field = field;
        setLocation(location);
    }
    
    abstract public void act(List<Animal> newAnimals);

    protected boolean isAlive()
    {
        return alive;
    }
    
    protected Location getLocation()
    {
        return location;
    }
    
    protected void setLocation(Location newLocation)
    {
        this.location = location;
    }
    
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }
    
    protected Field getField()
    {
        return field;
    }
    
}
