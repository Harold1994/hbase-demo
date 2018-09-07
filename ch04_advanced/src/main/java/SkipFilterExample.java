import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;

public class SkipFilterExample {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable", "colfam1");
        System.out.println("Adding rows to table...");
        helper.fillTable("testtable", 1, 30, 5, 2, true, true, "colfam1");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("testtable"));
        Filter filter = new ValueFilter(CompareFilter.CompareOp.NOT_EQUAL, new BinaryComparator(Bytes.toBytes("val-0")));
        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner scanner1 = table.getScanner(scan);
        int n = 0;
        System.out.println("Result for scan1 #...");
        for (Result result : scanner1) {
            for (Cell cell : result.rawCells()) {
                System.out.println("Cell: " + cell + ", Value: " +
                        Bytes.toString(cell.getValueArray(), cell.getValueOffset(),
                                cell.getValueLength()));
                n++;
            }
        }

        System.out.println("Total cell count for scan #1: " + n);
        scanner1.close();
        System.out.println("Result for scan2 #...");
        Filter filter1 = new SkipFilter(filter);
        scan.setFilter(filter1);
        ResultScanner scanner2 = table.getScanner(scan);
        n = 0;
        System.out.println("Result for scan1 #...");
        for (Result result : scanner2) {
            for (Cell cell : result.rawCells()) {
                System.out.println("Cell: " + cell + ", Value: " +
                        Bytes.toString(cell.getValueArray(), cell.getValueOffset(),
                                cell.getValueLength()));
                n++;
            }
        }
        scanner2.close();

        System.out.println("Total cell count for scan #2: " + n);
        connection.close();
        table.close();
        helper.close();
    }
}
