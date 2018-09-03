import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;

public class CheckAndPutExample {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable","colfam1");
        Connection connection = ConnectionFactory.createConnection(conf);
        TableName name = TableName.valueOf("testtable");
        Table table = connection.getTable(name);

        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));

        boolean res1 = table.checkAndPut(Bytes.toBytes("row1"),Bytes.toBytes("colfam1"),
                Bytes.toBytes("qual1"), null, put1);
        System.out.println(res1);

        boolean res2 = table.checkAndPut(Bytes.toBytes("row1"),Bytes.toBytes("colfam1"),
                Bytes.toBytes("qual1"), null, put1);
        System.out.println(res2);

        boolean res3 = table.checkAndPut(Bytes.toBytes("row1"),Bytes.toBytes("colfam1"),
                Bytes.toBytes("qual1"), Bytes.toBytes("val1"), put1);
        System.out.println(res3);

        Put put3 = new Put(Bytes.toBytes("row2"));
        put3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val3"));

        boolean res4 = table.checkAndPut(Bytes.toBytes("row1"),Bytes.toBytes("colfam1"),
                Bytes.toBytes("qual1"), Bytes.toBytes("val1"), put3);
        System.out.println(res4);

    }
}
