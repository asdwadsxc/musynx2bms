package com.bmx.musync;

import java.io.*;
import java.util.List;

/**
 * @author asdwadsxc
 * @create 2021-12-02 11:21
 */
public class MusyncWrite {

    public static void write(String string) throws IOException {
        File file = new File(string + "_BMS.bms");
        if(file.exists()){
            file.delete();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

        //写头
        List<String> headList = MusyncWriteChart.head();
        int sizeHead = headList.size();
        for (int i = 0; i < sizeHead; i++) {
            bufferedWriter.write(headList.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        //写key音
        List<String> wavList = MusyncWriteChart.wav();
        int sizeWav = wavList.size();
        for (int i = 0; i < sizeWav; i++) {
            bufferedWriter.write(wavList.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        //写背景音
        List<String> bgmList = MusyncWriteChart.bgm();
        int sizeBgm = bgmList.size();
        for (int i = 0; i < sizeBgm; i++) {
            String s = bgmList.get(i);
            StringBuffer buffer = new StringBuffer();
            StringBuffer append = buffer.append(s.substring(0, 4)).append("01:").append(s.substring(4));
            bufferedWriter.write(String.valueOf(append));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        //写note
        List<String> noteList = MusyncWriteChart.note();
            int sizeNote = noteList.size();
            for (int i = 0; i < sizeNote; i++) {
                String s = noteList.get(i);
                bufferedWriter.write(s);
                bufferedWriter.newLine();

        }
        bufferedWriter.flush();
        //写LN
        List<String> lnList = MusyncWriteChart.longNote();
            int sizeLn = lnList.size();
            for (int i = 0; i < sizeLn; i++) {
                String s = lnList.get(i);
                bufferedWriter.write(s);
                bufferedWriter.newLine();

        }

        bufferedWriter.flush();
        bufferedWriter.close();

        System.out.println("Successful");
    }
}
