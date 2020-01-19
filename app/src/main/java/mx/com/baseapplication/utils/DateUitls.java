package mx.com.baseapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUitls {



    public  static  DateUitls shared ;


    public static DateUitls getShared() {
        if (shared == null){
            shared = new DateUitls();
        }
        return shared;
    }

    private DateUitls(){

    }


    public static String getDate(double timestapm){
        Date hora = new Date((long)timestapm);
      //  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String date = simpleDateFormat.format(hora);
        String parst[] = date.split(" ");
        if(parst!= null && parst.length >1){
            return parst[0].substring(1);

        }else{
            return  date;
        }
    }

    public static  String getHour(double timestapm){
        Date hora = new Date((long)timestapm);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
        String date = simpleDateFormat.format(hora);
        String parst[] = date.split(" ");
        if(parst!= null && parst.length >1){
            return parst[1];

        }else{
            return  date;
        }
    }

}
