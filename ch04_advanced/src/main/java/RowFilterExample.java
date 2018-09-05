import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;

public class RowFilterExample {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable", "colfam1", "colfam2");
        System.out.println("Adding rows to table...");
        helper.fillTable("testtable", 1, 100, 100, "colfam1", "colfam2");
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("testtable"));

        System.out.println("Scanning table #1...");
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("col-1"));
        Filter filter = new RowFilter(CompareFilter.CompareOp.LESS_OR_EQUAL, new BinaryComparator(Bytes.toBytes("row-22")));//行键按照字典序排序
        scan.setFilter(filter);
        ResultScanner scanner1 = table.getScanner(scan);
        for (Result res : scanner1) {
            System.out.println(res);
        }
        scanner1.close();
        System.out.println("Scanning table #2...");

        Filter filter2 = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(".*-.5"));
        scan.setFilter(filter2);
        ResultScanner scanner2  = table.getScanner(scan);
        for (Result res: scanner2) {
            System.out.println(res);
        }
        scanner2.close();

        System.out.println("Scanning table #3...");
        Filter filter3 = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator("-5"));
        scan.setFilter(filter3);
        ResultScanner scanner3  = table.getScanner(scan);
        for (Result res: scanner3) {
            System.out.println(res);
        }
        scanner3.close();
        table.close();
        connection.close();
        helper.close();
    }
}
