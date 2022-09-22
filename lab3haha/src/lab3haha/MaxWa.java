
	
	/*
	 * 2021-06-28
	 * DisaplayMessage.java
	 * Author: Max Wallhem
	 */

	package mw223se_assign2;

	import java.time.LocalDate;
	import java.time.format.DateTimeFormatter;
	import java.time.format.DateTimeParseException;
	import java.time.format.ResolverStyle;
	import java.util.ArrayList;
	import java.util.Scanner;

	/**
	 * Controls the user input to see if it's a valid ID-number and which gender it
	 * belongs to. Also checks to see if two ID-numbers are equal.
	 * 
	 * @version 1.1 24 Jun 2021
	 * @author MaxWallhem
	 *
	 */
	public class SweID {

	    public static void main(String[] args) {
	        // Start Scanner
	        Scanner input = new Scanner(System.in);

	        System.out.print("Type a Swedish personal identity number on the format \"YYMMDD-NNNN\": ");
	        String iD1 = input.next();
	        if (iD1.length() != 11) {
	            System.out.println("\nThs is not a correct length for an identity number. Please try again!");
	            SweID.main(null);
	        } else if (iD1.charAt(6) != '-') {
	            System.out.println("\nThat is not a correct format for an identity number. Please try again!");
	            SweID.main(null);
	        }

	        System.out.print("Type another Swedish personal identity number on the same format: ");
	        String iD2 = input.next();
	        if (iD2.length() != 11) {
	            System.out.println("\nThs is not a correct length for an identity number. Please try again!");
	            SweID.main(null);
	        } else if (iD2.charAt(6) != '-') {
	            System.out.println("\nThat is not a correct format for an identity number. Please try again!");
	            SweID.main(null);
	        }

	        // Create a list with the input
	        ArrayList<String> list = new ArrayList<String>();
	        list.add(iD1);
	        list.add(iD2);

	        for (int i = 0; i <= list.size() - 1; i++) {
	            System.out.println("\n" + list.get(i));

	            if (isCorrect(list.get(i))) {
	                System.out.println("This is a valid ID-number. (Date, format & check-digit controlled).");
	                if (isFemaleNumber(list.get(i))) {
	                    System.out.println("This ID-number belongs to a woman.");
	                } else {
	                    System.out.println("This ID-number balongs to a man.");
	                }
	            }
	        }

	        areEqual(list.get(0), list.get(1));

	        // Close Scanner
	        input.close();
	    }

	    // Required method from assignment
	    public static String getFirstPart(String sweID) {
	        return sweID.substring(0, 6);
	    }

	    // Required method from assignment
	    public static String getSecondPart(String sweID) {
	        return sweID.substring(7, 11);
	    }

	    // Required method from assignment
	    public static boolean isFemaleNumber(String sweID) {
	        if (sweID.charAt(9) % 2 == 0) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    // Required method from assignment
	    public static void areEqual(String id1, String id2) {
	        System.out.print("\n" + id1 + " & " + id2);
	        if (id1.equalsIgnoreCase(id2)) {
	            System.out.print(" - These two ID-numbers are identical!");
	        } else {
	            System.out.print(" - These two ID-numbers are not the same!");
	        }
	    }

	    public static boolean isValidDate(String date) {

	        boolean valid = false;
	        /*
	         * Making the assumption that no user is >= 100 years old. So if user input
	         * start with <= 20 we assume they where born 20XX. Else we assume they where
	         * born 19XX
	         */
	        if (Integer.parseInt((date.substring(0, 2))) <= 21) {
	            date = "20".concat(date);
	        } else {
	            date = "19".concat(date);
	        }

	        // Controlling if the dates are valid using LocalDate Class
	        try {
	            LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT));
	            valid = true;

	        } catch (DateTimeParseException e) {
	            valid = false;
	        }
	        return valid;
	    }

	    // Required method from assignment
	    public static boolean isCorrect(String sweID) {

	        // Start with trimming the input.
	        /*
	         * A quick and effective way to get rid of the "-" in the string is the method
	         * below. 
	         * String trimmed = sweID.replaceAll("-", ""); 
	         * But we use two of the required "get-methods" instead.
	         */
	        String trimmed = getFirstPart(sweID).concat(getSecondPart(sweID));

	        // Check correct days & month. Year not checked since century isn't submitted
	        if ((Integer.parseInt(trimmed.substring(2, 4))) > 12) {
	            System.out.println(sweID + " is not a correct number (unvalid month)");
	            return false;
	        } else if (!isValidDate(trimmed.substring(0, 6))) {
	            System.out.println(sweID + " is not a correct number (unvalid day)");
	            return false;
	        }

	        /* Check last digit according to Luhn Algorithm */
	        int sum = 0;
	        boolean alternate = true;
	        for (int i = 0; i < (trimmed.length() - 1); i++) {
	            int n = Integer.parseInt(trimmed.substring(i, i + 1));
	            if (alternate) {
	                n *= 2;
	                if (n > 9) {
	                    n -= 9;
	                }
	            }
	            sum += n;
	            alternate = !alternate;
	        }

	        if (!(((sum * 9) % 10) == Integer.parseInt(trimmed.substring(9, 10)))) {
	            System.out.println(sweID + " is not a correct number (unvalid check/last digit)");
	            return false;
	        }

	        return true;
	    }
	}

	
	
	


