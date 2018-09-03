import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class PutExample {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
//        HBaseAdmin admin = new HBaseAdmin(conf);
//        HTableDescriptor tableDescriptor = new
//                HTableDescriptor(TableName.valueOf("testtable"));
//
//        // Adding column families to table descriptor
//        tableDescriptor.addFamily(new HColumnDescriptor("colfam1"));
//        // Execute the table through admin
//        admin.createTable(tableDescriptor);
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("testtable"));
        try {
            Put put = new Put(Bytes.toBytes("row2"));
            put.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual3"), Bytes.toBytes("val4"));
            put.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val3"));
            table.put(put);
        } finally {
            table.close();
            connection.close();
        }
    }
}

