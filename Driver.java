package com.company;

import java.util.Scanner;

// Input : I am a Robot!
// Output: I ma a Tobor!

// Input: My name is bot!?ABSD.
// Output: Ym eman si dsb!?ATOB.

public class Driver {
    public Boolean isCaps(char c) {
        return (c >= 'A' && c <= 'Z');
    }

    public Boolean isAlphabet(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public Character toCaps(char c) {
        return (char) (c - 32);
    }

    public Character toLower(char c) {
        return (char) (c + 32);
    }

    public StringBuilder reverse(StringBuilder word) {
        StringBuilder reverserWord = new StringBuilder();
        for (Integer j = word.length() - 1; j >= 0; j--)
            reverserWord.append(word.charAt(j));
        return reverserWord;
    }

    public String convert(String s) {
        StringBuilder sb = new StringBuilder(), word = new StringBuilder();

        for (Integer i = 0; i < s.length(); i++) {
            if (isAlphabet(s.charAt(i)) || s.charAt(i) == ' ') {
                if (s.charAt(i) == ' ') {
                    sb.append(reverse(word));
                    sb.append(' ');
                    word = new StringBuilder();
                } else {
                    word.append(s.charAt(i));
                }
            }
        }
        sb.append(reverse(word));

        String output = sb.toString();
        StringBuilder formattedString = new StringBuilder();
        Integer j = 0;

        for (Integer i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isAlphabet(s.charAt(i))) {
                c = sb.charAt(j);
                if (isCaps(s.charAt(i)) && !isCaps(sb.charAt(j)))
                    c = toCaps(sb.charAt(j));
                else if (!isCaps(s.charAt(i)) && isCaps(sb.charAt(j)))
                    c = toLower(sb.charAt(j));
                j += 1;
            } else if (s.charAt(i) == ' ') {
                c = sb.charAt(j);
                j += 1;
            }
            formattedString.append(c);
        }

        return  formattedString.toString();
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Driver d = new Driver();
        System.out.println("Input: " + input);
        System.out.println("Output: " + d.convert(input));
    }
}

