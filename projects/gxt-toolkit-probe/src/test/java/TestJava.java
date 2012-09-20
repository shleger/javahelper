import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 29.08.12
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public class TestJava {

    @Test
    public void runIt(){
        System.out.println("start");

        Integer one = 0x11;
        System.out.println(Integer.toBinaryString(one));

        Integer two = 0x10;
        System.out.println(Integer.toBinaryString(two));



        one |=  two;

        System.out.println(Integer.toBinaryString(one));


        boolean oo = false;
        boolean bb = true;

        oo|=bb;

        System.out.println("TestJava.runIt: " + oo);

    }
}
