import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is implemented to show text one page a t a time. 
 * The text is drawn from the input file.
 * 
 * @author Khalid Alzhrani (A00361007)
 *
 */
public class TextItems {

	// list of text items containing title and contents
	private List<TextItemData> textItemsList;
	// regex for items separator
	private static final String ITEM_SEPARATOR = "-{80}";
	// regex to identify pause
	private static final String PAUSE_PATTERN = "@{80}";

	/**
	 * constructor opens given input file, scans the contents and parses the
	 * text items. Also it deals with bad inputs like truncated file, absent
	 * file.
	 * 
	 * @param fileName
	 *            - input file to be parsed
	 */
	public TextItems(String fileName) {
		this.textItemsList = new ArrayList<>();
		if (fileName != null && !fileName.isEmpty()) {
			try (Scanner scan = new Scanner(new File(fileName))) {
				String currentTitle = "";
				List<String> currentTextLines = new ArrayList<>();
				// read until end of file
				while (scan.hasNextLine()) {
					String currentLine = scan.nextLine();
					// if current line isn't item separator then 
					// continue adding to current item
					if (!currentLine.matches(ITEM_SEPARATOR)) {
						if (currentTitle.isEmpty()) {
							currentTitle = currentLine;
						} else {
							currentTextLines.add(currentLine);
						}
					} else { 
						// add to list & clear the data members for next item
						textItemsList.add(new TextItemData(currentTitle,
								currentTextLines));
						currentTitle = "";
						currentTextLines = new ArrayList<>();
					}
				}
				// check for truncated file
				if (!currentTextLines.isEmpty()) {
					// remove all previously read items
					textItemsList.clear();
					throw new Exception(
							"File '" + fileName + "' is improperly formatted");
				}
			} catch (FileNotFoundException e) {
				System.err.println(
						"Could not open file '" + fileName + "' for input");
				System.err.flush();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else{
			System.err.println("input file can't be null or empty!");
		}
	} // end constructor

	/**
	 * 
	 * @param displayTitle
	 */
	public void displayItem(String displayTitle) {
		if (displayTitle != null) {
			// iterate through list of items to find the matching title
			for (TextItemData textItemData : textItemsList) {
				if (textItemData.getTitle().equals(displayTitle)) {
					for (String line : textItemData.getLines()) {
						if (line.matches(PAUSE_PATTERN)) {
							pause();
						} else {
							System.out.println(line);
						}
					} // end for
					// do not continue if the item found
					break;
				} // end if
			} // end for
		} // end if
	} // end displayItem

	/**
	 * the method stops the program execution and wait for user to press the
	 * enter key
	 */
	private static void pause() {
		Scanner kbd = new Scanner(System.in);
		System.out.println();
		System.out.print("...press enter...");
		kbd.nextLine();
		System.out.println();
	} // end pause

	// private class for holding text item details
	private class TextItemData {

		// title of the text item
		public String title;
		// following lines of the text item (body)
		public List<String> lines;

		/**
		 * creates an instance of TextItemData
		 * 
		 * @param title
		 *            - heading of the text item
		 * @param lines
		 *            - description of the text item
		 */
		public TextItemData(String title, List<String> lines) {
			this.title = title;
			this.lines = lines;
		} // end constructor

		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		} // end getTitle

		/**
		 * @return the lines
		 */
		public List<String> getLines() {
			return lines;
		} // end getLines
	} // end TextItemData
} // end TextItems
