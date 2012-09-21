package ru.saa.xadisk;

import org.xadisk.additional.XAFileOutputStreamWrapper;
import org.xadisk.bridge.proxies.interfaces.Session;
import org.xadisk.bridge.proxies.interfaces.XAFileOutputStream;
import org.xadisk.bridge.proxies.interfaces.XAFileSystem;
import org.xadisk.bridge.proxies.interfaces.XAFileSystemProxy;
import org.xadisk.connector.outbound.XADiskConnection;
import org.xadisk.connector.outbound.XADiskConnectionFactory;
import org.xadisk.connector.outbound.XADiskUserLocalTransaction;
import org.xadisk.filesystem.exceptions.*;
import ru.iteco.foraspecting.TestWeave;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.resource.ResourceException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 20.09.12
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
public class XadiskRun extends javax.servlet.http.HttpServlet {
    private static final String XADISK_INSTANCE_ID = "xadisk-iask-2ndfl";
    private static final String XADISK_JNDI_NAME = "eis/org.xadisk.connector.outbound.XADiskConnectionFactory";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("in servlet!");

        TestWeave testWeave = new TestWeave();

        testWeave.getAs();


        XAFileSystem nativeXAF = XAFileSystemProxy.getNativeXAFileSystemReference(XADISK_INSTANCE_ID);

        Session session = nativeXAF.createSessionForLocalTransaction();


        System.out.println("XadiskRun.doGet: " + session);

        try {


            XADiskConnectionFactory cfLocal = (XADiskConnectionFactory) new InitialContext().lookup(XADISK_JNDI_NAME);

            System.out.println("xadisk cfLocal: " + cfLocal);

            XADiskConnection connection = cfLocal.getConnection();


            XADiskUserLocalTransaction localTransaction = connection.getUserLocalTransaction();

//            localTransaction.setTransactionTimeOut(200); //in seconds

//            localTransaction.beginLocalTransaction();

            System.out.println("xadisk connection: " + connection);


        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }




        try {

            File file = new File(XADISK_INSTANCE_ID + "/2ndflFile.txt");
            if (!session.fileExists(file)) {
                session.createFile(file, false);

                XAFileOutputStream xmlFileStream = session.createXAFileOutputStream(file,
                        true);

                System.out.println("xadisk xmlFileStream: " + xmlFileStream);

                XAFileOutputStreamWrapper xmlFileStreamWrapper = new XAFileOutputStreamWrapper(
                        xmlFileStream);

                xmlFileStreamWrapper.write((new Date() + "ddddd").getBytes());

                String outFile = "filePath:" + file.getAbsolutePath();

                System.out.println(outFile);

                PrintWriter pw = response.getWriter();

                pw.write(outFile);
//                pw.close();

                session.commit();



                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println( "<head>" );
                out.println( "<title>A Sample Servlet</title>" );
                out.println( "</head>" );
                out.println( "<body>" );
                out.println( "<h1>File path:</h1>" );
                out.print(file.getAbsolutePath());
                out.println( "</body>" );
                out.println( "</html>" );

                out.close();



            }
        } catch (LockingFailedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoTransactionAssociatedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InsufficientPermissionOnFileException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileNotExistsException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FileUnderUseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
