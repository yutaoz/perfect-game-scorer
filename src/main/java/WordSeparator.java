import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WordSeparator {

    //static String[] words = new String[3614]; // 3614 is max number of tags possible found from wordcount
    static ArrayList<String> words = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException {
        // setting up file and file reader
        File file = new File("src\\main\\resources\\data.txt");
        Scanner scanner = new Scanner(file);

        words.add("Action");
        words.add("2D");


        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(line, ",");
            // skip first 2 tokens as they are not tags
            st.nextToken();
            st.nextToken();

            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                String container = "";
                // check in arraylist to see if tag already exists
                for (int i = 0; i < words.size(); i++) {
                    if (word.equals(words.get(i))) {
                        container += "x"; // if the tag was found in the array, make the container note that
                        i = words.size() + 1;
                    }
                }
                if (container.equals("")) {
                    // if the tag was never found, add to arraylist
                    words.add(word);
                }
                else {
                    //if the tag was found, do nothing
                }
            }

        }
        // print out all tags in arraylist (should be unique)
        for (int x = 0; x < words.size(); x++) {
            System.out.println(words.get(x));
        }


    }
}
