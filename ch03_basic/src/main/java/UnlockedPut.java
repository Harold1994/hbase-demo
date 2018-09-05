import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.regionserver.Region;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;

//Table类没有lockRow方法，调研之后会解决这个问题
public class UnlockedPut implements Runnable {

    public void run() {
        try {
            Configuration conf = HBaseConfiguration.create();
            Connection connection = ConnectionFactory.createConnection(conf);
            HBaseHelper helper = HBaseHelper.getHelper(conf);
            helper.dropTable("testtable");
            helper.createTable("testtable", "colfam1");
            Table table = connection.getTable(TableName.valueOf("testtable"));
            Put put = new Put(Bytes.toBytes("row1"));
            put.addColumn(Bytes.toBytes("colfam1"),Bytes.toBytes("qual1"), Bytes.toBytes("val3"));
            long time = System.currentTimeMillis();
            table.put(put);
            System.out.println("wait time:" + (System.currentTimeMillis() - time) + "ms");

        } catch (IOException e) {
            System.err.println("Thread err: " + e);
        }
    }

    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable", "colfam1");
        Table table = connection.getTable(TableName.valueOf("testtable"));

        System.out.println("Taking out lock ...");

    }
}
