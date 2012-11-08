package ru.saa.sample.gxt.client.grid;

import com.google.gwt.i18n.shared.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 08.11.12
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class TestData {

    public static List<Employee> getEmployees()
    {
        List<Employee> employees = new ArrayList<Employee>();
        DateTimeFormat f = DateTimeFormat.getFormat("yyyy-mm-dd");
        employees.add(new Employee("Hollie Voss","General Administration","Executive Dir  ector",150000,f.parse("2006-05-01")));
        employees.add(new Employee("Emerson Milton","Information Technology","CTO",120000,f.parse("2007-03-01")));
        employees.add(new Employee("Christina Blake","Information Technology","Project M  anager",90000,f.parse("2008-08-01")));
        employees.add(new Employee("Heriberto Rush","Information Technology","Senior S/W  Engineer",70000,f.parse("2009-02-07")));
        employees.add(new Employee("Candice Carson","Information Technology","S/W Engine  er",60000,f.parse("2007-11-01")));
        employees.add(new Employee("Chad Andrews","Information Technology","Senior S/W E  ngineer",70000,f.parse("2008-02-01")));
        employees.add(new Employee("Dirk Newman","Information Technology","S/W Engineer"  ,62000,f.parse("2009-03-01")));
        employees.add(new Employee("Bell Snedden","Information Technology","S/W Engineer  ",73000,f.parse("2007-07-07")));
        employees.add(new Employee("Benito Meeks","Marketing","General Manager",105000,f  .parse("2008-02-01")));
        employees.add(new Employee("Gail Horton","Marketing","Executive",55000,f.parse("  2009-05-01")));
        employees.add(new Employee("Claudio Engle","Marketing","Executive",58000,f.parse  ("2008-09-03")));
        employees.add(new Employee("Buster misjenou","Accounts","Executive",52000,f.parse("2008-02-07")));

        return employees;
    }
}
