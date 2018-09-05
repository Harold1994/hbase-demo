import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;

public class ScanResult {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable", "colfam1", "colfam2");
        System.out.println("Adding rows to table...");

        helper.fillTable("testtable",1,10, 5, "colfam1", "colfam2");
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("testtable"));

        System.out.println("Scanning table #1...");
        Scan scan1 = new Scan();
        ResultScanner scanner1 = table.getScanner(scan1);
        try {
            for (Result res : scanner1) {
                System.out.println(res);
            }
        } finally {
            scanner1.close();
        }

        System.out.println("Scanning table #2...");
        Scan scan2 = new Scan();
        scan2.addFamily(Bytes.toBytes("colfam1"));
        ResultScanner scanner2 = table.getScanner(scan2);
        try {
            for (Result res : scanner2) {
                System.out.println(res);
            }
        } finally {
            scanner2.close();
        }

        System.out.println("Scanning table #3...");
        Scan scan3 = new Scan();
        scan3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("col-4")).
                addColumn(Bytes.toBytes("colfam2"), Bytes.toBytes("col-1")).
                setStartRow(Bytes.toBytes("row-5")).
                setStopRow(Bytes.toBytes("row-9"));
        ResultScanner scanner3 = table.getScanner(scan3);
        try {
            for (Result res : scanner3) {
                System.out.println(res);
            }
        } finally {
            scanner3.close();
        }

        System.out.println("Scanning table #4...");
        Scan scan4 = new Scan();
        scan4.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("col-4")).
                addColumn(Bytes.toBytes("colfam2"), Bytes.toBytes("col-1")).
                setStartRow(Bytes.toBytes("row-9")).
                setStopRow(Bytes.toBytes("row-5"));
        ResultScanner scanner4 = table.getScanner(scan4);
        try {
            for (Result res : scanner4) {
                System.out.println(res);
            }
        } finally {
            scanner4.close();
        }
    }
}
