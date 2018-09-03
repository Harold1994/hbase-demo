import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PutListErrorExample1 {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable","colfam1");
        Connection connection = ConnectionFactory.createConnection(conf);
        TableName name = TableName.valueOf("testtable");
        Table table = connection.getTable(name);

        List<Put> puts = new ArrayList<Put>();

        // vv PutListErrorExample1
        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"),
                Bytes.toBytes("val1"));
        puts.add(put1);
        Put put2 = new Put(Bytes.toBytes("row2"));
        /*[*/put2.addColumn(Bytes.toBytes("BOGUS"),/*]*/ Bytes.toBytes("qual1"),
                Bytes.toBytes("val2")); // co PutListErrorExample1-1-AddErrorPut Add put with non existent family to list.
        puts.add(put2);
        Put put3 = new Put(Bytes.toBytes("row2"));
        put3.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"),
                Bytes.toBytes("val3"));
        puts.add(put3);

        try {
            table.put(puts);
        } catch (RetriesExhaustedWithDetailsException e) {
            int numErrors = e.getNumExceptions();
            System.out.println("num of errors" + numErrors);
            for (int n = 0; n < numErrors; n++) {
                System.out.println("Cause: " + e.getCause(n));
                System.out.println("Hostname[" + n + "]: " + e.getHostnamePort(n));
                System.out.println("Row[" + n + "]: " + e.getRow(n));
            }
            System.out.println("Cluster issues: " + e.mayHaveClusterIssues());
            System.out.println("Description: " + e.getExhaustiveDescription());
        }

        table.close();
        connection.close();
        helper.close();
    }
}
