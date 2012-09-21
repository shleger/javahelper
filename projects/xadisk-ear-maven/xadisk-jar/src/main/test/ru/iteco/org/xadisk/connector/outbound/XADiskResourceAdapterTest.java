package ru.iteco.org.xadisk.connector.outbound;

import junit.framework.TestCase;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 21.09.12
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
public class XADiskResourceAdapterTest extends TestCase {
    public void testStart() throws Exception {

        String txDir = "C:\\IBM\\SDP\\runtimes\\base_v7\\profiles\\WTE_APPSRV7_FEP1\\bin\\xadisk-iask-2ndfl\\txnlogs";

        XADiskResourceAdapter.deleteOldLogs(txDir);

    }


}
