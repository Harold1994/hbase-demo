import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PutList {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable","colfam1");
        Connection connection = ConnectionFactory.createConnection(conf);
        TableName name = TableName.valueOf("testtable");
        Table table = connection.getTable(name);

        List<Put> puts = new ArrayList<Put>();

        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"),
                Bytes.toBytes("val1"));
        puts.add(put1);

//        Put put2 = new Put(Bytes.toBytes("row2"));
//        put1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"),
//                Bytes.toBytes("val2"));
//        puts.add(put2);
//
//        Put put3 = new Put(Bytes.toBytes("row2"));
//        put3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"),
//                Bytes.toBytes("val3"));
//        puts.add(put2);
        table.put(puts);

        Get get = new Get(Bytes.toBytes("row1"));
        Result res1 = table.get(get);
        System.out.println("Result: " + res1);

        table.close();
        connection.close();
        helper.close();

    }
}
