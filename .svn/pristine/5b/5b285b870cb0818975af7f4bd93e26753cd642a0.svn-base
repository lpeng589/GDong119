package com.pg.util;
import java.io.IOException;

import org.nutz.lang.Encoding;
import org.nutz.lang.Lang;


/**
 * Describe:针对silk文件进行转码工具类
 * Author:陆小不离
 * Age:Eighteen
 * Time:2017年5月27日 10:12:06
 */
public class DeCoder {

    public static void main(String[] args){

        String skil = "C:\\Users\\koron\\Downloads\\0E3C6CA72B991C69C249ED9BDDC36469.silk";
        String pcm = "C:\\Users\\koron\\Downloads\\0E3C6CA72B991C69C249ED9BDDC36469.pcm";
        String mp3 = "C:\\Users\\koron\\Downloads\\0E3C6CA72B991C69C249ED9BDDC36469.mp3";

        boolean b = getPcm(skil,pcm);
        System.out.println(b);
        if (b)
          getMp3(pcm,mp3);

    }

    /**
     * 解码为pcm格式
     * @param silk 源silk文件,需要绝对路径!! 例:F:\zhuanma\vg2ub41omgipvrmur1fnssd3tq.silk
     * @param pcm 目标pcm文件,需要绝对路径!! 例:F:\zhuanma\vg2ub41omgipvrmur1fnssd3tq.pcm
     * @return
     */
    public static boolean getPcm(String silk,String pcm){
        boolean flag = true;
        String cmd="cmd.exe /c silk-v3-decoder-master\\windows\\silk_v3_decoder.exe "+silk+" "+pcm+" -quiet";
        System.out.println("转码到pcm...");
        try
        {
            StringBuilder msg = Lang.execOutput(cmd, Encoding.CHARSET_GBK);
            System.out.println(msg);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 转码为MP3格式
     * @param pcm 源pcm文件,需要绝对路径!!  例:F:\zhuanma\vg2ub41omgipvrmur1fnssd3tq.pcm
     * @param mp3 目标mp3文件,需要绝对路径!! 例:F:\zhuanma\vg2ub41omgipvrmur1fnssd3tq.mp3
     * @return
     */
    public static boolean getMp3(String pcm,String mp3){
        boolean flag = true;
        System.out.println("转码到mp3...");
        try {
            StringBuilder sb = Lang.execOutput("cmd /c silk-v3-decoder-master\\windows\\ffmpeg\\ffmpeg.exe -y -f s16le -ar 24000 -ac 1 -i "+pcm+" "+mp3+"", Encoding.CHARSET_GBK);
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
