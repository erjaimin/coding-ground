import java.util.Scanner;

/**
 * A program to test the TextItems class.
 *
 * @author Mark Young (A00000000)
 */
public class TestMyTextItems {

    public static void main(String[] args) {
    
        // One Item File
        TextItems ti = new TextItems("OneItem.txt");
        System.out.println("---Displaying only item from OneItem---");
        ti.displayItem("Only Item");
        System.out.println("---End of item---");
        pause();
        
        // Two Item File
        ti = new TextItems("MiniItems.txt");
        System.out.println("---Displaying First Item from MiniItems---");
        ti.displayItem("First Item");
        System.out.println("---End of item---");
        pause();
        System.out.println("---Displaying Second Item from MiniItems---");
        ti.displayItem("Second Item");
        System.out.println("---End of item---");
        pause();

        // Many Item File
        ti = new TextItems("ManyItems.txt");
        System.out.println("---Displaying many items from ManyItems---");
        for (int i = 1; i <= 1000; i+=100) {
            ti.displayItem("Item #" + i);
        }
        System.out.println("---End of items from ManyItems---");
        pause();

        // Item fail
        System.out.println("---Displaying No Such Item from ManyItems---");
        ti.displayItem("No Such Item");
        System.out.println("---End of non-existent item---");
        pause();

        // With pauses in
        ti = new TextItems("WithPauses.txt");
        System.out.println("---Displaying First Item With Pauses---");
        ti.displayItem("First Item");
        System.out.println("---End of item---");
        pause();
        System.out.println("---Displaying Second Item from WithPauses---");
        ti.displayItem("Second Item");
        System.out.println("---End of item---");
        System.out.println("---Displaying null from WithPauses---");
        ti.displayItem(null);
        System.out.println("---End of item---");
        pause();

        // Creation fail #1
        System.out.println("---Creation Fail #1---");
        ti = new TextItems("NoSuchFile.txt");
        System.out.println("---Displaying No Such from NoSuchFile---");
        ti.displayItem("No Such");
        System.out.println("---End of item---");
        pause();
        
        // (You must comment out the previous creation fail
        // in order to see whether this one works as it should!)
        // Creation fail #2
        System.out.println("---Creation Fail #2---");
        ti = new TextItems("Truncated.txt");
        System.out.println("---Displaying First Item from Truncated---");
        ti.displayItem("First Item");
        System.out.println("---End of item---");
        System.out.println("---Displaying Second Item from Truncated---");
        ti.displayItem("Second Item");
        System.out.println("---End of item---");
        System.out.println("---Displaying Third Item from Truncated---");
        ti.displayItem("Third Item");
        System.out.println("---End of item---");
        pause();

        System.out.println("\nThis is the end of the program\n");
    }
    
    public static void pause() {
        Scanner kbd = new Scanner(System.in);
        System.out.print("..........PRESS ENTER TO CONTINUE..........");
        kbd.nextLine();
        System.out.println();
    }

}
