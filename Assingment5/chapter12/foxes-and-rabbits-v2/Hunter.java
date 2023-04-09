import java.util.List;
import java.util.Random;
/**
 * Write a description of class Hunter here.
 *
 * @author Eric Wallace
 * @version 1
 */
public class Hunter implements Actor
{
    // instance variables - replace the example below with your own
    private final Field field;
    private Location location;
    private static final Random random = new Random();
    private static final int NUMBER_OF_SHOTS = 4;
    private boolean active;
    
    /**
     * Constructor for the Hunter.
     */
    public Hunter(Field field, Location location)
    {
        this.field = field;
        setLocation(location);
        active = true;
    }
    
    @Override
    public void act(List<Actor> newActors)
    {
        Location newLocation = getRandomLocation();
        Object obj = field.getObjectAt(newLocation);
        if(obj instanceof Animal) {
            Animal animal = (Animal) obj;
            kill(animal);
        }
        
        if(!(obj instanceof Hunter)) {
            setLocation(newLocation);
        }
        
        List<Location> adjacent = field.adjacentLocations(location);
        shoot(adjacent);
    }
    
    private void shoot(List<Location> locations)
    {
        for(int i = 0; i < NUMBER_OF_SHOTS; i++) {
            Location loc = locations.get(random.nextInt(locations.size()));
            Object obj = field.getObjectAt(loc);
            if(obj instanceof Animal) {
                Animal animal = (Animal) obj;
                kill(animal);
            }
        }
    }
    
    private void kill(Animal animal)
    {
        animal.setDead();
    }
    
    /**
     * Return the Hunter's location.
     * @return The hunter's location.
     */
    protected Location getLocation()
    {
        return location;
    }
    
    /**
     * Place the hunter at the new location in the given field.
     * @param newLocation The hunter's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    private Location getRandomLocation()
    {
        Location location;
        int x = random.nextInt(field.getWidth());
        int y = random.nextInt(field.getDepth());
        location = new Location(y, x);
        return location;
    }
    
    @Override
    public boolean isActive()
    {
        return active;
    }
}
