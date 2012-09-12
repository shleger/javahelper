package ru.saa.xadisk.ejbbean;
import java.io.IOException;

import javax.ejb.Local;
import javax.naming.NamingException;
import javax.resource.ResourceException;

import org.xadisk.filesystem.exceptions.ClosedStreamException;
import org.xadisk.filesystem.exceptions.FileAlreadyExistsException;
import org.xadisk.filesystem.exceptions.FileNotExistsException;
import org.xadisk.filesystem.exceptions.FileUnderUseException;
import org.xadisk.filesystem.exceptions.InsufficientPermissionOnFileException;
import org.xadisk.filesystem.exceptions.LockingFailedException;
import org.xadisk.filesystem.exceptions.NoTransactionAssociatedException;

@Local
public interface XaDiskEjbBeanLocal {
	
	 public void doGetXaDisk() throws NamingException, ResourceException, FileNotExistsException, FileUnderUseException, InsufficientPermissionOnFileException, LockingFailedException, NoTransactionAssociatedException, InterruptedException, IOException ;
	 
	 public void doGetXaDiskSingleJVM() throws NamingException, ResourceException,
		FileNotExistsException, FileUnderUseException,
		InsufficientPermissionOnFileException, LockingFailedException,
		NoTransactionAssociatedException, InterruptedException, IOException, FileAlreadyExistsException, ClosedStreamException ; 

}
