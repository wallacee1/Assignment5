import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    public static void main(String[] args)
    {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        Random random = new Random();
        for(int i = 0; i < 100; i++) {
            arrayList.add(random.nextInt());
            linkedList.add(random.nextInt());
        }
        ListEfficiencyTester tester = new ListEfficiencyTester(arrayList,100000);
        System.out.println("ArrayList results:");
        tester.test();
        tester.setList(linkedList);
        System.out.println("LinkedList results:");
        tester.test();
    }
}
