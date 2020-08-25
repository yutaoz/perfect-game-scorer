import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;

public class StoreData {
    public static void main(String[] args) throws IOException {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);

        File file = new File("src\\main\\resources\\data.txt");
        PrintWriter writer = new PrintWriter(file);

        String url = "https://steam250.com/top250";
        HtmlPage page = client.getPage(url);

        for (int i = 1; i < 251; i++) {
            HtmlElement element1 = ((HtmlElement) page.getFirstByXPath("//div[@id='" + i + "']//div//span[@class='score']"));
            HtmlElement element2 = ((HtmlElement) page.getFirstByXPath("//div[@id='" + i + "']//div//span[@class='votes']"));
            String score = element1.asText();
            String votes = element2.asText();

            votes = votes.substring(0, votes.length() - 5);
            StringTokenizer st = new StringTokenizer(votes, ",");
            String finalvote = "";
            while (st.hasMoreTokens()) {
                String part = st.nextToken();
                finalvote += part;
            }

            writer.println(score + "," + finalvote);

        }

        writer.close();





    }
}
