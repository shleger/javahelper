package ru.saa.xadisk;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import org.xadisk.connector.outbound.XADiskConnection;
import org.xadisk.connector.outbound.XADiskConnectionFactory;

import ru.saa.xadisk.ejbbean.XaDiskEjbBeanLocal;

/**
 * Servlet implementation class ServletXaDisk
 */
public class ServletXaDisk extends HttpServlet {

	@Resource
	UserTransaction utx;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletXaDisk() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		
		pw.println("start write");

		try {
			System.out.println("start");
			

			
			Hashtable parms = new Hashtable();
			parms.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.ibm.websphere.naming.WsnInitialContextFactory");
			InitialContext ctx = new InitialContext(parms);
			
			XaDiskEjbBeanLocal helloEjb = (XaDiskEjbBeanLocal)ctx.lookup("ejblocal:ru.saa.xadisk.ejbbean.XaDiskEjbBeanLocal");
			
			System.out.println("ejb:" + helloEjb);
			
//			helloEjb.doGetXaDisk();
			
			helloEjb.doGetXaDiskSingleJVM();
			System.out.println("==commit==servlet");
			
			pw.println("commit");
			
		} catch (Exception e) {
			e.printStackTrace(pw);

		}
		
		pw.println("stop write");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
