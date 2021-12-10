package com.musynx2bms;

import com.musynx2bms.bms.WriteChart;
import com.musynx2bms.musynx.ReadChart;

import java.io.File;
import java.io.IOException;

/**
 * @author asdwadsxc
 * @create 2021-12-06 10:08
 */
public class main {
    public static void main(String[] args) throws IOException {
        String text1 = "C:\\Users\\31062\\Desktop\\EasyBms.txt";
        String text2 = "C:\\Users\\31062\\Desktop\\HardBms.txt";
        String text3 = "C:\\Users\\31062\\Desktop\\brainPower6T_easy";
        String[] strs = {text1,text2,text3};

        for(int j=0;j<strs.length;j++){
            File file = new File(strs[j]);
            String path = file.getPath();
            int i = path.indexOf(".txt");
            if (i < 0) {
                path = path + "_bms.bms";
            } else {
                path = path.substring(0, i) + ".bms";
            }
            ReadChart.read(file);
            WriteChart.write(path);
            System.out.println("Successful");
        }
    }
}
