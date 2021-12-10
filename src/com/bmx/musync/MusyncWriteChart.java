package com.bmx.musync;

import com.bmx.util.Beat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author asdwadsxc
 * @create 2021-12-02 11:52
 */
public class MusyncWriteChart {

    //头文件
    public static List<String> head() {
        List<String> headList = new ArrayList<>();
        headList.add("#PLAYER 1");
        headList.add("#BPM " + MusyncReadChart.bpm);
        headList.add("#RANK " + MusyncReadChart.rank);
        return headList;
    }

    //key音
    public static List<String> wav() {
        List<String> wavList = new ArrayList<>();
        int size = MusyncReadChart.wavList.size();
        for (int i = 0; i < size; i++) {
            String[] strings = MusyncReadChart.wavList.get(i);
            wavList.add("#" + strings[0] + strings[1] + " " + strings[2] + ".wav");
        }
        return wavList;
    }

    //背景音乐note
    public static List<String> bgm() {
        List<String[]> bgmList = new ArrayList<>();
        int sizeBgm = MusyncReadChart.bgmList.size();
        for (int i = 0; i < sizeBgm; i++) {
            String[] bgm = MusyncReadChart.bgmList.get(i);
            Integer bgmTime = Integer.valueOf(bgm[1]);
            for (int j = 0; j < MusyncReadChart.beatLineList.size(); j++) {
                String[] beatLine = MusyncReadChart.beatLineList.get(j);
                Integer beatStart = Integer.valueOf(beatLine[1]);
                Integer beatEnd = Integer.valueOf(beatLine[4]);
                if ((bgmTime >= beatStart) && (bgmTime < beatEnd)) {
                    String beat = beatLine[2];
                    String time = String.valueOf(Integer.valueOf(bgmTime) - beatStart);
                    String[] beat1 = Beat.beat(time);
                    bgmList.add(new String[]{beat, beat1[0], beat1[1], bgm[3]});
                }
            }
        }
        return Beat.arrangeBgm(bgmList);
    }

    //单键
    public static List<String> note() {
        List<String[]> noteList = new ArrayList<>();
        int sizeNote = MusyncReadChart.noteList.size();
        for (int i = 0; i < sizeNote; i++) {
            String[] note = MusyncReadChart.noteList.get(i);
            getBeatLine(noteList, note);
        }
        return Beat.arrangePattern(noteList);
    }

    //长键
    public static List<String> longNote() {
        List<String[]> longNoteBreak = Beat.longNoteBreak(MusyncReadChart.lnList);
        List<String[]> longList = new ArrayList<>();
        int sizeLong = longNoteBreak.size();
        for(int i=0;i<sizeLong;i++){
            String[] longNote = longNoteBreak.get(i);
            getBeatLine(longList, longNote);
        }
        return Beat.arrangePatternLn(longList);
    }

    //获取节拍
    private static void getBeatLine(List<String[]> List, String[] Note) {
        Integer NoteTime = Integer.valueOf(Note[1]);
        for (int j = 0; j < MusyncReadChart.beatLineList.size(); j++) {
            String[] beatLine = MusyncReadChart.beatLineList.get(j);
            Integer beatStart = Integer.valueOf(beatLine[1]);
            Integer beatEnd = Integer.valueOf(beatLine[4]);
            if ((NoteTime >= beatStart) && (NoteTime < beatEnd)) {
                String beat = beatLine[2];
                String time = String.valueOf(Integer.valueOf(NoteTime) - beatStart);
                String[] beat1 = Beat.beat(time);
                List.add(new String[]{beat, beat1[0], beat1[1], Note[3], Note[2]});
            }
        }
    }
}
