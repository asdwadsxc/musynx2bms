package com.bmx.musync;

import java.util.ArrayList;
import java.util.List;

/**
 * @author asdwadsxc
 * @create 2021-12-02 11:12
 */
public class MusyncReadChart {

    //头文件
    public static String bpm;
    static String rank;

    public static void head(String[] split) {

        if ("BPM".equals(split[0])) {
            bpm = split[1];
        }
        if ("Rank".equals(split[0])) {
            rank = split[1].trim();
        }
    }

    //key音
    static List<String[]> wavList = new ArrayList<>();

    public static void sound(String[] split) {
        wavList.add(split);
    }

    //bpm
    static List<String[]> bpmList = new ArrayList<>();

    public static void bpm(String[] split) {
        bpmList.add(split);
    }

    //背景音乐note
    static List<String[]> bgmList = new ArrayList<>();

    public static void music(String[] split) {
        bgmList.add(split);
    }

    //单键
    static List<String[]> noteList = new ArrayList<>();

    public static void note(String[] split) {
        noteList.add(split);
    }

    //长键
    static List<String[]> lnList = new ArrayList<>();

    public static void longNote(String[] split) {
        lnList.add(split);
    }

    //长键节点
    static List<String[]> lnSubList = new ArrayList<>();

    public static void longNoteSub(String[] split) {
        lnSubList.add(split);
    }

    //小节线
    static List<String[]> beatLineList = new ArrayList<>();

    public static void beatLine(String[] split) {
        beatLineList.add(split);
    }


}
