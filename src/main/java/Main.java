import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static File file = new File("src\\main\\resources\\data.txt");
    static File file1 = new File("src\\main\\resources\\wordlist.txt");
    static File outputFile = new File("src\\main\\resources\\outputfile.txt");

    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter(outputFile);
        String[][] tagScores = new String[301][2]; // 301 unique tags, 2 columns - 1 for the tag and 1 for the score
        Scanner scanner = new Scanner(file1);
        int counter = 0; // counter to add tag to array index

        while (scanner.hasNextLine() && counter < 301) {// reading through each tag
            String tag = scanner.nextLine();
            tagScores[counter][0] = tag; // first column will be the tag
            tagScores[counter][1] = Double.toString(averageScore(tag)); // second column will be score
            counter++;
        }
        
        // writing tag and score data to output file
        for (int i = 0; i < tagScores.length; i++) {
            writer.write(tagScores[i][0] + " | " + tagScores[i][1] + "\n");
        }
        writer.close();



    }

    public static double averageScore(String tag) throws FileNotFoundException { // method to calculate average score of a tag
        Scanner scan = new Scanner(file);
        String line;
        double total = 0, average;
        int count = 0;

        // for each line....
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            StringTokenizer st = new StringTokenizer(line, ",");
            boolean done = false;
            st.nextToken();
            int voteCount = Integer.parseInt(st.nextToken().trim());

            // tokenize each line and iterate through tokens until the tag is found
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
