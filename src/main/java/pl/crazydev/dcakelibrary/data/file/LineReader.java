package pl.crazydev.dcakelibrary.data.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineReader {
    private final File file;
    public LineReader(File file) {
        this.file = file;
    }

    public List<String> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<>();

        String line = reader.readLine();

        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }

        reader.close();

        return lines;
    }
}
