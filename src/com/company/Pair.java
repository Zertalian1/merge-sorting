package com.company;

import java.io.BufferedReader;
import java.io.IOException;

public class Pair {
    private BufferedReader in;
    private String data;

    Pair(BufferedReader fr){
        this.in = fr;
    }
    public BufferedReader getIn() {
        return in;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public static boolean Comp(String a, String b, boolean isInteger, boolean isReverse){
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
    public  String readData(boolean isInteger) throws IOException {
        String data = in.readLine();
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
