import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner inputKey = new Scanner(System.in);
        System.out.print("Enter the API key from website: ");
        String KEY = inputKey.nextLine();

        System.out.print("Enter the base currency in code format (e.x USD): ");
        Scanner inputCurrency = new Scanner(System.in);
        String baseCurrency = inputCurrency.nextLine();

        System.out.println(getData(KEY, baseCurrency));
        inputKey.close();
        inputCurrency.close();

    }

    private static String getData(String key, String baseCurrency) throws IOException {
        InputStream is = new URL(
                "https://v6.exchangerate-api.com/v6/" + key + "/latest/" + baseCurrency)
                .openStream();

        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = readAll(rd);
        return jsonText;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
