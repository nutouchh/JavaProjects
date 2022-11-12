package lab24;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    private static String path = "https://www.mirea.ru";

    public static void main(String[] args) {

        try {
            Document document = Jsoup.connect(path).get();
            Elements imgs = document.select("img");
            Pattern pattern = Pattern.compile("[\\w-]+.\\w+$");

//            Pattern d = Pattern.compile("^(0?[1-9]|1[012])\\/(0?[1-9]|[12][0-9]|3[01])\\/\\d{1,4}/$");

            Matcher matcher;

            String src;
            URL url; //класс хранящий путь к ресурсу в интернете
            InputStream in;   //класс байтового потока
            for (Element img : imgs) {
                src = img.attr("abs:src"); //значение атрибута "src"

                if (src != "" && ( src.contains("jpg") || src.contains("jpeg") || src.contains("png") ||
                                   src.contains("svg") || src.contains("webp") || src.contains("gif"))) {
                    System.out.println(src); //выводим адрес картинки
                    url = new URL(src);
                    in = url.openStream();

                    matcher = pattern.matcher(src);
                    matcher.find();

                    Files.copy( //копируем ссылки из потока в папку
                            in,
                            Paths.get(
                                    "C:/Users/Anna/IdeaProjects/Lab's/src/ru/mirea/projects/lab23_24/imgs/" +
                                            matcher.group()
                            ),
                            StandardCopyOption.REPLACE_EXISTING //замена файла
                    );

                    in.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
