package lab25_26;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    static String urlPath = "https://www.moscowmap.ru/metro.html#lines";

    public static Data getData() {
        try {
            // Получения элементов со страницы
            Document doc = Jsoup.connect(urlPath).maxBodySize(0).get();
            Elements linesHTML = doc.select(
                    ".t-metrostation-list-header[data-line]"
            );
            Elements stationsHTML = doc.select(
                    ".t-metrostation-list-table[data-line]"
            );

            // Запись станций в данные
            Map<String, ArrayList<String>> stationsData = new HashMap<>();
            ArrayList<String> stations = new ArrayList<>();
            for (Element stationElem : stationsHTML) {
                Pattern pattern = Pattern.compile("[0-9]. (?<group>[^0-9]+)");
                Matcher matcher = pattern.matcher(stationElem.text());

                while (matcher.find()) {
                    stations.add(matcher.group("group").trim());
                }

                stationsData.put(
                        String.valueOf(stationElem.attr("data-line")),
                        (ArrayList<String>) stations.clone()
                );

                stations.clear();
            }

            // Запись линий в данные
            ArrayList<Line> lines = new ArrayList<>();
            for (Element lineElem : linesHTML) {
                lines.add(
                        new Line(lineElem.attr("data-line"), lineElem.text().trim())
                );
            }

            return new Data(lines, stationsData);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return new Data(null, null);
    }

    public static void main(String[] args) {
        Data data = getData();
        Gson gson = new Gson();

        try {
            OutputStream outputStream = new FileOutputStream(
                    "C:\\Users\\Anna\\IdeaProjects\\Lab's\\src\\ru\\mirea\\projects\\lab23_24\\src\\main\\java\\lab25_26\\data.json"
            );
            Writer outputStreamWriter = new OutputStreamWriter(
                    outputStream,
                    "UTF-8"
            );

            outputStreamWriter.write(gson.toJson(data));
            outputStreamWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}