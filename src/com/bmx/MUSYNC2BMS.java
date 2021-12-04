package com.bmx;

import com.bmx.musync.MusyncRead;
import com.bmx.musync.MusyncWrite;

import java.io.File;
import java.io.IOException;

/**
 * @author asdwadsxc
 * @create 2021-12-02 11:34
 */
public class MUSYNC2BMS {
    public static void main(String[] args) throws IOException {
       // for (int j = 0; j < args.length; j++) {
            File file = new File(args[0]);
            String path = file.getPath();
            int i = path.lastIndexOf(".txt");
            path = path.substring(0, i);
            MusyncRead.read(file);
            MusyncWrite.write(path);
        //}
    }
}
