import de.bmgamez.currenttimetable.main.Reader;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class ReaderTest {

    @Test
    public void testReader() {

        String testText = "A\nB\nKl.\n1\n2\nC\nD\nKl.\n3";
        List<String> expectedOutput = new ArrayList<String>();
        expectedOutput.add("1");
        expectedOutput.add("2");
        expectedOutput.add("3");

        Reader reader = new Reader();

        List<String> output = reader.getImportantInformation(testText);

        assertThat(expectedOutput, is(output));

    }
}
