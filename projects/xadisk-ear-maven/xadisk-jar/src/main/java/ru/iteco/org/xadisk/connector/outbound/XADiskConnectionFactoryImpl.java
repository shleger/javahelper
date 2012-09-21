/*
Copyright © 2010-2011, Nitin Verma (project owner for XADisk https://xadisk.dev.java.net/). All rights reserved.

This source code is being made available to the public under the terms specified in the license
"Eclipse Public License 1.0" located at http://www.opensource.org/licenses/eclipse-1.0.php.
*/


package ru.iteco.org.xadisk.connector.outbound;

import org.xadisk.bridge.proxies.interfaces.XADiskRemoteConnectionFactory;
import org.xadisk.connector.outbound.XADiskConnection;
import org.xadisk.connector.outbound.XADiskConnectionFactory;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;

public class XADiskConnectionFactoryImpl implements XADiskConnectionFactory, XADiskRemoteConnectionFactory {

    private static final long serialVersionUID = 1L;
    
    private final XADiskManagedConnectionFactory mcf;
    private final ConnectionManager cm;
    private Reference ref;

    public XADiskConnectionFactoryImpl(XADiskManagedConnectionFactory mcf, ConnectionManager cm) {
        this.mcf = mcf;
        this.cm = cm;
    }

    public XADiskConnection getConnection() throws ResourceException {
        return (XADiskConnection) cm.allocateConnection(mcf, null);
    }

    public Reference getReference() throws NamingException {
        return ref;
    }

    public void setReference(Reference ref) {
        this.ref = ref;
    }
}
