import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleTests {
    @Test
    public void examples() {
        assertEquals("Your interpreter should initialize all cells in the datagrid to 0", "000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000", Paintfuck.interpreter("*e*e*e*es*es*ws*ws*w*w*w*n*n*n*ssss*s*s*s*", 0, 6, 9));
        assertEquals("Your interpreter should adhere to the number of iterations specified", "111100\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000\r\n000000", Paintfuck.interpreter("*e*e*e*es*es*ws*ws*w*w*w*n*n*n*ssss*s*s*s*", 7, 6, 9));
        assertEquals("Your interpreter should traverse the 2D datagrid correctly", "111100\r\n000010\r\n000001\r\n000010\r\n000100\r\n000000\r\n000000\r\n000000\r\n000000", Paintfuck.interpreter("*e*e*e*es*es*ws*ws*w*w*w*n*n*n*ssss*s*s*s*", 19, 6, 9));
        assertEquals("Your interpreter should traverse the 2D datagrid correctly for all of the \"n\", \"e\", \"s\" and \"w\" commands", "111100\r\n100010\r\n100001\r\n100010\r\n111100\r\n100000\r\n100000\r\n100000\r\n100000", Paintfuck.interpreter("*e*e*e*es*es*ws*ws*w*w*w*n*n*n*ssss*s*s*s*", 42, 6, 9));
        assertEquals("Your interpreter should terminate normally and return a representation of the final state of the 2D datagrid when all commands have been considered from left to right even if the number of iterations specified have not been fully performed", "111100\r\n100010\r\n100001\r\n100010\r\n111100\r\n100000\r\n100000\r\n100000\r\n100000", Paintfuck.interpreter("*e*e*e*es*es*ws*ws*w*w*w*n*n*n*ssss*s*s*s*", 100, 6, 9));

        assertEquals("Your interpreter should jump to the command straight *after* the matching [ on the iteration where it hits the ] and *not* the matching [ itself",
                "10000\r\n01000\r\n00100\r\n00000\r\n00000\r\n00000", Paintfuck.interpreter("*[es*]", 9, 5, 6));

        assertEquals("Your interpreter should should exhibit toroidal behaviour ;)",
                "11000\r\n01100\r\n00110\r\n00011\r\n00001\r\n10000", Paintfuck.interpreter("*[es*]", 37, 5, 6));        
    }
}
