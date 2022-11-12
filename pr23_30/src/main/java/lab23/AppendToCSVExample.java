package lab23;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;
class AppendToCSVExample
{
    public static void write() throws Exception
    {
        String csv = "data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        String [] record = "6,LoveLL,35680,54643,5742,5464.3,401.94,79518.64".split(",");
        writer.writeNext(record);
        writer.close();
    }
}
