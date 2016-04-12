import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class transformar_data {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public static Date stringToDate(String s){
        try {
            Date data =  formatter.parse(s);
            return data;
        }
        catch (ParseException e){
            return null;
        }
    }
    public static String dateToString(Date data){
        if(data != null) return formatter.format(data);
        return null;
    }
}
