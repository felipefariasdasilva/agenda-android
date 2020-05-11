package felipe.com.database.converter;

import androidx.room.TypeConverter;

import felipe.com.model.TipoTelefone;

public class ConversorTipoTelefone {

    @TypeConverter
    public String paraString(TipoTelefone tipoTelefone){
        return tipoTelefone.name();
    }

    @TypeConverter
    public TipoTelefone paraTipoTelefone(String tipoTelefone){
        if(tipoTelefone != null){
            return TipoTelefone.valueOf(tipoTelefone);
        }
        return TipoTelefone.FIXO;
    }
}
