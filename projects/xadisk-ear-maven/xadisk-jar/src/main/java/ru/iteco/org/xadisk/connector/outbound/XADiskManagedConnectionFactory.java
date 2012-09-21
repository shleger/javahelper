package ru.iteco.org.xadisk.connector.outbound;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 20.09.12
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 */

import java.io.PrintWriter;
import java.util.Set;
import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.security.auth.Subject;
import org.xadisk.bridge.proxies.impl.XADiskRemoteManagedConnectionFactory;
import org.xadisk.connector.outbound.XADiskManagedConnection;
import org.xadisk.filesystem.NativeXAFileSystem;

public class XADiskManagedConnectionFactory implements ManagedConnectionFactory {

    private static final long serialVersionUID = 1L;
    private transient volatile PrintWriter logWriter;

    private String instanceId;

    public XADiskManagedConnectionFactory() {
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Object createConnectionFactory() throws ResourceException {
        throw new NotSupportedException("Works only in managed environments.");
    }

    public Object createConnectionFactory(ConnectionManager cm) throws ResourceException {
        return new XADiskConnectionFactoryImpl(this, cm);
    }

    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cri)
            throws ResourceException {
        return new XADiskManagedConnection(NativeXAFileSystem.getXAFileSystem(instanceId), instanceId);
    }

    public ManagedConnection matchManagedConnections(Set candidates, Subject subject, ConnectionRequestInfo cri)
            throws ResourceException {
        boolean glassFish = false;

        //throw new NotSupportedException("Please don't pool connections to this EIS");
        if (candidates.size() == 0) {
            return null;
        }
        Object mc = candidates.iterator().next();
        if (mc instanceof XADiskManagedConnection) {
            return (XADiskManagedConnection) mc;
        }
        return null;
    }

    public PrintWriter getLogWriter() throws ResourceException {
        return logWriter;
    }

    public void setLogWriter(PrintWriter logWriter) throws ResourceException {
        this.logWriter = logWriter;
    }

    @Override
    public int hashCode() {
        return instanceId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof XADiskManagedConnectionFactory
                && !(obj instanceof XADiskRemoteManagedConnectionFactory)) {
            XADiskManagedConnectionFactory that = (XADiskManagedConnectionFactory) obj;
            return this.instanceId.equals(that.getInstanceId());
        }
        return false;
    }


}
