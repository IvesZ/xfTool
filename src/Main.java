//import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zzhuo
 * @title ${NAME}
 * @time 2024/7/11 10:50
 * @description
 */
public class Main {

    private static final String SCP = "SCP";
    private static final String CP = "CP";
    private static final String MTN = "MTN";

    private static final String lineMark = "==========================================================";

    public static void main(String[] args) {

        System.out.println("获取当前目录");
        System.out.println(System.getProperty("user.dir"));

        String filePath1 = ".\\inputs\\当日新增.txt";
        String filePath2 = ".\\inputs\\一创固收.txt";
        String filePath3 = ".\\inputs\\昨日新增.txt";
        String filePath4 = ".\\inputs\\config.txt";

        String savePath = ".\\outputs\\";

//        String filePath1 = "./inputs/当日新增.txt";
//        String filePath2 = "./inputs/一创固收.txt";
//        String filePath3 = "./inputs/昨日新增.txt";
//        String filePath4 = "./inputs/config.txt";
//
//        String savePath = "./outputs/";


        List<List<String>> scpList;
        List<List<String>> cpList;
        List<List<String>> mtnList;
        HashMap<String, List<String>> ycMap;
        HashMap<String, String> config;

        List<String> info = new ArrayList<>();

        try{
            System.out.println("正在读取："+filePath4);
            List<String> configStr = FileUtils.readFile(filePath4);
            System.out.println("正在缓存配置文件");
            config = Utils.getConfig(configStr);
            System.out.println("缓存完毕");
            System.out.println(lineMark);

            System.out.println("正在读取："+filePath1);
            List<String> xzList = FileUtils.readFile(filePath1);
            System.out.println("正在读取："+filePath2);
            List<String> ycList = FileUtils.readFile(filePath2);
            System.out.println("正在读取："+filePath3);
            List<String> zrList = FileUtils.readFile(filePath3);
            System.out.println("读取完成");
            System.out.println(lineMark);

            System.out.println("正在读取列表SCP");
            scpList = Utils.getSubList(SCP,xzList,info);
            System.out.println("正在读取列表CP");
            cpList = Utils.getSubList(CP,xzList,info);
            System.out.println("正在读取列表MIT");
            mtnList = Utils.getSubList(MTN,xzList,info);
            System.out.println("正在读取一创固收列表");
            ycMap = Utils.getYCMap(ycList);
            System.out.println("新增列表读取完毕");
            System.out.println(lineMark);


            String time = xzList.get(0);
            StringBuffer sb = new StringBuffer();

            System.out.println("正在匹配SCP数据");
            sb.append(time);sb.append("\n");
//            System.out.println("info size"+info.size());
            sb.append(info.get(0));sb.append("\n");
            for (List<String> stringList : scpList) {
                stringList.set(7,"(剩余 Y");
                stringList.set(8,"估值)");
                sb.append(String.join("，",stringList));
                sb.append("\n");
            }

            System.out.println("正在匹配CP数据");
            sb.append("\n");
            sb.append(time);sb.append("\n");
            sb.append(info.get(1));sb.append("\n");
            for (List<String> strings : cpList) {
                Utils.addStr(sb, strings, ycMap);
                sb.append("\n");
            }

            System.out.println("正在匹配MTN数据");
            sb.append("\n");
            sb.append(time);sb.append("\n");
            sb.append(info.get(2));sb.append("\n");
            for (List<String> strings : mtnList) {
                Utils.addStr(sb, strings, ycMap);
                sb.append("\n");
            }

            if(config.containsKey("date")){
                System.out.println("正在匹配继续发数据");
                sb.append("\n");
                sb.append(time);sb.append("\n");
                Utils.jxf(zrList,sb,config);
            }else {
                System.out.println("未找到继续发筛选日期，已跳过继续发");
            }

            System.out.println("数据匹配完毕");

            String saveStr = sb.toString().replace("，("," (").replace("*","");
            System.out.println("正在保存数据");

            savePath=savePath+time.replace("/","-");
            if(config.containsKey("author")){
                savePath=savePath+"-by-"+config.get("author");
            }
            savePath=savePath+".txt";

            FileUtils.writeFile(savePath,saveStr);
            System.out.println("已保存文件"+savePath);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }






    }
}