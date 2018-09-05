import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.metrics.ScanMetrics;
import util.HBaseHelper;

import java.io.IOException;

public class ScanTimeoutExample {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable", "colfam1", "colfam2");
        System.out.println("Adding rows to table...");
        helper.fillTable("testtable", 1, 10, 10, "colfam1", "colfam2");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("testtable"));

        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);

        int scannerTimeout = (int) conf.getLong(HConstants.HBASE_CLIENT_SCANNER_TIMEOUT_PERIOD, -1);
        System.out.println("Current (local) lease period: " + scannerTimeout + "ms");
        System.out.println("Sleeping now for " + (scannerTimeout + 5000) + "ms...");
        try {
            Thread.sleep(scannerTimeout + 50000);
        } catch (InterruptedException e) {

        }

        System.out.println("iterate over scannner");
        while (true) {
            try {
                Result res = scanner.next();
                if (res == null)
                    break;
                System.out.println(res);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        scanner.close();
        table.close();
        connection.close();
        helper.close();
    }
}
