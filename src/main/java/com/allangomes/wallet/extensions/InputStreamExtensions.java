package com.allangomes.wallet.extensions;

import java.io.*;
import java.util.stream.Stream;

public class InputStreamExtensions {

    public static Stream<String> readLines(InputStream input) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        return bufferedReader.lines();
    }

}
