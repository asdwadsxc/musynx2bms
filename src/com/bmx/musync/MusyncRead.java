package com.bmx.musync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author asdwadsxc
 * @create 2021-12-02 9:07
 */
public class MusyncRead {
    static String text;
    static int index;

    public static void read(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (true) {
            text = bufferedReader.readLine();
            if (text == null)
                break;
            String[] split = text.split("\t");
            if ("BPM".equals(split[0]) || "Rank".equals(split[0])) {
                MusyncReadChart.head(split);
            } else if ("WAV".equals(split[0])) {
                MusyncReadChart.sound(split);
            } else if ("BPMChanger".equals(split[0])) {
                MusyncReadChart.bpm(split);
            } else if ("MusicNote".equals(split[0])) {
                MusyncReadChart.music(split);
            } else if ("Note".equals(split[0])) {
                MusyncReadChart.note(split);
            } else if ("LongNote".equals(split[0])) {
                MusyncReadChart.longNote(split);
            } else if ("LongNoteSub".equals(split[0])) {
                MusyncReadChart.longNoteSub(split);
            } else if ("BeatLine".equals(split[0])) {
                MusyncReadChart.beatLine(split);
            }
        }
        bufferedReader.close();
    }
}
