package com.musynx2bms.musynx;

import java.io.*;

/**
 * @author asdwadsxc
 * @create 2021-12-06 10:10
 */
public class ReadChart {

    //解析musynx谱面文件
    public static void read(File path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String text;

        while (true) {
            text = bufferedReader.readLine();
            if (text == null)
                break;
            String[] split = text.split("\t");
            if ("BPM".equals(split[0]) || "Rank".equals(split[0])) {
                ChartList.head(split);
            } else if ("WAV".equals(split[0])) {
                ChartList.sound(split);
            } else if ("BPMChanger".equals(split[0])) {
                ChartList.beatLine(split);
            } else if ("MusicNote".equals(split[0])) {
                ChartList.beatLine(split);
            } else if ("Note".equals(split[0])) {
                ChartList.beatLine(split);
            } else if ("LongNote".equals(split[0])) {
                ChartList.beatLine(split);
            } else if ("BeatLine".equals(split[0])) {
                ChartList.beatLine(split);
            }
        }
    }
}
