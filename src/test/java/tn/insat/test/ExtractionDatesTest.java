package tn.insat.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static tn.insat.textParser.dates.DatesExtractor.textParser;
import static tn.insat.textParser.dates.Utilities.nestedPrint;

public class ExtractionDatesTest{

        /** This method will test our program, I choose to run the example sent in the email
         *
         * **/
        @Test
        public void testExtractDates(){

            assertEquals( nestedPrint(textParser("Marvin Lee Minsky at the Mathematics Genealogy Project; 20 May 2014\n" +
                    "Marvin Lee Minsky at the AI Genealogy Project. {reprint 18 September 2011)\n" +
                    "\"Personal page for Marvin Minsky\". web.media.mit.edu. Retrieved 23 June 2016.\n" +
                    "Admin (January 27, 2016). \"Official Alcor Statement Concerning Marvin Minsky\". \n" +
                    "\tAlcor Life Extension Foundation. Retrieved 2016-04-07.\n" +
                    "\"IEEE Computer Society Magazine Honors Artificial Intelligence Leaders\". \n" +
                    "\tDigitalJournal.com. August 24, 2011. Retrieved September 18, 2011. \n" +
                    "\tPress release source: PRWeb (Vocus).\n" +
                    "\"Dan David prize 2014 winners\". May 15, 2014. Retrieved May 20, 2014.")), "2011:\n" +
                    "\t-08\n" +
                    "\t\t-24(1)\n" +
                    "\t-09\n" +
                    "\t\t-18(2)\n" +
                    "2014:\n" +
                    "\t-05\n" +
                    "\t\t-15(1)\n" +
                    "\t\t-20(2)\n" +
                    "2016:\n" +
                    "\t-01\n" +
                    "\t\t-27(1)\n" +
                    "\t-04\n" +
                    "\t\t-07(1)\n" +
                    "\t-06\n" +
                    "\t\t-23(1)\n");
        }
}
