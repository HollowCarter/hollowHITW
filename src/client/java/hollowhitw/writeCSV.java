package hollowhitw;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class writeCSV {
    public void writerCSV(String[] data) throws IOException {
        String datePatern = "yyMMddkk";
        DateFormat dateFormat = new SimpleDateFormat(datePatern);
        Date time = Calendar.getInstance().getTime();
        String today = dateFormat.format(time);

        try {
            FileWriter myWriter = new FileWriter("HITW_"+ today +".csv",true);
            String comma = ",";
            String newLine = System.getProperty("line.separator");
            myWriter.append(data[0]); // a for loop wasn't working for i data[i] would break mywriter and for i n=n+1 data[n] output would be the data[0]
            myWriter.append(comma);
            myWriter.append(data[1]);
            myWriter.append(comma);
            myWriter.append(data[2]);
            myWriter.append(comma);
            myWriter.append(data[3]);
            myWriter.append(comma);
            myWriter.append(data[4]);
            myWriter.append(comma);
            myWriter.append(data[5]);
            myWriter.append(comma);
            myWriter.append(data[6]);
            myWriter.append(comma);
            myWriter.append(data[7]);
            myWriter.append(comma);
            myWriter.append(data[8]);
            myWriter.append(comma);
            myWriter.append(newLine);
            myWriter.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("An error occurred wri.");
            e.printStackTrace();
        }

        //CSVWriter writer = new CSVWriter(new FileWriter("HITW_"+ today +".csv"));
        //writer.writeNext(data);
        //writer.close();
    }
}
