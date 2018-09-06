import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;

public class FirstKeyOnlyFilterExample {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable", "colfam1");
        System.out.println("Adding rows to table...");
        helper.fillTableRandom("testtable",0,30,0,1,30,0,
                0,100,0,true,"colfam1");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("testtable"));
        Filter filter1 = new FirstKeyOnlyFilter();
        Scan scan = new Scan();
        scan.setFilter(filter1);
        ResultScanner scanner = table.getScanner(scan);
        int totalRows = 0;
        for (Result res : scanner) {
            for (Cell cell : res.rawCells()) {
                System.out.println("Cell: " + cell + ", Value: " +
                        Bytes.toString(cell.getValueArray(), cell.getValueOffset(),
                                cell.getValueLength()));
            }
            totalRows++;
        }
        System.out.println("Total Rows: " + totalRows);
        scanner.close();
        helper.close();
        table.close();
        connection.close();

    }
}
