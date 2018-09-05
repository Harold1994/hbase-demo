import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;

public class CompareAndDelete {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable", 100, "colfam1", "colfam2");
        helper.put("testtable",
                new String[] { "row1" },
                new String[] { "colfam1", "colfam2" },
                new String[] { "qual1", "qual2", "qual3" },
                new long[]   { 1, 2, 3 },
                new String[] { "val1", "val2", "val3" });
        System.out.println("Before delete call...");
        helper.dump("testtable", new String[]{ "row1" }, null, null);

        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("testtable"));

        Delete delete1 = new Delete(Bytes.toBytes("row1"));
        delete1.addColumns(Bytes.toBytes("colfam1"), Bytes.toBytes("qual3"));
        boolean res1 = table.checkAndDelete(Bytes.toBytes("row1"), Bytes.toBytes("colfam2"), Bytes.toBytes("qual3"), null, delete1);
        System.out.println("Delete 1 successful: " + res1);

        Delete delete2 = new Delete(Bytes.toBytes("row1"));
        delete2.addColumns(Bytes.toBytes("colfam2"), Bytes.toBytes("qual3")); // co CheckAndDeleteExample-4-Delete2 Delete checked column manually.
        table.delete(delete2);

        boolean res2 = table.checkAndDelete(Bytes.toBytes("row1"),
                Bytes.toBytes("colfam2"), Bytes.toBytes("qual3"), null, delete1); // 可以跨列族检查
        System.out.println("Delete 2 successful: " + res2);

        Delete delete3 = new Delete(Bytes.toBytes("row2"));
        delete3.addFamily(Bytes.toBytes("colfam1"));
        try{
            boolean res4 = table.checkAndDelete(Bytes.toBytes("row1"),
                    Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), // co CheckAndDeleteExample-8-CAS4 Try to delete while checking a different row.
                    Bytes.toBytes("val1"), delete3);
            System.out.println("Delete 3 successful: " + res4); // co CheckAndDeleteExample-9-SOUT4 We will not get here as an exception is thrown beforehand!
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        table.close();
        connection.close();
        System.out.println("After delete call...");
        helper.dump("testtable", new String[]{"row1"}, null, null);
        helper.close();


    }
}
