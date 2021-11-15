package com.dominic.kata.bankaccount;

import com.dominic.kata.bankaccount.service.BankAccountService;

import java.util.Date;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        BankAccountService bankAccountService = new BankAccountService();

        while (true) {
            System.out.print("Enter an amount (positive if DEPOSIT, negative if WITHDRAWAL). Q to Quit: ");
            String line = in.nextLine();
            if (line.trim().equalsIgnoreCase("Q")) {
                System.out.println("Bye.");
                break;
            }

            double amount;
            try {
                amount = Double.parseDouble(line.trim());
            } catch (NumberFormatException exception) {
                System.out.println("Cannot parse your number. Please try again!");
                continue;
            }

            if (amount > 0) {
                bankAccountService.deposit(amount, new Date());
            } else {
                bankAccountService.withdrawal(-amount, new Date());
            }

            bankAccountService.printAccountHistory();
        }
    }
}
