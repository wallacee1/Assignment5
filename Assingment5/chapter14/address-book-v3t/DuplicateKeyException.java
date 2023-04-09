
/**
 * Write a description of class DuplicateKeyException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DuplicateKeyException extends Exception
{
    // instance variables - replace the example below with your own
    private String key;

    /**
     * Constructor for objects of class DuplicateKeyException
     */
    public DuplicateKeyException(String key)
    {
        // initialise instance variables
        this.key = key;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getKey()
    {
        // put your code here
        return key;
    }
    
    public String toString()
    {
        return "Duplicate key: " + key + ".";
    }
}
