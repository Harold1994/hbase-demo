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
import java.util.ArrayList;
import java.util.List;

public class DeleteListErrorDemo {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        HBaseHelper helper = HBaseHelper.getHelper(conf);
        helper.dropTable("testtable");
        helper.createTable("testtable", 100, "colfam1", "colfam2");
        helper.put("testtable",
                new String[]{"row1"},
                new String[]{"colfam1", "colfam2"},
                new String[]{"qual1", "qual1", "qual2", "qual2", "qual3", "qual3"},
                new long[]{1, 2, 3, 4, 5, 6},
                new String[]{"val1", "val2", "val3", "val4", "val5", "val6"});
        helper.put("testtable",
                new String[]{"row2"},
                new String[]{"colfam1", "colfam2"},
                new String[]{"qual1", "qual1", "qual2", "qual2", "qual3", "qual3"},
                new long[]{1, 2, 3, 4, 5, 6},
                new String[]{"val1", "val2", "val3", "val4", "val5", "val6"});
        helper.put("testtable",
                new String[]{"row3"},
                new String[]{"colfam1", "colfam2"},
                new String[]{"qual1", "qual1", "qual2", "qual2", "qual3", "qual3"},
                new long[]{1, 2, 3, 4, 5, 6},
                new String[]{"val1", "val2", "val3", "val4", "val5", "val6"});
        System.out.println("Before delete call ...");
        helper.dump("testtable", new String[]{"row1", "row2", "row3"}, null, null);
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("testtable"));

        List<Delete> deletes = new ArrayList<Delete>();

        Delete delete1 = new Delete(org.apache.hadoop.hbase.util.Bytes.toBytes("row1"));
        delete1.setTimestamp(4);
        deletes.add(delete1);

        Delete delete2 = new Delete(org.apache.hadoop.hbase.util.Bytes.toBytes("row2"));
        delete2.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
        delete2.addColumns(Bytes.toBytes("colfam2"), Bytes.toBytes("qual3"), 5);
        deletes.add(delete2);

        Delete delete3 = new Delete(Bytes.toBytes("row3"));
        delete3.addFamily(Bytes.toBytes("colfam1")); // co DeleteListExample-5-AddCol Delete entire family, all columns and versions.
        delete3.addFamily(Bytes.toBytes("colfam2"), 3); // co DeleteListExample-6-AddCol Delete the given and all older versions in the entire column family, i.e., from all columns therein.
        deletes.add(delete3);

        Delete delete4 = new Delete(Bytes.toBytes("row2"));
        /*[*/
        delete4.addColumn(Bytes.toBytes("BOGUS"),/*]*/ Bytes.toBytes("qual1")); // co DeleteListErrorExample-1-DelColNoTS Add bogus column family to trigger an error.
        deletes.add(delete4);

        try {
            table.delete(deletes); // co DeleteListErrorExample-2-DoDel Delete the data from multiple rows the HBase table.
        } catch (Exception e) {
            System.err.println("Error: " + e); // co DeleteListErrorExample-3-Catch Guard against remote exceptions.
        }

        table.close();
        System.out.println("delete length: " + deletes.size());
        for (Delete delete : deletes) {
            System.out.println(delete);
        }
        connection.close();
        System.out.println("After delete call ...");
        helper.dump("testtable", new String[]{"row1", "row2", "row3"}, null, null);
        helper.close();
    }
}
