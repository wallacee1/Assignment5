import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field containing 
 * rabbits and foxes.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;    

    // Lists of animals in the field.
    private List<Rabbit> rabbits;
    private List<Fox> foxes;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    
    private PopulationGenerator generator;
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be >= zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        rabbits = new ArrayList<>();
        foxes = new ArrayList<>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        generator = new PopulationGenerator(field, view);
        
        // Setup a valid starting point.
        reset();
    }
    
    /**
     * Run the simulation from its current state for a reasonably long 
     * period (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }
    
    /**
     * Run the simulation for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step=1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
            // delay(60);   // uncomment this to run more slowly
        }
    }
    
    /**
     * Run the simulation from its current state for a single step. Iterate
     * over the whole field updating the state of each fox and rabbit.
     */
    public void simulateOneStep()
    {
        ++this.step;

        // Provide space for newborn rabbits.
        List<Rabbit> newRabbits = new ArrayList<>();
        Iterator it = this.rabbits.iterator();
        // Let all rabbits act.
        while(it.hasNext()) {
            Rabbit rabbit = (Rabbit)it.next();
            rabbit.run(newRabbits);
            if(!rabbit.isAlive()) {
                it.remove();
            }
        }
        
        // Provide space for newborn foxes.
        List<Fox> newFoxes = new ArrayList<>();  
        Iterator ot = this.foxes.iterator();
        // Let all foxes act.
        while(it.hasNext()) {
            Fox fox = (Fox)it.next();
            fox.hunt(newFoxes);
            if(!fox.isAlive()) {
                it.remove();
            }
        }
        
        // Add the newly born foxes and rabbits to the main lists.
        this.rabbits.addAll(newRabbits);
        this.foxes.addAll(newFoxes);
        this.view.showStatus(this.step, this.field);
        test();
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        rabbits.clear();
        foxes.clear();
        populate();
        
        // Show the starting state in the view.
        view.showStatus(step, field);
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        animals = generator.populate(rand);
    }
    
    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec)
    {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
    
    public void test()
    {
        boolean animalsInField = false;
        for (int i = 0; i < field.getDepth(); i++) {
            for(int j = 0; j < field.getWidth(); j++) {
                Object animal = field.getObjectAt(i, j);
                if (!foxes.contains(animal) && !rabbits.contains(animal)) {
                    animalsInField = true;
                    break;
                }
            }
            if(animalsInField) {
                break;
            }
        }
        boolean animalsInLists = false;
        for(Fox f : foxes) {
            if (f == null || f.getLocation() == null || !field.getObjectAt(f.getLocation()).equals(f)) {
                animalsInLists = true;
                break;
            }
        }
        if (!animalsInLists) {
            for(Rabbit r : rabbits) {
                if (r == null || r.getLocation() == null || !field.getObjectAt(r.getLocation()).equals(r)){
                    animalsInLists = true;
                    break;
                }
            }
        }
        System.out.println("There are " + ((animalsInField)? "" : "NO ") + "animals in the field which are not in the lists.");
        System.out.println("There are " + ((animalsInLists)? "" : "NO ") + "animals in the lists which are not in the field.");
    }
}
