package com.musynx2bms.musynx;

import java.util.ArrayList;
import java.util.List;

/**
 * @author asdwadsxc
 * @create 2021-12-06 10:14
 */
public class ChartList {

    //定义头文件变量
    private static String bpm; //BPM
    private static String rank; //难度

    //获取BPM
    public static String getBpm() {
        return bpm;
    }

    //获取rank
    public static String getRank() {
        return rank;
    }

    //存储头文件数据
    public static void head(String[] split) {
        if ("BPM".equals(split[0])) {
            bpm = split[1];
        }
        if ("Rank".equals(split[0])) {
            rank = split[1].trim();
        }
    }

    //定义key sound列表
    public static List<String[]> wavList = new ArrayList<>();

    //存储key sound
    public static void sound(String[] split) {
        wavList.add(split);
    }

    //定义谱面内容列表
    public static List<String[]> beatList = new ArrayList<>();

    //储存谱面内容
    public static void beatLine(String[] split) {
        beatList.add(split);
    }

    //定义细分列表
    public static List<String[]> bpmList = new ArrayList<>();//速度
    public static List<String[]> bpmNoteList = new ArrayList<>();
    public static List<String[]> bgmList = new ArrayList<>();//背景播放
    public static List<String[]> noteList = new ArrayList<>();//点单
    public static List<String[]> lnList = new ArrayList<>();//长键
}
