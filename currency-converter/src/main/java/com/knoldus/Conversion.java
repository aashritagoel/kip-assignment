package com.knoldus;

public class Conversion {
    public static double convertToInr(String to, double amount) {

        double result=amount;

       if ("USD".equals(to))
           result = amount * 69;

       else if ("EUR".equals(to))
           result = amount * 81;

       else if ("SAR".equals(to))
           result = amount * 19;

       else if ("AED".equals(to))
           result = amount * 18;

        return result;
    }

    public static double convertFromInr(String from, double amount) {

        double result=amount;

        if ("USD".equals(from))
            result = amount / 69;

        else if ("EUR".equals(from))
            result = amount / 81;

        else if ("SAR".equals(from))
            result = amount / 19;

        else if ("AED".equals(from))
            result = amount / 18;

        return result;
    }

}
