package tn.insat.textParser.dates;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.time.*;
import java.util.*;
import java.util.stream.Collectors;


public class DatesExtractor{

    private static final Logger logger = LogManager.getLogger(DatesExtractor.class);


    /** this method returns all dates from input text in nested map in desired format 
     * for example the output of our example will be {2011={8={24=[2011-08-24]}, 9={18=[2011-09-18, 2011-09-18]}},
     * 2014={5={15=[2014-05-15],20=[2014-05-20, 2014-05-20]}}, 2016={1={27=[2016-01-27]}, 4={7=[2016-04-07]}, 6={23=[2016-06-23]}
     * **/

    public static Map<Integer, Map<Integer, Map<Integer,List<LocalDate>>>> textParser(String text) {

        Parser parser = new Parser();
        List<DateGroup> groups = parser.parse(text);

        List<LocalDate> dates = new ArrayList<>();
        for(DateGroup group : groups){
            dates.add(group.getDates().get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }

        // grouping by year then by month then by date
        Map<Integer, Map<Integer, Map<Integer,List<LocalDate>>>>
                groupByYearMonthDay = dates.
                stream()
                .collect(
                        Collectors.
                                groupingBy(
                                        //grouping by year
                                        d -> d.getYear(),
                                        HashMap::new,
                                        Collectors.
                                                groupingBy(
                                                        //grouping by month
                                                        d -> d.getMonth().getValue(),
                                                        HashMap::new,
                                                        Collectors.
                                                                groupingBy(
                                                                        //grouping by day
                                                                        d -> d.getDayOfMonth()
                                                                )
                                                )
                                )
                )
                //sort the whole HashMap (by year,then by month,then by day)
                .entrySet()
                .stream()
                .sorted(HashMap.Entry.comparingByKey()) //sort years
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        y -> y.getValue() // get the values associated to each year
                                .entrySet()
                                .stream()
                                .sorted(Map.Entry.comparingByKey())// sort the months
                                .collect(
                                        Collectors.toMap(
                                                Map.Entry::getKey,
                                                m -> m.getValue() // get the values associated to each month
                                                        .entrySet()
                                                        .stream()
                                                        .sorted(Map.Entry.comparingByKey()) // sort the days
                                                        .collect(
                                                                Collectors.toMap(
                                                                    Map.Entry::getKey,
                                                                    Map.Entry::getValue,
                                                                    (a, b) -> a,
                                                                    LinkedHashMap::new
                                                                )
                                                        ),
                                                (a, b) -> a,
                                                LinkedHashMap::new
                                        )
                                ),
                        (a, b) -> a,
                        LinkedHashMap::new
                        )
                );

        return groupByYearMonthDay;

    }
}
