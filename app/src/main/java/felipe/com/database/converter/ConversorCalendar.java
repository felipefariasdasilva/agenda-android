package felipe.com.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversorCalendar {

    @TypeConverter
    public Long paraLong(Calendar calendar){
        if(calendar != null){
            return calendar.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar paraCalendar(Long timeInMillis){
        Calendar momentoAtual = Calendar.getInstance();
        if(timeInMillis != null){
            momentoAtual.setTimeInMillis(timeInMillis);
        }
        return momentoAtual;
    }
}
