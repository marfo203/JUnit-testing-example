import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class PersonalNumber {

	private String pNum;

	public PersonalNumber(String pNum) throws Exception {

		if (pNum.length() != 11) {

			throw new Exception("Invalind length!");

		} else if (!validDate(pNum)) {

			throw new Exception("Invalid date!");
		} else if (Integer.parseInt(pNum.substring(11)) != getCheckSum(pNum)) {
			throw new Exception("Invalid checksum!");
		}
		this.pNum = pNum;

	}

	public String getDate(String pNum) {

		String dateStr = pNum.substring(2, 6);

		return dateStr;
	}

	public String getYear(String pNum) {
		String yearStr = pNum.substring(0, 2);

		return yearStr;
	}

	public String getMonth(String pNum) {

		String monthStr = pNum.substring(2, 4);

		return monthStr;
	}

	public String getSex(String pNum) {

		String sexString = pNum.substring(9, 10);
		int sexInt = Integer.parseInt(sexString);

		if (sexInt % 2 != 0)
			return "male";
		else
			return "female";

	}

	public int getCheckSum(String pNum) {
		String firstPart = pNum.substring(0, 6);
		String lastPart = pNum.substring(7, 11);

		String number = firstPart.concat(lastPart);

		int sum = 0;
		boolean alternate = true;
		for (int i = 0; i < (number.length() - 1); i++) {
			int n = Integer.parseInt(number.substring(i, i + 1));
			if (alternate) {
				n *= 2;
				if (n > 9) {
					n -= 9;
				}
			}
			sum += n;
			alternate = !alternate;
		}

		return (sum * 9) % 10;

	}

	private boolean validDate(String pNum2) {

		boolean valid = false;

		String pNum = pNum2.substring(0, 6);

		try {
			LocalDate.parse(pNum, DateTimeFormatter.ofPattern("uuMMdd").
					withResolverStyle(ResolverStyle.STRICT));
			valid = true;

		} catch (DateTimeParseException e) {
			valid = false;
		}
		return valid;

	}
}
