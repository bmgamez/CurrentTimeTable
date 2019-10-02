package de.bmgamez.test;

public class Test {

    public static void main(String[] args) {

        int i = 0;
        int[] array;
        String text = "hallo ich bin der manuel";
        String[] words;
        words = text.split(" ");
        array = new int[10];

        while (i < 10)
        {
            array[i] = i;
            i++;
        }
        for (int index : array)
        {
            System.out.println(index);
        }
        for (String word : words)
        {
            System.out.println(word);
        }
    }
}
