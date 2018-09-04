import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

public class GetExample {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        TableName name = TableName.valueOf("testtable");
        Table table = connection.getTable(name);
        Get get = new Get(Bytes.toBytes("row1"));
        get.addColumn(Bytes.toBytes("colfam1"),Bytes.toBytes("qual1"));
        Result res = table.get(get);
        byte [] val = res.getValue(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
        System.out.println("Value: " + Bytes.toString(val));
        byte [] row = res.getRow();
        System.out.println("Row: " + row);
        List<Cell> list = res.listCells();
        System.out.println("List: " + list);
    }
}
