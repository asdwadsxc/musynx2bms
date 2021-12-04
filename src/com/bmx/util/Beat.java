package com.bmx.util;

import com.bmx.musync.MusyncReadChart;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author asdwadsxc
 * @create 2021-12-02 17:17
 */
public class Beat {

    //节拍计算
    public static String[] beat(String string) {
        double v = (Double.valueOf(string) * 0.0001) / ((60 * 1000) / Double.valueOf(MusyncReadChart.bpm)) / 4;
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(5);
        String beat = nf.format(v);
        String s = dicimalToFraction(Double.parseDouble(beat));
        String[] split = s.split("/");
        return split;
    }

    //note排列
    private static List<String> arrangeNote = new ArrayList<>();

    //背景note
    public static List<String> arrangeBgm(List<String[]> strings) {
        if(strings.size()==0){
            return null;
        }
        List<String[]> arrLine = new ArrayList<>();
        List<String[]> arrLineRe = new ArrayList<>();
        arrLine.add(strings.get(0));
        for (int i = 1; i < strings.size(); i++) {
            String[] bgmNoteA = strings.get(i - 1);
            String[] bgmNoteB = strings.get(i);
            if (bgmNoteB[0].equals(bgmNoteA[0])) {
                if ((bgmNoteB[1].equals(bgmNoteA[1])) && (bgmNoteB[2].equals(bgmNoteA[2]))) {
                    arrLineRe.add(bgmNoteB);
                } else {
                    arrLine.add(bgmNoteB);
                }
            } else {
                arrangeNote.add(arrangeLine(arrLine));
                arrLine.clear();
                arrLine.add(bgmNoteB);
            }
        }
        arrangeNote.add(arrangeLine(arrLine));
        if (!arrLineRe.isEmpty()) {
            arrangeBgm(arrLineRe);
        }
        return arrangeNote;
    }

    //打击note
    public static List<String> arrangePattern(List<String[]> noteList) {
        List<String> arranged = new ArrayList<>();
        for (int j = 1; j <= 8; j++) {
            arrangeNote.clear();
            List<String[]> arrLine = new ArrayList<>();
            for (int i = 0; i < noteList.size(); i++) {
                String[] strings = noteList.get(i);
                if (Integer.valueOf(strings[4]) == j) {
                    arrLine.add(strings);
                }
            }
            List<String> strings = new ArrayList<>();
            strings = arrangeBgm(arrLine);
            if(strings==null){
                continue;
            }
            switch (j) {
                case 1:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("16:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 2:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("11:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 3:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("12:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 4:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("13:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 5:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("14:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 6:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("15:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 7:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("18:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 8:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("19:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                default:
                    break;
            }
        }
        return arranged;
    }

    //长键
    public static List<String> arrangePatternLn(List<String[]> longList) {
        List<String> arranged = new ArrayList<>();
        for (int j = 1; j <= 8; j++) {
            arrangeNote.clear();
            List<String[]> arrLine = new ArrayList<>();
            for (int i = 0; i < longList.size(); i++) {
                String[] strings = longList.get(i);
                if (Integer.valueOf(strings[4]) == j) {
                    arrLine.add(strings);
                }
            }
            List<String> strings = new ArrayList<>();
            strings = arrangeBgm(arrLine);
            if(strings==null){
                continue;
            }
            switch (j) {
                case 1:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("56:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 2:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("51:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 3:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("52:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 4:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("53:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 5:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("54:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 6:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("55:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 7:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("58:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                case 8:
                    for (int k = 0; k < strings.size(); k++) {
                        String s = strings.get(k);
                        StringBuffer buffer = new StringBuffer();
                        StringBuffer append = buffer.append(s.substring(0, 4)).append("59:").append(s.substring(4));
                        arranged.add(String.valueOf(append));
                    }
                    break;
                default:
                    break;
            }
        }
        return arranged;
    }

    //小节排列
    private static String arrangeLine(List<String[]> arrLine) {
        String string = "#";
        String[] strings = arrLine.get(0);
        string += format(Integer.valueOf(strings[0]));
        if (arrLine.size() == 1) {
            String[] value = new String[Integer.valueOf(strings[2])];
            for (int i = 0; i < value.length; i++) {
                value[i] = "00";
            }
            value[Integer.valueOf(strings[1])] = strings[3];
            for (int i = 0; i < value.length; i++) {
                string += value[i];
            }
        } else {
            //公倍数
            int result = 1;
            int flag=0;
            for (int i = 0; i < arrLine.size(); i++) {
                String[] strings1 = arrLine.get(i);
                flag=Integer.valueOf(strings1[2]);
                if(result < flag){
                    flag = result;
                    result = Integer.valueOf(strings1[2]);
                }
                if(isInteger(String.valueOf(result/result))){
                    continue;
                }else{
                    result = get_lcm(result, flag);
                }
            }
            String[] value = new String[result];
            for (int i = 0; i < value.length; i++) {
                value[i] = "00";
            }
            for (int i = 0; i < arrLine.size(); i++) {
                String[] strings1 = arrLine.get(i);
                int i1 = result / Integer.valueOf(strings1[2]);
                //System.out.println(result);
                //System.out.println(strings1[2]);
                //System.out.println(i1);
                //System.out.println(Integer.valueOf(strings1[1]));
                //System.out.println(value.length);
                if((Integer.valueOf(strings1[1]) * i1) == value.length){
                    value[(Integer.valueOf(strings1[1]) * i1)-1] = strings1[3];
                }else{
                    value[(Integer.valueOf(strings1[1]) * i1)] = strings1[3];
                }

            }
            for (int i = 0; i < value.length; i++) {
                string += value[i];
            }
        }
        //   System.out.println(string);
        return string;
    }


    //小数化分数
    private static String dicimalToFraction(double num) {
        int count = 0;
        int base = 10;
        while (num != Math.floor(num)) {
            num *= base;
            count++;
        }
        base = (int) Math.pow(base, count);
        int nor = (int) num;
        int gcd = findGCD(nor, base);
        return String.valueOf(nor / gcd) + "/" + String.valueOf(base / gcd);
    }

    //求最大公约数
    private static int findGCD(int a, int b) {
        if (a == 0) {
            return b;
        }
        return findGCD(b % a, a);
    }

    //最小公倍数
    private static int get_lcm(int n1, int n2) {
        return n1 * n2 / findGCD(n1, n2);
    }

    //三位数
    private static String format(int i) {
        NumberFormat n = NumberFormat.getInstance();
        n.setMinimumIntegerDigits(3);
        String k = n.format(i);
        return k;
    }

    //长键一分二
    public static List<String[]> longNoteBreak(List<String[]> lnList) {
        ArrayList<String[]> objects = new ArrayList<>();
        int size = lnList.size();
        for(int i=0;i<size;i++){
            String[] strings = lnList.get(i);
            objects.add(new String[]{"LongNote", strings[1], strings[2], strings[3]});
            objects.add(new String[]{"LongNote", strings[4], strings[2], strings[3]});
        }
        return objects;
    }

    //判断整数
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
