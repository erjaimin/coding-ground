import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Implement a postfix calculator using a Deque for a stack.
 * 
 * @author Khalid Alzhrani (A00361007)
 *
 */
public class Calculator {

	/**
	 * mathematical operators
	 */
	private static final List<String> ALLOWED_OPERATORS = Arrays.asList("+", "-",
			"*", "/", "%", "^", "//");

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		displayHeading();

		Scanner inputScanner = new Scanner(System.in);
		// sentinel-controlled loop
		while (true) {
			System.out.println();
			System.out.print(">>>");
			String input = inputScanner.nextLine();

			// exit scenario
			if ("quit".equalsIgnoreCase(input))
				break;

			ArrayDeque<Double> deque = new ArrayDeque<>();
			Scanner scan = new Scanner(input);
			scan.useDelimiter("\\s");
			String token = "";
			try {
				while (scan.hasNext()) {
					token = scan.next();
					// identify the token is valid operator
					if (ALLOWED_OPERATORS.contains(token)) {
						Double operand2 = deque.pop();
						Double operand1 = deque.pop();
						double result = performOperation(token, operand1,
								operand2);
						System.out.println(operand1 + " " + token + " "
								+ operand2 + " = " + result);
						deque.push(result);
					} else {
						// add operand to stack if valid
						deque.push(Double.parseDouble(token));
					}
				} // end while
				if ((deque.size() == 1)) {
					System.out.println();
					System.out.println("Result: " + deque.pop());
				} // end if
				if (deque.size() > 1) {
					System.err.println(
							"Error: too many numbers in that expression!");
				} // end if
			} catch (NoSuchElementException e) {
				System.err.println(
						"Error: not enough numbers in that expression!");
			} catch (NumberFormatException e) {
				System.err.println("Error: Unknown operator: " + token);
			} finally {
				if (scan != null)
					scan.close();
				System.err.flush();
			}
		} // end while
		inputScanner.close();
	} // end main

	/**
	 * Returns the result of operation performed on given operands
	 * 
	 * @param token
	 *            operation to be performed
	 * @param operand1
	 *            first operand
	 * @param operand2
	 *            second operand
	 * @return the result
	 */
	private static double performOperation(String token, Double operand1,
			Double operand2) {
		double result = 0.0;
		try {
			switch (token) {
			case "+":
				result = operand1 + operand2;
				break;
			case "-":
				result = operand1 - operand2;
				break;
			case "*":
				result = operand1 * operand2;
				break;
			case "/":
				result = operand1 / operand2;
				break;
			case "%":
				result = operand1 % operand2;
				break;
			case "^":
				result = Math.pow(operand1, operand2);
				break;
			case "//":
				result = (int) (operand1 / operand2);
				break;
			} // end switch
		} catch (ArithmeticException e) {
			System.err.println(e.getMessage());
		}
		return result;
	} // end performOperation

	/**
	 * prepares the welcome message and displays it
	 */
	private static void displayHeading() {
		StringBuilder sb = new StringBuilder();
		sb.append("Postfix Calculator").append("\n")
			.append("------------------").append("\n\n")
			.append("by Khalid Alzhrani (A00361007)").append("\n\n")
			.append("This program reads and evaluates postfix expressions.")
			.append("\n").append("Enter your expression at the >>> sign.")
			.append("\n").append("Enter QUIT to exit the program.")
			.append("\n\n");
		System.out.println(sb.toString());
	} // end displayHeading
}
