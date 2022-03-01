package com.company;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
	    System.out.println("Тестирование и отладка программного обеспечения.3 ПР.");
	    System.out.println("Тимошкин Максим. ИКБО-07-18. 20 вариант.");
	    System.out.println("Приложение для создания миксов мелодий из нескольких файлов");
        System.out.println("Введите в одну строку через пробел названия всех файлов(с расширением)");
        System.out.println("в той последовательности, в которой они должны быть объеденены.");
        System.out.println("Поддерживаемый формат файлов - .wav");
        Scanner in = new Scanner(System.in);
        String[] names = in.nextLine().split(" ");;
        List<AudioFile> list;
        while (true) {
            list = new ArrayList<>();
            try {
                for (String elem : names) {
                    list.add(new AudioFile(elem));
                }
                break;
            }catch (FileNotFoundException e){
                System.out.println(e.getMessage());
                names = in.nextLine().split(" ");
            }catch (UnsupportedAudioFileException e){
                System.out.println(e.getMessage());
                names = in.nextLine().split(" ");
            }
        }
        for (AudioFile af: list){
            System.out.println("Название: "+af.getFilename());
            System.out.println("Длина: "+af.getLength());
            System.out.println("Введите начало и конец фрагмента: ");
            while(true) {
                try {
                    int start,end;
                    start = in.nextInt();
                    end = in.nextInt();
                    af.crop(start, end);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        AudioFile.concat(list.toArray(new AudioFile[0]));
    }
}
