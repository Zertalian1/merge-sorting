package com.company;

public class Main {
    private static boolean isInteger = true;
    private static boolean isReverse = false;


    public static void main(String[] args) {
        int fileStart=parseParam(args);
        String[] inputList = new String[args.length-++fileStart];
        if (args.length - fileStart >= 0) System.arraycopy(args, fileStart, inputList, 0, args.length - fileStart);

        MergeSort sort = new MergeSort(isInteger, isReverse);
        sort.sort(args[fileStart-1],inputList);
    }

    private static int parseParam(String[] input){
        int j=0;
        while (true){
            switch (input[j]){
                case "-s"-> isInteger=false;
                case "-d"-> isReverse=true;
                case "-a"-> isReverse=false;
                case "-i"-> isInteger=true;
                default-> {return j;}
            }
            j++;
        }
    }

}
