package alitea.am.ali_travel;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alitea.am.ali_travel.api_wrapper.util.DateFormats;
import alitea.am.ali_travel.api_wrapper.util.DurationFormatter;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testRegex() {

        final String regex = "P(\\d{1,4}Y)?(\\d{1,2}M)?(\\d{1,2}D)?(T(\\d{1,2}H)?(\\d{1,2}M)?(\\d{1,2}S)?)?";
        final String string = "P4Y6M28DT2H6M57S";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group(0));
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
    }

    @Test
    public void testPopLast() {
        assertEquals(DurationFormatter.popLast("Memes"), "Meme");
        assertEquals(DurationFormatter.popLast(""), "");
        assertEquals(DurationFormatter.popLast("5H"), "5");
    }

    @Test
    public void testPadZeros() {
        assertEquals("007", DurationFormatter.padZeros("7", 3));
        assertEquals("00", DurationFormatter.padZeros(null, 2));
    }

    @Test
    public void testHumanDateFormat() {
        assertEquals("05:21:00", DurationFormatter.ISO_8601ToHuman("PT5H21M"));
        assertEquals("36:07:00", DurationFormatter.ISO_8601ToHuman("P1DT12H7M"));
    }
}