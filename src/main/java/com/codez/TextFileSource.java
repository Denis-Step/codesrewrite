package com.codez;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TextFileSource {

    private List<String> words;

    public TextFileSource (String path) {
        Charset charset = Charset.forName("US-ASCII");
        words = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            line = reader.readLine();

            while (line != null){
                if (line != ""){
                    words.add(line.trim());
                }
                line = reader.readLine();
            }
        }
        catch (IOException exception) {
            System.err.println(exception);
        }

    }

    public String[] getWords(){
        return words.toArray(new String[1000]);
    }
}
