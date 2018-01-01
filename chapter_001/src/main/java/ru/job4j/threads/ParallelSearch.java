package ru.job4j.threads;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParallelSearch extends Thread {
    File file;
    String text;
    List<String> exts;
    static List<String> result = new ArrayList<>();

    ParallelSearch(String root, String text, List<String> exts) {
        this.file = new File(root);
        this.exts = exts;
        this.text = text;
    }

    @Override
    public void run() {
        if (!file.isDirectory() && this.license() && this.search()) {
            synchronized (result) {
                result.add(file.getAbsolutePath());
            }
        } else if (file.isDirectory()) {
            for (File item : file.listFiles()) {
                ParallelSearch threadFindFile = new ParallelSearch(item.getAbsolutePath(), text, exts);
                threadFindFile.start();
                try {
                    threadFindFile.join();
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    public synchronized boolean license() {
        char[] path = file.getAbsolutePath().toCharArray();
        List<Character> characters = new ArrayList<>();
        for (int i = path.length - 1; i >= 0; i--) {
            if (path[i] != '.') {
                characters.add(path[i]);
            } else {
                break;
            }
        }
        char[] chars = new char[characters.size()];
        for (int i = 0; i < characters.size(); i++) {
            chars[i] = characters.get(i).charValue();
        }
        StringBuffer stringBuilder = new StringBuffer(String.valueOf(chars));
        String license = new String(stringBuilder.reverse());
        for (int j = 0; j < exts.size(); j++) {
            if (exts.get(j).equals(license))
                return true;
        }
        return false;
    }

    public synchronized boolean search() {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            int i = -1;
            int k = 0;
            char[] textChars = text.toCharArray();
            while ((i = fileInputStream.read()) != -1) {
                if (textChars[k++] != (char) i) {
                    k = 0;
                } else if (k == textChars.length) {
                    return true;
                }
            }
        } catch (IOException ex) {
        }
        return false;
    }
}
