/**
 * @author zzhuo
 * @title FileUntils
 * @time 2024/7/11 10:50
 * @description
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    // 读取文件内容
    public static List<String> readFile(String filePath) {

        List<String> ret = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ret.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(filePath+" 文件未找到");
            System.exit(0);
        }
        System.out.println("读取"+ret.size()+"条");
        return ret;
    }

    // 写入内容到文件
    public static void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
