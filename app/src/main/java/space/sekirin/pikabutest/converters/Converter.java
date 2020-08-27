package space.sekirin.pikabutest.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {
    @TypeConverter
    public String stringListToString(List<String> strings){
        String s = "";
        if(strings == null){
            return s;
        }
        for (String str : strings) {
            s += str + " ";
        }
        return s.trim();
    }

    @TypeConverter
    public List<String> stringToStringList(String listAsString){
        String[] s = listAsString.split(" ");
        List<String> strings;
        strings = Arrays.asList(s);
        return strings;
    }
}
