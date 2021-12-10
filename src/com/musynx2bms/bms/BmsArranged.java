package com.musynx2bms.bms;

import com.musynx2bms.musynx.ArrangedList;
import com.musynx2bms.musynx.ChartList;

import java.util.ArrayList;
import java.util.List;

import static com.musynx2bms.musynx.ArrangedList.get_lcm;
import static com.musynx2bms.musynx.ArrangedList.isInteger;

/**
 * @author asdwadsxc
 * @create 2021-12-07 8:37
 */
public class BmsArranged {

    //头文件转换
    public static List<String> head() {
        List<String> headList = new ArrayList<>();
        headList.add("#GENERATOR: musynx2bms");
        headList.add("#PLAYER 1");
        headList.add("#BPM " + ChartList.getBpm());
        headList.add("#RANK " + ChartList.getRank());
        headList.add("#LNTYPE 1");
        return headList;
    }

    //key音转换
    public static List<String> wav() {
        List<String> wavList = new ArrayList<>();
        int size = ChartList.wavList.size();
        for (int i = 0; i < size; i++) {
            String[] strings = ChartList.wavList.get(i);
            wavList.add("#" + strings[0] + strings[1] + " " + strings[2] + ".wav");
        }
        ChartList.wavList.clear();
        return wavList;
    }

    //转存详细列表
    public static void restore() {
        List<String[]> group = ArrangedList.group(ChartList.beatList);
        for (int i = 0; i < group.size(); i++) {
            String[] strings = group.get(i);
            if ("BPMChanger".equals(strings[0])) {
                ChartList.bpmList.add(strings);
            } else if ("MusicNote".equals(strings[0])) {
                ChartList.bgmList.add(strings);
            } else if ("Note".equals(strings[0])) {
                ChartList.noteList.add(strings);
            } else if ("LongNote".equals(strings[0])) {
                ChartList.lnList.add(strings);
            }
        }
        ChartList.beatList.clear();
    }

    //BPM列表编号
    public static List<String> bpmInfo() {
        List<String> bpmInfoList = new ArrayList<>();
        int size = ChartList.bpmList.size();
        int index = 1;
        for (int i = 0; i < size; i++) {
            int flag = 1;
            String[] strings1 = ChartList.bpmList.get(i);
            for (int j = i; j > 0; j--) {
                String[] strings2 = ChartList.bpmNoteList.get(j - 1);
                if (strings1[4].equals(strings2[4])) {
                    ChartList.bpmNoteList.add(new String[]{strings1[0], strings1[1], strings1[2], strings1[3], strings1[4], strings2[5]});
                    flag = 0;
                    break;
                }
            }
            if (flag == 0) {
                continue;
            }
            String s = ArrangedList.setFormat(index, 2);
            ChartList.bpmNoteList.add(new String[]{strings1[0], strings1[1], strings1[2], strings1[3], strings1[4], s});
            bpmInfoList.add("#BPM" + s + " " + strings1[4]);
            index++;
        }
        ChartList.bpmList.clear();
        return bpmInfoList;
    }

    //BPM信息转换
    public static List<String> bpmNote() {
        List<String> bpmNoteList = new ArrayList<>();
        List<String[]> bpmNoteLineList = new ArrayList<>();
        int size = ChartList.bpmNoteList.size();
        if (ChartList.bpmNoteList.isEmpty()) {
            return bpmNoteList;
        }
        String[] strings = ChartList.bpmNoteList.get(0);
        bpmNoteLineList.add(strings);
        for (int i = 1; i < size; i++) {
            String[] strings1 = ChartList.bpmNoteList.get(i);
            String[] strings2 = ChartList.bpmNoteList.get(i - 1);
            if (strings1[1].equals(strings2[1])) {
                bpmNoteLineList.add(strings1);
            } else {
                String s = BmsArranged.arrangeNote(bpmNoteLineList);
                StringBuffer buffer = new StringBuffer();
                StringBuffer append = buffer.append(s, 0, 4).append("08:").append(s.substring(4));
                bpmNoteList.add(String.valueOf(append));
                bpmNoteLineList.clear();
                bpmNoteLineList.add(strings1);
            }
        }
        if (bpmNoteLineList.size() != 0) {
            String s = BmsArranged.arrangeNote(bpmNoteLineList);
            StringBuffer buffer = new StringBuffer();
            StringBuffer append = buffer.append(s, 0, 4).append("08:").append(s.substring(4));
            bpmNoteList.add(String.valueOf(append));
        }
        ChartList.bpmNoteList.clear();
        return bpmNoteList;
    }

    //bgm信息转换
    public static List<String> bgmNote() {
        List<String> bgmNoteList = new ArrayList<>();
        List<String[]> bgmNoteListRearrange = bgmRearrange(ChartList.bgmList);
        List<String[]> bgmNoteLineList = new ArrayList<>();
        int size = bgmNoteListRearrange.size();
//        for(int i =0;i<size;i++){
//            String[] strings = bgmNoteListRearrange.get(i);
//            for(int j=0;j<strings.length;j++){
//                System.out.print(strings[j]+"\t");
//            }
//            System.out.println();
//        }
        String[] strings = bgmNoteListRearrange.get(0);
        bgmNoteLineList.add(strings);
        for (int i = 1; i < size; i++) {
            String[] strings1 = bgmNoteListRearrange.get(i);
            String[] strings2 = bgmNoteListRearrange.get(i - 1);
            if (strings1[1].equals(strings2[1])) {
                bgmNoteLineList.add(strings1);
            } else {
                String s = BmsArranged.arrangeNote(bgmNoteLineList);
                StringBuffer buffer = new StringBuffer();
                StringBuffer append = buffer.append(s, 0, 4).append("01:").append(s.substring(4));
                bgmNoteList.add(String.valueOf(append));
                bgmNoteLineList.clear();
                bgmNoteLineList.add(strings1);
            }
        }
        if (bgmNoteLineList.size() != 0) {
            String s = BmsArranged.arrangeNote(bgmNoteLineList);
            StringBuffer buffer = new StringBuffer();
            StringBuffer append = buffer.append(s, 0, 4).append("01:").append(s.substring(4));
            bgmNoteList.add(String.valueOf(append));
            bgmNoteLineList.clear();
        }
        ChartList.bgmList.clear();
        return bgmNoteList;
    }

    //tapNote信息转换
    public static List<String> tapNote() {
        List<String> tapNoteList = new ArrayList<>();
        List<String[]> tapNoteLineList = new ArrayList<>();
        List<String[]> noteList = ChartList.noteList;
        String[] info = {"16:", "11:", "12:", "13:", "14:", "15:", "18:", "19:"};
        for (int j = 1; j <= 8; j++) {
            List<String[]> tapNoteRow = new ArrayList<>();
            for (int i = 0; i < noteList.size(); i++) {
                String[] strings = noteList.get(i);
                if (Integer.valueOf(strings[4]) == j) {
                    tapNoteRow.add(strings);
                }
            }
            if(tapNoteRow.size()==0){
                continue;
            }
            String[] strings = tapNoteRow.get(0);
            tapNoteLineList.add(strings);
            for (int i = 1; i < tapNoteRow.size(); i++) {
                String[] strings1 = tapNoteRow.get(i);
                String[] strings2 = tapNoteRow.get(i - 1);
                if (strings1[1].equals(strings2[1])) {
                    tapNoteLineList.add(strings1);
                } else {
                    String s = BmsArranged.arrangeNote(tapNoteLineList);
                    StringBuffer buffer = new StringBuffer();
                    StringBuffer append = buffer.append(s, 0, 4).append(info[j - 1]).append(s.substring(4));
                    tapNoteList.add(String.valueOf(append));
                    tapNoteLineList.clear();
                    tapNoteLineList.add(strings1);
                }
            }
            if (tapNoteLineList.size() != 0) {
                String s = BmsArranged.arrangeNote(tapNoteLineList);
                StringBuffer buffer = new StringBuffer();
                StringBuffer append = buffer.append(s, 0, 4).append(info[j - 1]).append(s.substring(4));
                tapNoteList.add(String.valueOf(append));
                tapNoteLineList.clear();
            }
        }
        ChartList.noteList.clear();
        return tapNoteList;
    }

    //longNote信息转换
    public static List<String> longNote() {
        List<String> longNoteList = new ArrayList<>();
        List<String[]> longNoteLineList = new ArrayList<>();
        List<String[]> noteList = ChartList.lnList;
        String[] info = {"56:", "51:", "52:", "53:", "54:", "55:", "58:", "59:"};
        for (int j = 1; j <= 8; j++) {
            List<String[]> longNoteRow = new ArrayList<>();
            for (int i = 0; i < noteList.size(); i++) {
                String[] strings = noteList.get(i);
                if (Integer.valueOf(strings[4]) == j) {
                    longNoteRow.add(strings);
                }
            }
            if(longNoteRow.size()==0){
                continue;
            }
            String[] strings = longNoteRow.get(0);
            longNoteLineList.add(strings);
            for (int i = 1; i < longNoteRow.size(); i++) {
                String[] strings1 = longNoteRow.get(i);
                String[] strings2 = longNoteRow.get(i - 1);
                if (strings1[1].equals(strings2[1])) {
                    longNoteLineList.add(strings1);
                } else {
                    String s = BmsArranged.arrangeNote(longNoteLineList);
                    StringBuffer buffer = new StringBuffer();
                    StringBuffer append = buffer.append(s, 0, 4).append(info[j - 1]).append(s.substring(4));
                    longNoteList.add(String.valueOf(append));
                    longNoteLineList.clear();
                    longNoteLineList.add(strings1);
                }
            }
            if (longNoteLineList.size() != 0) {
                String s = BmsArranged.arrangeNote(longNoteLineList);
                StringBuffer buffer = new StringBuffer();
                StringBuffer append = buffer.append(s, 0, 4).append(info[j - 1]).append(s.substring(4));
                longNoteList.add(String.valueOf(append));
                longNoteLineList.clear();
            }
        }
        ChartList.lnList.clear();
        return longNoteList;
    }

    //note排列
    public static String arrangeNote(List<String[]> noteList) {
        String string = "#";
        String[] strings = noteList.get(0);
        string += ArrangedList.setFormat(Integer.parseInt(strings[1]), 3);

        int result = 1;//公倍数
        int flag = 0;
        for (int i = 0; i < noteList.size(); i++) {
            String[] strings1 = noteList.get(i);
            flag = Integer.valueOf(strings1[3]);
            if (result < flag) {
                flag = result;
                result = Integer.valueOf(strings1[3]);
            }
            if (isInteger(String.valueOf(result / flag))) {
                continue;
            } else {
                result = get_lcm(result, flag);
            }
        }

        String[] value = new String[result];
        for (int i = 0; i < value.length; i++) {
            value[i] = "00";
        }
        for (int i = 0; i < noteList.size(); i++) {
            String[] strings1 = noteList.get(i);
            int i1 = result / Integer.valueOf(strings1[3]);
            value[(Integer.valueOf(strings1[2]) * i1)] = strings1[5];
        }
        for (int i = 0; i < value.length; i++) {
            string += value[i];
        }
        return string;
    }

    //bgmNote重复排列
    private static List<String[]> bgmRearrange(List<String[]> list) {
        List<String[]> list1 = new ArrayList<>();
        List<String[]> listRe = new ArrayList<>();
        list.size();
        if (list.isEmpty()) {
            return null;
        }
        list1.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            String[] strings = list.get(i);
            String[] strings1 = list.get(i - 1);
            if (strings[1].equals(strings1[1]) && strings[2].equals(strings1[2]) && strings[3].equals(strings1[3])) {
                listRe.add(list.get(i));
            } else {
                list1.add(list.get(i));
            }
        }
        List<String[]> rearrange = bgmRearrange(listRe);
        if (rearrange != null) {
            for (int i = 0; i < rearrange.size(); i++) {
                list1.add(rearrange.get(i));
            }
        }
        return list1;
    }

}
