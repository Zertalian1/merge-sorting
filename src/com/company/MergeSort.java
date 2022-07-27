package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSort {
    ParametersParser parser;
    public MergeSort(ParametersParser parser){
        this.parser=parser;
    }

    public void start(){
        List<Stream> streams = new ArrayList<>();
        for (String inputFile : parser.getInputFilesNames()) {
            Stream stream = new Stream(inputFile, parser.isInteger());
            readElement(stream);
            streams.add(stream);
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(parser.getOutputFileName()))){
            mergeSort(streams, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void mergeSort(List<Stream> streams, BufferedWriter out) throws IOException {
        while (streams.size()>0) {
                Collections.sort(streams);      // не нравится
                if(parser.isReverse()) {
                    Collections.reverse(streams);
                }
                out.write(streams.get(0).getData() + "\n");
                if(readElement(streams.get(0))){
                    streams.remove(0);
                }
        }
        out.close();
    }

    private boolean readElement(Stream stream){
        try {
            String temp = stream.readData(parser.isInteger());
            if(temp == null){
                System.out.println("file " + stream.getFileName() + " is ended");
               return true;
            }
        } catch (IOException e) {
            System.err.println("Read data ERROR");
            return true;
        } catch (IncorrectDataException e) {
            System.err.println(e.getMassage());
            return true;
        }
        return false;
    }
    

}