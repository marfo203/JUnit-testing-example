import junit.framework.TestCase;

public class testUnits extends TestCase {
	private PersonalNumber MalePersonalNumber;
	private PersonalNumber FemalePersonalNumber;

	
	public String malePNum = "200229-3237";
	public String femalePNum = "890201-3286";
	
	public testUnits(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		
		MalePersonalNumber = new PersonalNumber(malePNum);
		FemalePersonalNumber = new PersonalNumber(femalePNum);
	}

	
	
	public void testSex() {
		String expected = "male";
		assertEquals(expected, MalePersonalNumber.getSex(malePNum));
		
		expected = "female";
		assertEquals(expected, FemalePersonalNumber.getSex(femalePNum));
	}

	public void testDate() {
		String expected = malePNum.substring(2, 6);
		assertEquals(expected, MalePersonalNumber.getDate(malePNum));
		
		expected = femalePNum.substring(2, 6);
		assertEquals(expected, FemalePersonalNumber.getDate(femalePNum));
		
	}

	public void testYear() {
		String expected = malePNum.substring(0, 2);
		assertEquals(expected, MalePersonalNumber.getYear(malePNum));

		expected = femalePNum.substring(0, 2);
		assertEquals(expected, FemalePersonalNumber.getYear(femalePNum));
	}

	public void testMonth() {
		String expected = malePNum.substring(2, 4);
		assertEquals(expected, MalePersonalNumber.getMonth(malePNum));
		
		expected = femalePNum.substring(2, 4);
		assertEquals(expected, FemalePersonalNumber.getMonth(femalePNum));
		
	}

	public void testSum() {
		int expected = Integer.parseInt(malePNum.substring(10));
		assertEquals(expected, MalePersonalNumber.getCheckSum(malePNum));
	
		expected = Integer.parseInt(femalePNum.substring(10));
		assertEquals(expected, FemalePersonalNumber.getCheckSum(femalePNum));
	}
}
