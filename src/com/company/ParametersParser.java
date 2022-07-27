package com.company;

public class ParametersParser {
    private boolean isInteger = true;
    private int fileStart =0;
    private boolean isReverse = false;

    private String[] inputFilesNames;

    private String outputFileName;

    ParametersParser(){}

    ParametersParser(String[] n_parsed_str){
        parseParam(n_parsed_str);
    }


    public void parseParam(String[] input){
        while (true){
            switch (input[fileStart]){
                case "-s"-> isInteger=false;
                case "-d"-> isReverse=true;
                case "-a"-> isReverse=false;
                case "-i"-> isInteger=true;
                default-> {
                    getParams(input);
                    return;
                }
            }
            fileStart++;
        }
    }

    private void getParams(String[] input){
        outputFileName = input[fileStart];
        fileStart++;
        inputFilesNames = new String[input.length-fileStart];
        if (input.length - fileStart >= 0) {
            System.arraycopy(input, fileStart, inputFilesNames, 0, input.length - fileStart);
        }
    }

    public boolean isInteger() {
        return isInteger;
    }

    public boolean isReverse() {
        return isReverse;
    }

    public String[] getInputFilesNames() {
        return inputFilesNames;
    }

    public String getOutputFileName() {
        return outputFileName;
    }
}
