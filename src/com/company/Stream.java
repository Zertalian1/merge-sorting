package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Stream implements Comparable{
    private String fileName;
    private String data;
    private String prevData;
    private long offset;

    private boolean isInteger = true;

    public String getPrevData() {
        return prevData;
    }

    public String getData() {
        return data;
    }

    public Stream(String name, boolean isInteger){
        this.isInteger = isInteger;
        fileName = name;
    }

    public Stream(){
    }

    public String readData(boolean isInteger) throws IOException, IncorrectDataException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        skip(in,offset);
        prevData = data;
        data = in.readLine();
        in.close();
        if(data!= null && data.contains(" ")){
            throw  new IncorrectDataException("File " + fileName + " contains space");
        }
        if(data!= null && isInteger){
            try {
                Integer.parseInt(data);
            }catch (Exception e){
                throw new IncorrectDataException("Waited Integer in file " + fileName);
            }
        }
        offset++;
        return data;
    }

    private void skip(BufferedReader in, long offset) throws IOException {
        for(int i=0;i<offset;i++){
            in.readLine();
        }
    }

    @Override
    public int compareTo(Object input) {
        Stream stream = (Stream) input;
        if(!isInteger) {
            return data.compareTo(stream.getData());
        }else{
            int value1 = Integer.parseInt(data);
            int value2 = Integer.parseInt(stream.getData());
            return Integer.compare(value1, value2);
        }
    }

    /**
     * сравнивает старое значение data с новым
     * меньше 0, если новое меньше старого
     * больше 0, если новое больше старого
     * 0, иначе
     * @param parser
     * @return
     */
    public int compareData(ParametersParser parser){
        if(parser.isInteger()){
            return data.compareTo(prevData);
        }else{
            int value1 = Integer.parseInt(data);
            int value2 = Integer.parseInt(prevData);
            return Integer.compare(value1, value2);
        }
    }

    public String getFileName() {
        return fileName;
    }
}
