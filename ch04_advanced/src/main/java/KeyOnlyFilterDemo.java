import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.KeyOnlyFilter;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;

public class KeyOnlyFilterDemo {
    private static Table table;
    private static void scan(Filter filter) throws IOException {
        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        System.out.println("result od scan");
        int rowCount = 0;
        for (Result res : scanner) {
            for (Cell cell : res.rawCells()) {
                System.out.println("Cell: " + cell +
                        "Value: " + (cell.getValueLength()>0 ? Bytes.toString(cell.getValueArray(),
                        cell.getValueOffset(),cell.getValueLength()):"nan"));
            }
            rowCount++;
        }
        System.out.println("Total num of rows: " + rowCount);
        scanner.close();
    }
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable","colfam1");
        helper.fillTableRandom("testtable",1, 5, 0, 1, 30, 0,  0, 10000, 0, true, "colfam1");
        Connection connection = ConnectionFactory.createConnection(conf);
        table = connection.getTable(TableName.valueOf("testtable"));

        System.out.println("Scan1 #");
        Filter filter1 = new KeyOnlyFilter();
        scan(filter1);

        Filter filter2 = new KeyOnlyFilter(true);
        scan(filter2);
        helper.close();
        table.close();
        connection.close();
    }
}
