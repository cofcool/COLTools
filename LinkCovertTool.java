import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by CofCool.net
 */
public class LinkCovertTool {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        LinkCovertTool.covert(path, path + "/output.md");
    }

    private static final String FILE_TYPE_LINUX = ".desktop";
    private static final String FILE_TYPE_OSX = ".webloc";


    public static void covert(String inputPath, String outputPath)    {
        List<File> files = new ArrayList<>();

        File inputFile = new File(inputPath);
        if (inputFile.isDirectory()) {
            File[] allFiles = inputFile.listFiles();
            files = Arrays.asList(allFiles);
        }else {
            files.add(inputFile);
        }

        for (File file : files) {

            if (!file.getName().startsWith(".")) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    String content = null;
                    while ((content = bufferedReader.readLine()) != null) {
                        if (file.getName().endsWith(FILE_TYPE_LINUX)) {
                            String reg = "http.+";
                            outputFile(outputPath, content, file, reg);
                        } else if (file.getName().endsWith(FILE_TYPE_OSX)) {
                            String reg = "(http(.+)</s)";
                            outputFile(outputPath, content, file, reg);
                        }
                    }

                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void outputFile(String outputPath, String content, File file, String reg)   {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(content);
        int line = 0;
        while (matcher.find())  {
            String result = matcher.group(line).replace("</s", "");
            line++;

            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputPath, true));
                String fileName = file.getName();

                bufferedWriter.write("* [" + fileName.substring(0, fileName.lastIndexOf(".")) + "]" + "(" + result + ")" + "\n");
                bufferedWriter.flush();
            }catch (IOException e)  {
                e.printStackTrace();
            }

        }
    }

}
