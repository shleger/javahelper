package ru.saa.main;

import java.io.File;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

import org.xadisk.connector.outbound.XADiskConnection;
import org.xadisk.connector.outbound.XADiskConnectionFactory;

public class ContextConnect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("start");
		
    	try {
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
			     "com.ibm.websphere.naming.WsnInitialContextFactory");
			env.put(Context.PROVIDER_URL, "iiop://localhost:2809");
			Context ctx = new InitialContext(env);
			
			
			UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/UserTransaction");
			
			
			utx.begin();
			
			System.out.println("begin tx");
			XADiskConnectionFactory cfRemote = (XADiskConnectionFactory) new InitialContext().lookup("eis/xaDisk/RemoteConnectionFacory");
			
			System.out.println("xadiskConnFactoru:   " + cfRemote);

			XADiskConnection connectionLocal = cfRemote.getConnection();

			File f1 = new File("c:/1/test.txt");
			File f2 = new File("c:/1/data.txt");
			connectionLocal.createFile(f1, false);


			utx.commit();
			connectionLocal.close();
			
			
			System.out.println("stop " + ctx);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
