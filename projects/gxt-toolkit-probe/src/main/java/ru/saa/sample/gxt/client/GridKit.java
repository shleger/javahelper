package ru.saa.sample.gxt.client;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.widget.client.TextButton;
import ru.saa.sample.gxt.client.grid.Employee;
import ru.saa.sample.gxt.client.grid.TestData;
import ru.saa.sample.gxt.client.internationalization.LangConstants;
import ru.saa.sample.gxt.client.internationalization.LangConstantsMessages;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * @see  <url>https://developers.google.com/web-toolkit/doc/latest/DevGuideUiBinder#Hello_World</url>
 *
 */
public class GridKit implements EntryPoint {
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

//    private final Messages messages = GWT.create(Messages.class);
    private LangConstants constants = GWT.create(LangConstants.class);
    private LangConstantsMessages messages = GWT.create(LangConstantsMessages.class);

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        final TextButton textButton = new TextButton("gxtSimpleButton");

        LayoutContainer container = new LayoutContainer();

        container.setLayout(new FlowLayout(10));



        // Add the nameField and sendButton to the RootPanel
        // Use RootPanel.get() to get the entire body element

        // GXT button

        PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(TestData.getEmployees());
        PagingLoader loader = new BasePagingLoader(proxy);
        loader.setRemoteSort(true);
        ListStore<Employee> employeeList = new ListStore<Employee>(loader);

        final PagingToolBar toolBar = new PagingToolBar(5);
        toolBar.bind(loader);
        loader.load(0, 5);

        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setId("name");
        column.setHeader("Employee Name");
        column.setWidth(200);
        configs.add(column);

        column = new ColumnConfig("department", "Department", 150);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig("designation", "Designation", 150);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig("salary", "Slary", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        final NumberFormat number = NumberFormat.getFormat("0.00");
        GridCellRenderer<Employee> checkSalary = new GridCellRenderer<Employee>() {

            public Object render(Employee employee, String property, ColumnData columnData, int i, int i1, ListStore<Employee> employeeListStore, com.extjs.gxt.ui.client.widget.grid.Grid<Employee> employeeGrid) {
                double val = (Double) employee.get(property);
                String style = val < 70000 ? "red" : "green";
                return "<span style='color:" + style + "'>" + number.format(val) + "</span>";
            }
        };
        column.setRenderer(checkSalary);
        configs.add(column);

        column = new ColumnConfig("joiningdate", "Joining Date", 100);
        column.setAlignment(Style.HorizontalAlignment.RIGHT);
        column.setDateTimeFormat(DateTimeFormat.getShortDateFormat());
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);
        Grid<Employee> grid = new Grid<Employee>(employeeList, cm);
        grid.setStyleAttribute("borderTop", "none");
        grid.setAutoExpandColumn("name");
        grid.setBorders(true);
        grid.setStripeRows(true);

        ContentPanel cp = new ContentPanel();
        cp.setBodyBorder(false);
        cp.setHeading("Employee List");
        cp.setButtonAlign(Style.HorizontalAlignment.CENTER);
        cp.setLayout(new FitLayout());
        cp.setSize(700, 300);
        cp.add(grid);
        cp.setBottomComponent(toolBar);


        RootPanel.get("gxtGrid").add(cp);

        RootPanel.get("gxtContainer").add(textButton);




    }
}
