package com.knoldus;
import java.util.Scanner;
import java.text.DecimalFormat;

public class CurrencyApp {
    public static void main(String[] args) {

        String inputCurrency, outputCurrency;
        double amount, convertedAmount, inrConverted;
        Scanner sc=new Scanner(System.in);

        System.out.println("Currency Converter(INR, USD, AED, SAR, EUR)");

        // Input from user
        System.out.println("TO: ");
        outputCurrency=sc.next();
        outputCurrency = outputCurrency.toUpperCase();

        System.out.println("FROM: ");
        inputCurrency=sc.next();
        inputCurrency = inputCurrency.toUpperCase();

        System.out.println("AMOUNT: ");
        amount = sc.nextDouble();

        String indianCurrency="INR";
        if(!inputCurrency.equals(indianCurrency)) {
            inrConverted = Conversion.convertToInr(inputCurrency, amount);
        }
        else
            inrConverted = amount;

        convertedAmount = Conversion.convertFromInr(outputCurrency, inrConverted);

        convertedAmount = Math.round(convertedAmount*100.0)/100.0;

        System.out.println("Converted Amount: " + convertedAmount + " " + outputCurrency);

    }
}
