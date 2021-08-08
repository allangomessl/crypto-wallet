package com.allangomes.wallet.extensions;

import java.io.*;
import java.util.stream.Stream;

public class FileExtensions {

    public static Stream<String> readLines(File file) throws FileNotFoundException {
        InputStream inputFS = new FileInputStream(file);
        return InputStreamExtensions.readLines(inputFS);
    }

}
