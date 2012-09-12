package ru.saa.xadisk.ejbbean;

import java.io.File;
import java.io.IOException;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.resource.ResourceException;

import org.xadisk.additional.XAFileOutputStreamWrapper;
import org.xadisk.bridge.proxies.interfaces.Session;
import org.xadisk.bridge.proxies.interfaces.XAFileOutputStream;
import org.xadisk.bridge.proxies.interfaces.XAFileSystem;
import org.xadisk.bridge.proxies.interfaces.XAFileSystemProxy;
import org.xadisk.connector.outbound.XADiskConnection;
import org.xadisk.connector.outbound.XADiskConnectionFactory;
import org.xadisk.filesystem.exceptions.ClosedStreamException;
import org.xadisk.filesystem.exceptions.FileAlreadyExistsException;
import org.xadisk.filesystem.exceptions.FileNotExistsException;
import org.xadisk.filesystem.exceptions.FileUnderUseException;
import org.xadisk.filesystem.exceptions.InsufficientPermissionOnFileException;
import org.xadisk.filesystem.exceptions.LockingFailedException;
import org.xadisk.filesystem.exceptions.NoTransactionAssociatedException;

/**
 * Session Bean implementation class XaDiskEjbBean
 */
@Stateless
public class XaDiskEjbBean implements XaDiskEjbBeanLocal {

	/**
	 * Default constructor.
	 */
	public XaDiskEjbBean() {

	}

	public void doGetXaDisk() throws NamingException, ResourceException,
			FileNotExistsException, FileUnderUseException,
			InsufficientPermissionOnFileException, LockingFailedException,
			NoTransactionAssociatedException, InterruptedException, IOException {

		System.out.println("start");

		System.out.println("begin tx");
		XADiskConnectionFactory cfLocal = (XADiskConnectionFactory) new InitialContext()
				.lookup("eis/xaDisk/LocalConnectionFacory");


		
		System.out.println("xadiskConnFactoru:   " + cfLocal);

		XADiskConnection connection = cfLocal.getConnection();

		System.out.println("connLocal: " + connection);

		File f1 = new File("c:\\test123321.txt");

		XAFileOutputStream xaFOS = connection.createXAFileOutputStream(f1,
				false);
		XAFileOutputStreamWrapper wrapperOS = new XAFileOutputStreamWrapper(
				xaFOS);
		wrapperOS
				.write((System.currentTimeMillis() + ", Coffee Beans, 5, 100, Street #11, Moon - 311674 \n")
						.getBytes());
		wrapperOS.close();

		connection.setPublishFileStateChangeEventsOnCommit(true);
		connection.close();

		System.out.println("==commit==");

	}
	
	public void doGetXaDiskSingleJVM() throws NamingException, ResourceException,
	FileNotExistsException, FileUnderUseException,
	InsufficientPermissionOnFileException, LockingFailedException,
	NoTransactionAssociatedException, InterruptedException, IOException, FileAlreadyExistsException, ClosedStreamException {
		
		
		XAFileSystem xaf = XAFileSystemProxy.getNativeXAFileSystemReference("xadisk-was-cf1");
		
		System.out.println("same JVM:" + xaf);
		
		Session session = xaf.createSessionForLocalTransaction();
		
		File f =  new File("c:/test333.txt");
		
		try {
			session.createFile(f, false);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		}
		
		XAFileOutputStream xafos = session.createXAFileOutputStream(f, false);
		
		xafos.write((System.currentTimeMillis() + " millistime\n").getBytes());
		xafos.close();
		session.commit();
		
		System.out.println("same JVM commit:");
	}

}
