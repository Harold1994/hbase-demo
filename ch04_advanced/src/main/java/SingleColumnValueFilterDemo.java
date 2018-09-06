import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;

public class SingleColumnValueFilterDemo {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable", "colfam1", "colfam2");
        System.out.println("Adding rows to table...");
        helper.fillTable("testtable", 1, 10, 10, "colfam1", "colfam2");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("testtable"));

        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("colfam1"),
                Bytes.toBytes("col-5"),
                CompareFilter.CompareOp.NOT_EQUAL,
                new SubstringComparator("val-5"));
        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result res : scanner) {
            for (Cell cell : res.rawCells()) {
                System.out.println("Cell : " + cell + ",Value :" + Bytes.toString(cell.getValueArray(),
                        cell.getValueOffset(),cell.getValueLength()));
            }
        }
        scanner.close();
        Get get = new Get(Bytes.toBytes("row-6"));
        get.setFilter(filter);
        Result res2 = table.get(get);
        System.out.println(
                "Result of get"
        );
        System.out.println(res2);
        for (Cell cell : res2.rawCells()) {
            System.out.println("Cell : " + cell + ",Value :" + Bytes.toString(cell.getValueArray(),
                    cell.getValueOffset(),cell.getValueLength()));
        }
        table.close();
        scanner.close();
        helper.close();
        connection.close();
    }
}
