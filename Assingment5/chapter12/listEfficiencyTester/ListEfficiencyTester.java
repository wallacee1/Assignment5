import java.util.List;
/**
 * Write a description of class ListEfficiencyTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ListEfficiencyTester
{
    // instance variables - replace the example below with your own
    private List list;
    private int testItemSize;

        
    public ListEfficiencyTester(List list, int testItemSize)
    {
        this.list = list;
        this.testItemSize = testItemSize;
    }
    
    public long testAdd() 
    {
        System.out.println("Testing add on " + testItemSize + " elements at the end of the list");
        long startTime = System.currentTimeMillis();
        for (int i = 0;i < testItemSize; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        System.out.println("Result:" + result);
        return result;
    }
    
    public long testInsert()
    {
        System.out.println("Testing insert of " + testItemSize + " elements");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testItemSize; i++) {
            list.add(i, i);
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        System.out.println("Result:" + result);
        return result;
    }
    
    public long testRemoveByValue()
    {
        System.out.println("Testing removal of " + testItemSize + " elements");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testItemSize; i++) {
            Object el = list.remove(new Integer(i));
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        System.out.println("Result:" + result);
        return result;
    }
    
    public long testGetByIndex()
    {
        System.out.println("Testing get on " + testItemSize + " elements by index");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testItemSize; i++) {
            Object el = list.get(i);
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        System.out.println("Result:" + result);
        return result;
    }
    
    public long testRemoveByIndex()
    {
        System.out.println("Testing removal of " + testItemSize + " elements by index");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testItemSize; i++) {
            Object el = list.remove(i);
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        System.out.println("Result:" + result);
        return result;
    }
    
    public List getList()
    {
        return list;
    }
    
    public void setList(List list)
    {
        this.list = list;
    }
    
    public int getTestItemSize()
    {
        return testItemSize;
    }
    
    public void test()
    {
        testAdd();
        testInsert();
        testGetByIndex();
        testRemoveByIndex();
        testRemoveByValue();
    }
}
