package assignments2017.a4.TesterForPosting;

import java.util.ArrayList;
import java.io.PrintStream;

public class Grader {

	public static void main(String[] args) {
		PrintStream out = System.out;
		// Uncomment this to silence student outputs.
		/*
		 * System.setOut(new PrintStream(new OutputStream() { public void
		 * write(int b) { } }));
		 */
		Grader grader = new Grader();
		Integer totalScore = 0;
		ArrayList<Test> tests = new ArrayList<Test>();
		tests.add(new TestZero(1, out));
		tests.add(new TestOne(1000, out));
		tests.add(new TestTwo(3000, out));
		for (Test t : tests) {
			totalScore += t.run();
		}
		out.println("Total Score : " + totalScore);
		System.exit(totalScore);
	}
}
