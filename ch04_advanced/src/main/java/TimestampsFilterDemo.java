import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.TimestampsFilter;
import util.HBaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimestampsFilterDemo {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable", "colfam1");
        System.out.println("Adding rows to table...");
        helper.fillTable("testtable", 1, 100, 20, true, "colfam1");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("testtable"));

        List<Long> ts = new ArrayList<Long>();
        ts.add(new Long(5));
        ts.add(new Long(10));
        ts.add(new Long(15));
        Filter filter = new TimestampsFilter(ts);

        Scan scan = new Scan();
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result res : scanner) {
            System.out.println(res);
        }
        scanner.close();

        Scan scan2 = new Scan();
        scan2.setFilter(filter);
        scan2.setTimeRange(8,12);
        ResultScanner scanner2 = table.getScanner(scan2);
        for (Result res : scanner2) {
            System.out.println(res);
        }
        scanner2.close();
        helper.close();
        table.close();
        connection.close();

    }
}
