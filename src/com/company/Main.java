package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static boolean isInteger = true;
    private static boolean isReverse = false;




    public static void main(String[] args) {
        int fileStart=parseParam(args);
        String outFile = args[fileStart];
        String[] inputList = new String[args.length-++fileStart];
        for(int i=0; i< args.length-fileStart;i++){
            inputList[i]=args[i+fileStart];
        }
        List<BufferedReader> readerList = new ArrayList<>();
        List<String> dataList = new ArrayList<>();
        for (String s : inputList) {
            FileReader fr;
            try {
                fr = new FileReader(s);
                readerList.add(new BufferedReader(fr));
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        for(int i= 0; i<readerList.size();i++){
            String temp;
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



        try (BufferedWriter out = new BufferedWriter(new FileWriter(outFile))){
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

    private static boolean Comp(String a, String b){
        if(isInteger){
            int n1 = Integer.parseInt(a);
            int n2 = Integer.parseInt(b);
            return !isReverse && n1 > n2 || isReverse && n2 > n1;
        }else{
            int l1=a.length();
            int l2=b.length();
            return l1 > l2 && !isReverse || l1 < l2 && isReverse;
        }
    }

    private static int findMinMax(List<String> dataList){
        int place=0;
        for(int i=1;i<dataList.size();i++){
            if(Comp(dataList.get(place),dataList.get(i)))
                place=i;
        }
        return place;
    }

    private static void mergeSort(List<BufferedReader> readerList, List<String> dataList, BufferedWriter out) throws IOException {
       while (readerList.size()>0) {
           int place = findMinMax(dataList);
           out.write(dataList.get(place) + "\n");
           try {
               String temp =readData(readerList.get(place));
               if(temp!=null && !Comp(dataList.get(place),temp))
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
        if(isInteger && data != null){
            try {
                Integer.parseInt(data);
            }catch (Exception e){
                //e.printStackTrace();
                System.out.println("invalid data type");
                return null;
            }
        }
        return data;
    }
}
