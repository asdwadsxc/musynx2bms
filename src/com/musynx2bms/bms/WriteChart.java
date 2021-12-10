package com.musynx2bms.bms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author asdwadsxc
 * @create 2021-12-06 10:24
 */
public class WriteChart {

    public static void write(String path) throws IOException {
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

        //写头
        List<String> headList = BmsArranged.head();
        int sizeHead = headList.size();
        for (int i = 0; i < sizeHead; i++) {
            bufferedWriter.write(headList.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();

        //写key音
        List<String> wavList = BmsArranged.wav();
        int sizeWav = wavList.size();
        for (int i = 0; i < sizeWav; i++) {
            bufferedWriter.write(wavList.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();

        //获取详细列表
        BmsArranged.restore();

        //写BPM列表
        List<String> bpmInfoList = BmsArranged.bpmInfo();
        int sizeBpmInfo = bpmInfoList.size();
        for (int i = 0; i < sizeBpmInfo; i++) {
            bufferedWriter.write(bpmInfoList.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();

        //写BPM
        List<String> bpmNoteList = BmsArranged.bpmNote();
        int sizeBpmNote = bpmNoteList.size();
        for (int i = 0; i < sizeBpmNote; i++) {
            bufferedWriter.write(bpmNoteList.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();

        //写bgm
        List<String> bgmNoteList = BmsArranged.bgmNote();
        int sizeBgmNote = bgmNoteList.size();
        for (int i = 0; i < sizeBgmNote; i++) {
            bufferedWriter.write(bgmNoteList.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();

        //写note
        List<String> tapNoteList = BmsArranged.tapNote();
        int sizeTapNote = tapNoteList.size();
        for (int i = 0; i < sizeTapNote; i++) {
            bufferedWriter.write(tapNoteList.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();

        //写longNote
        List<String> longNoteList = BmsArranged.longNote();
        int sizeLongNote = longNoteList.size();
        for (int i = 0; i < sizeLongNote; i++) {
            bufferedWriter.write(longNoteList.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();

    }
}
