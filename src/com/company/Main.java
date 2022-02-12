package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static boolean isInteger = true;
    private static boolean isReverse = false;




    public static void main(String[] args)/* throws IOException*/ {
        int fileStart=parseParam(args);
        String outFile = args[fileStart];
        String[] inputList = new String[args.length-++fileStart];
        for(int i=0; i< args.length-fileStart;i++){
            inputList[i]=args[i+fileStart];
        }
        List<BufferedReader> readerList = new ArrayList<>();
        List<String> dataList = new ArrayList<>();
        for(int i=0;i<inputList.length;i++){
            FileReader fr = null;
            try {
                fr = new FileReader(inputList[i]);
                readerList.add(new BufferedReader(fr));
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        for(int i= 0; i<readerList.size();i++){
            String temp = null;
            try {
                temp = readData(readerList.get(i));
                if(temp!=null)
                    dataList.add(temp);
                else{
                    readerList.get(i).close();
                    readerList.remove(i);
                    i--;
                }
            } catch (IOException e) {
            e.printStackTrace();
            }
        }



        try (BufferedWriter out = new BufferedWriter(new FileWriter(outFile));){
            mergeSort(readerList, dataList, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int parseParam(String[] input){
        int j=0;
        while (true){
            switch (input[j]){
                case "-s": isInteger=false; break;
                case "-d": isReverse=true; break;
                case "-a":
                case "-i": break;
                default: return j;
            }
            j++;
        }
    }

    private static int findMinMax(List<String> dataList){
        int place=0;
        for(int i=1;i<dataList.size();i++){
            if(isInteger){
                int a = Integer.parseInt(dataList.get(place));
                int b = Integer.parseInt(dataList.get(i));
                if(a> b){
                    if(!isReverse)
                        place = i;
                }else{
                    if(isReverse)
                        place = i;
                }
            }else{
                if(dataList.get(place).length()>dataList.get(i).length()) {
                    if(!isReverse)
                        place = i;
                }else{
                    if(isReverse)
                        place = i;
                }
            }
        }
        return place;
    }

    private static void mergeSort(List<BufferedReader> readerList, List<String> dataList, BufferedWriter out) throws IOException {
       while (readerList.size()>0) {
           int place = findMinMax(dataList);
           out.write(dataList.get(place) + "\n");
           try {
               String temp =readData(readerList.get(place));
               if(temp!=null)
                dataList.set(place, temp);
               else{
                   readerList.get(place).close();
                   readerList.remove(place);
                   dataList.remove(place);
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       out.close();
    }

    private static String readData(BufferedReader bufferedReader) throws IOException {
        String data = bufferedReader.readLine();
        if(!isInteger && data != null){
            for(int i=0;i<data.length();i++){
                if(data.charAt(i)==' ')
                    return null;
            }
        }
        return data;
    }
}
