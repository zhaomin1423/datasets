package com.zy.rocketmq.hive;

import java.io.*;

public class Test {

    public static void main(String[] args) {
        FileOutputStream fileOutputStream = null;
        String outputDir = "D:\\ftpfile\\赵敏\\etl\\video\\";
        try {
            String line;
            int count = 0;

            File file = new File("D:\\ftpfile\\赵敏\\video\\unzip");
            String root = "D:\\ftpfile\\赵敏\\video\\unzip";
            int fileNums = 0;
            String[] filenames = file.list();
            for (String s : filenames){
                String outputFilename = outputDir + fileNums ++ + ".txt";
                fileOutputStream = new FileOutputStream(new File(outputFilename));
                for (int m = 0; m < 4; m++) {
                    String filename = root + "\\" + s + "\\" + s + "\\" + m + ".txt";
                    File txt = new File(filename);
                    System.out.println(txt.getName());

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(txt)));

                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                        String[] fields = line.split("\t");

                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < fields.length && i < 9; i++) {
                            stringBuilder.append(fields[i]).append("\t");
                        }

                        StringBuilder relatedIds = new StringBuilder();
                        if (fields.length > 9) {
                            for (int i = 9; i < fields.length - 1; i++) {
                                relatedIds.append(fields[i]).append(" & ");
                            }
                            relatedIds.append(fields[fields.length - 1]);
                            stringBuilder.append(relatedIds);
                        }
                        stringBuilder.append("\n");
                        count++;
                        String result = stringBuilder.toString().replace(" & ", "&");
                        System.out.println(result);
                        fileOutputStream.write(result.getBytes("UTF-8"));
                    }
                }

            }

            System.out.println("count:" + count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }

    }
}
