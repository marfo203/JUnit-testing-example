import junit.framework.*;

public class ChekcStrTest extends TestCase {
	private ChekcStr ChekcStr;

	public ChekcStrTest(String name) {
		super(name);
	}

	protected void setUp() {
		ChekcStr = new ChekcStr();
	}

	public void FuncInTest() {
		int expected = 0;
		assertEquals(expected, ChekcStr.total(""));
		expected = 100;
		assertEquals(expected, ChekcStr.total("d"));
		expected = 265;
		assertEquals(expected, ChekcStr.total("Add"));
	}

	public void testBinariseFunction() {
		String expected = "101";
		assertEquals(expected, ChekcStr.binarise(5));
		expected = "11111100";
		assertEquals(expected, ChekcStr.binarise(252));
	}

	public void testTotalConversion() {
		String expected = "1000001";
		assertEquals(expected, ChekcStr.convert("A"));
	}
}