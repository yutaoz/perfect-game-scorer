import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static File file = new File("src\\main\\resources\\data.txt");
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(averageScore("tag1"));

    }

    public static double averageScore(String tag) throws FileNotFoundException { // method to calculate average score of a tag
        Scanner scan = new Scanner(file);
        String line;
        double total = 0, average;
        int count = 0;

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            StringTokenizer st = new StringTokenizer(line, ",");
            boolean done = false;
            st.nextToken();
            int voteCount = Integer.parseInt(st.nextToken().trim());

            while (st.hasMoreTokens() && !done) { // for each word in the line, check if it matches tag, if so add the score to total
                String word = st.nextToken();
                if (word.equals(tag)) {
                    count += voteCount;
                    total += Double.parseDouble(line.substring(0,4)) * voteCount; // add the score given (first 3 characters) to total
                    done = true;
                }
            }
            System.out.println(voteCount);
        }
        average = total / count;
        return average;
    }
}
