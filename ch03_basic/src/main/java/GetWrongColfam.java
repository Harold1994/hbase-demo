import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import util.HBaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetWrongColfam {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        HBaseHelper helper = HBaseHelper.getHelper(conf);
        if (!helper.existsTable("testtable")) {
            helper.createTable("testtable", "colfam1");
        }
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("testtable"));

        byte[] cf1 = Bytes.toBytes("colfam1");
        byte[] qf1 = Bytes.toBytes("qual1");
        byte[] qf2 = Bytes.toBytes("qual2");
        byte[] row1 = Bytes.toBytes("row1");
        byte[] row2 = Bytes.toBytes("row2");

        List<Get> gets = new ArrayList<Get>();
        Get get1 = new Get(row1);
        get1.addColumn(cf1, qf1);
        gets.add(get1);

        Get get2 = new Get(row2);
        get2.addColumn(cf1,qf1);
        gets.add(get2);

        Get get3 = new Get(row2);
        get3.addColumn(cf1, qf2);
        gets.add(get3);

        Get get4 = new Get(row2);
        get4.addColumn(Bytes.toBytes("BOGUS"), qf2);
        gets.add(get4);


        Result[] res = table.get(gets);
        System.out.println("Res count: " + res.length);
        table.close();
        connection.close();
        helper.close();
    }
}

