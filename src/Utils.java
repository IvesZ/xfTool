//import com.sun.deploy.util.StringUtils;

import java.util.*;

/**
 * @author zzhuo
 * @title StringUtils
 * @time 2024/7/11 13:22
 * @description
 */
public class Utils {
    public static List<List<String>> getSubList(String key, List<String> contents, List<String> info){
        int index1=-1, index2=-1;
        List<List<String>> list = new ArrayList<>();

        for(int i=0;i<contents.size();i++){
            String temp = contents.get(i).split("】")[0].replace("【","");
            if(temp.equals(key)){
                info.add(contents.get(i));
                index1=i;
                index2 = index1 + Integer.parseInt(
                         contents.get(i).split("，")[0]
                         .replaceAll(".*共(\\d+)只.*", "$1"));
                break;
            }
        }
        System.out.println("正在处理"+key+", 共有"+(index2-index1)+"只");

        if((index2-index1==0) || index1==-1 || index2==-1) return list;

        for(int i=index1+1;i<=index2;i++){
            List<String> temp = unfoldString(contents.get(i));
            list.add(temp);
        }
        System.out.println("已处理"+ list.size() +"只");
        return list;
    }

    public static List<String> unfoldString(String s){
//        System.out.println(s);
        List<String> ret = new ArrayList<>();

        int index = s.lastIndexOf("(");

        if(index==-1){
            System.out.println("格式不正确未找到符号()");
        }

        String subStr1 = s.substring(0,index);

        String subStr2 = s.substring(index).replaceAll("\\(|\\)","");

        String[] tempList = subStr1.split("，");
        ret.addAll(Arrays.asList(tempList));

        tempList = subStr2.split("，");
        ret.addAll(Arrays.asList(tempList));

        return ret;
    }

    public static HashMap<String, List<String>> getYCMap(List<String> inputs){
        HashMap<String, List<String>> retMap = new HashMap<>();

        for (int i = 0; i < inputs.size(); i++) {
            if(inputs.get(i).charAt(0)!='【') continue;
            if(inputs.get(i).lastIndexOf(")")!=inputs.get(i).length()-1) continue;

            String ss = inputs.get(i).substring(
                    inputs.get(i).lastIndexOf("】")+2
            );

//            String[] tempStr = ss.replaceAll("\\s|\\(|\\)|\\*","")
//                    .split(",");

            String[] tempStr = ss.replace(" ","").split(",");

            String key = tempStr[0];
            List<String> contents = new ArrayList<>();
            int index = tempStr.length-1;
//            System.out.println(index);
            contents.add(tempStr[index-2].substring(1));
            contents.add(tempStr[index-1]);
            contents.add(tempStr[index].replace(")",""));

            retMap.put(key,contents);
        }
//        System.out.println("读取"+retMap.size()+"条");
        return retMap;
    }


//    public static double getSum(List<List<String>> list){
//        double ret = 0;
//
//        for (int i = 0; i < list.size(); i++) {
//            double temp = list.get(i).get()
//            ret+=list.get(i).get()
//        }
//
//    }

    public static void addStr(StringBuffer sb, List<String> xz, Map<String,List<String>> ycMap){
        if(ycMap.containsKey(xz.get(1))){
            List<String> content = ycMap.get(xz.get(1));
            xz.set(7,"("+content.get(0)+"，剩余"+content.get(1));
            xz.set(8,"估值"+content.get(2)+")");
            sb.append(String.join("，",xz));
        }else {
            sb.append(String.join("，",xz.subList(0,7)));
        }
    }

    public static HashMap<String,String> getConfig(List<String> str){
        HashMap<String,String> config = new HashMap<>();

        for (String s: str) {
            String[] temp = s.split(":");
            if(temp.length != 2){
                System.out.println("配置文件格式错误：\n"+s+"\n");
                continue;
            }
            config.put(temp[0],temp[1]);
        }
        System.out.println("读取"+config.size()+"条配置");
        return config;
    }

    public static void jxf(List<String> dataList, StringBuffer sb,HashMap<String,String> config){
        if(!config.containsKey("date")){
            sb.append("【继续发】\n");
            System.out.println("未找到筛选时间配置，跳过继续发");
            return;
        }
        List<String> tempList = new ArrayList<>();
        String date = config.get("date");
        System.out.println("开始筛选继续发，筛选时间"+date);
        for (String str: dataList) {
            if(str.contains("【继续发】")) break;
            if(str.contains(date)){
                tempList.add(str+"\n");
            }
        }

        sb.append("【继续发】\t"+"【共" + tempList.size() +"只】\n");
        for (String str:tempList) {
            sb.append(str);
        }

    }




}
