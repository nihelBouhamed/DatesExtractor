package tn.insat.textParser.dates;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;

public class Utilities{

    /** This method will print the nested map as format below
     * year:
     *      -month
     *              -day(number of occurrences)
     * **/
    public static String nestedPrint( Map<Integer, Map<Integer, Map<Integer,List<LocalDate>>>> map){

        StringBuilder res = new StringBuilder();
        map.forEach((year, monthsAndDaysDates) -> {
            res.append(year+":\n");
            monthsAndDaysDates.forEach((month, daysDates) -> {
                res.append("\t-" +String.format("%02d",month)+"\n");
                daysDates.forEach((day, wholeDates) -> {
                    res.append("\t\t-" +String.format("%02d",day)+"("+wholeDates.size()+")\n");
                });
            });
        });

        return res.toString();
    }
}
