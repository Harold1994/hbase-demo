import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;

public class FlushDemo {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable","colfam1");
        Connection connection = ConnectionFactory.createConnection(conf);
        TableName name = TableName.valueOf("testtable");
        Table table = connection.getTable(name);
        BufferedMutator mutator = connection.getBufferedMutator(name);

        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        mutator.mutate(put1);

        Put put2 = new Put(Bytes.toBytes("row2"));
        put2.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val2"));
        mutator.mutate(put2);

        Put put3 = new Put(Bytes.toBytes("row3"));
        put3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val3"));
        mutator.mutate(put3);

        Get get = new Get(Bytes.toBytes("row3"));
        Result rst = table.get(get);
        System.out.println("Result" + rst);

        mutator.flush();

        Result res = table.get(get);
        System.out.println("Result: " + res);

        mutator.close();
        table.close();
        connection.close();
        helper.close();
    }
}
