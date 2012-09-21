package ru.iteco.foraspecting;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 20.09.12
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */
public class TestWeave {


    public static void main(String ...args) {

        TestWeave testWeave = new TestWeave();

        testWeave.getAs();
    }

    String as ;


    public TestWeave(){
           as = "qqqqqqq";
        System.out.println("constructor:" + as);
    }

    public String getAs() {
        System.out.println("get:" + as);
        return as;
    }

    public void setAs(String as) {
        System.out.println("set:" + as);
        this.as = as;
    }
}
