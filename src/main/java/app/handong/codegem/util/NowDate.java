package app.handong.codegem.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NowDate {
    public String getNow() {
        String[] nowDateString = {"", ""};

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //현재시간
        return simpleDateFormat.format(nowDate);
    }

    public String getDue(int second) {
        String nowDateString = "";

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        //시간 더하기
        cal.add(Calendar.SECOND, second);
        nowDateString = simpleDateFormat.format(cal.getTime());

        return nowDateString;
    }
}
