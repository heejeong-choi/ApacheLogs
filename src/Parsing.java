import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parsing {

    public static String readFile(String fileName) {
        StringBuilder string = new StringBuilder();
        FileReader f = null;
        char[] buff = new char[1024];

        try {
            f = new FileReader(fileName);
            while (f.read(buff) != -1) {
                string.append(buff);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                f.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return string.toString();
    }

    public static void main(String[] args) throws IOException {
        String apache = readFile("D:\\90factory\\apacheHW\\apache_logs.txt");

        /* File file = new File("D:\\90factory\\apacheHW\\user-agent.csv");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps); */

        String regexIp = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";
        String regexUserIdent = "(\\s-\\s-\\s)";
        String regexDate = "(\\[\\d{2}\\/\\w{3}\\/\\d{4}:\\d{2}:\\d{2}:\\d{2}\\s\\+\\d{4}\\])";
        String regexUri = "(\\s\\\"(GET|POST|OPTIONS|HEAD)\\s\\/(\\??\\s?|\\??\\w.+)HTTP\\/\\d\\.\\d\\\")";
        String regexResponse = "(\\s(\\d{3}|-))";
        String regexDataAmount = "(\\s(\\d*|-))";
        String regexUserURL = "(\\s(\\\"(-|(http|https):\\/\\/(\\w+:{0,1}\\w*@)?(\\S+)(:\\d+)?(\\/|\\/([\\w#!:.?+=&%@!\\-\\/]))?)\\\"))";
        String regexUserOS = "(\\s\\\"(.+))";
        // String regex_userAgent = "(\\s\".+?[/\\s][\\d.]+)";

        String regexUserAgent = "(\\\\s\\\".+?[/\\\\s][\\\\d.]+)";

        Pattern pattern = Pattern.compile(regexIp+regexUserIdent+regexDate+regexUri+regexResponse+regexDataAmount+regexUserURL+regexUserOS);
        Pattern userAgent = Pattern.compile(regexUserAgent);
        Matcher matcher = pattern.matcher(apache);
        Matcher matcher2 = userAgent.matcher(apache);

        while (matcher.find()) {
            ArrayList<String> log = new ArrayList<>(1);
            log.add(matcher.group(1));
            log.add(matcher.group(2));
            log.add(matcher.group(3));
            log.add(matcher.group(4));
            log.add(matcher.group(7));
            log.add(matcher.group(9));
            log.add(matcher.group(11));
            log.add(matcher.group(20));

            System.out.println(log);

            ArrayList<String> user_agent = new ArrayList<>(1);
            user_agent.add(matcher2.group(1));
            System.out.println(userAgent);
        }

        //System.out.println(log.get(7));//0번째 index 출력

        // System.out.print(String.format("%s\t,%s\t,%s\t,%s\t,%s\t,%s\t,%s\t,%s\n", matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(7), matcher.group(9), matcher.group(11), matcher.group(20)));
        /* }
        float percentage = (i/size)*100;
        System.out.println(percentage + "%"); */
    }
}