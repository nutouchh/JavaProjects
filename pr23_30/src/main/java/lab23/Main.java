package lab23;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.FileReader;
import java.util.List;

public class Main {

//    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) throws Exception {
//        AppendToCSVExample wr = new AppendToCSVExample();
//        //wr.write();
//
//        ParseCSVLineByLine rd = new ParseCSVLineByLine();
//        rd.read();


        CsvToBean csv = new CsvToBean();
        String csvFilename = "data.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFilename), ',', '"', 1);

        List list = csv.parse(setColumMapping(), csvReader);

//       for (Object object : list) {
//           Transaction transaction = (Transaction) object;
//
//           System.out.println(transaction);
//        }
        render(list);

    }


//    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ColumnPositionMappingStrategy setColumMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Transaction.class);
        String[] columns = new String[] {"Id","Name","Start","Income","Expenses","Percents","Cashback","Result"};
        strategy.setColumnMapping(columns);
        return strategy;
    }



    public static void render(List<Transaction> arr) {
        float allExpenses = 0;
        float allIncome = 0;
        float percents = 0;
        float cashback = 0;

        for (Transaction transact : arr) {
            allExpenses += transact.getExpenses();
            allIncome += transact.getIncome();
            percents += transact.getPercents();
            cashback += transact.getCashback();
        }

        System.out.println(
                "Сумма расходов: " +
                        allExpenses +
                        " руб.\n" +
                        "Сумма доходов: " +
                        allIncome +
                        " руб.\n\n" +
                        "Сумма процентов: " +
                        percents +
                        " руб.\n" +
                        "Сумма кэшбэка: " +
                        cashback +
                        " руб.\n\nСуммы расходов по организациям:\n");

        for (Transaction transact : arr) {
            System.out.println(transact.getName() + "\t" + (transact.getExpenses()+transact.getPercents()));
        }
    }


}


