import java.util.List;
/**
 * Write a description of class Actor here.
 *
 * @author Eric Wallace
 * @version 1
 */
public interface Actor
{
    /**
     * Perform the actor's regular behavior.
     * @param newActors A list for receiving newly created actors.
     */
    void act(List<Actor> newActors);
    
    /**
     * Is the actor still active?
     * @return true if still active, false if not.
     */
    boolean isActive();
}
