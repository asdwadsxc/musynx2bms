package com.musynx2bms.musynx;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author asdwadsxc
 * @create 2021-12-06 11:04
 */
public class ArrangedList {


    //分裂长条后按时间顺序排序
    public static List<String[]> group(List<String[]> list) {
        List<String[]> sortList = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String[] strings = list.get(i);
            if (strings[0].equals("LongNote")) {
                List<String[]> longNoteSplit = longNoteSplit(strings);
                sortList.add(longNoteSplit.get(0));
                sortList.add(longNoteSplit.get(1));
            } else {
                sortList.add(strings);
            }
        }
        sortList.sort((Comparator) (o1, o2) -> {
            String[] s1 = (String[]) o1;
            String[] s2 = (String[]) o2;
            Integer i1 = Integer.valueOf(s1[1]);
            Integer i2 = Integer.valueOf(s2[1]);
            return Integer.compare(i1, i2);
        });
        return timeToBeat(sortList);
    }

    //分裂长键
    public static List<String[]> longNoteSplit(String[] lnNote) {
        ArrayList<String[]> lnSplitList = new ArrayList<>();
        lnSplitList.add(new String[]{"LongNote", lnNote[1], lnNote[2], lnNote[3]});
        lnSplitList.add(new String[]{"LongNote", lnNote[4], lnNote[2], lnNote[3]});
        return lnSplitList;
    }

    private static double speed;
    private static double time;
    private static double last;


    //时间转化为节拍
    public static List<String[]> timeToBeat(List<String[]> beatList) {

        speed = Double.parseDouble(ChartList.getBpm());
        time = 0.0;
        last = 0.0;

        List<String[]> arrayList = new ArrayList<>();

        for (int i = 0; i < beatList.size(); i++) {
            String[] beat = beatList.get(i);
            Double aDouble = Double.valueOf(beat[1]);//获取时间
            //System.out.print(beat[0] + "\t" + beat[1] + "\t");
            if (beat[0].equals("BPMChanger")) {

                String[] beat1 = beat(aDouble);
                speed = Double.parseDouble(beat[2]);
                arrayList.add(new String[]{beat[0], beat1[0], beat1[1], beat1[2], beat[2]});

            } else {
                String[] beat1 = beat(aDouble);
                //note类型，小节线，拍，拍，列，key音
                arrayList.add(new String[]{beat[0], beat1[0], beat1[1], beat1[2], beat[2], beat[3]});
                //System.out.println(beat1[0] + " " + beat1[1] + " " + beat1[2]);
            }
        }
        return arrayList;
    }

    //节拍计算
    private static String[] beat(Double aDouble) {
        double now = ((aDouble - time) * 0.0001 / ((60 * 1000) / speed)) / 4 + last;
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(5);
        String format = nf.format(now);
        String[] strings;
        if (!format.contains(".")) {
            strings = new String[]{format, "0"};
        } else {
            strings = format.split("\\.");
        }
        String s = decimalToFraction(strings[1]);
        String[] split = s.split("/");
        last = now;
        time = aDouble;

        //  System.out.println(beats);
        return new String[]{strings[0], split[0], split[1]};//小节， 拍
    }

    //小数化分数
    private static String decimalToFraction(String num) {

        Integer value = Integer.valueOf(num);
        int base;

        base = (int) Math.pow(10,num.length());


/*
        while (format != Math.floor(format)) {
            format *= base;
            count++;
        }
        return String.valueOf(nor / gcd) + "/" + String.valueOf(base / gcd);
 */
        int gcd = findGCD(value, base);
        return (value / gcd) + "/" + (base / gcd);
    }

    //求最大公约数
    private static int findGCD(int a, int b) {
        if (a == 0) {
            return b;
        }
        return findGCD(b % a, a);
    }

    //位数
    public static String setFormat(int i,int j) {
        NumberFormat n = NumberFormat.getInstance();
        n.setMinimumIntegerDigits(j);
        String k = n.format(i);
        return k;
    }

    //最小公倍数
    public static int get_lcm(int n1, int n2) {
        return n1 * n2 / findGCD(n1, n2);
    }

    //判断整数
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
