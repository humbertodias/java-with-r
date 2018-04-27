package com.r;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class RUtil {

    private static Rengine instance;

    private RUtil() {
    }

    public static Rengine getInstance() {
        if (instance == null) {
            instance = createREngine();
        }
        return instance;
    }

    public static Rengine createREngine() {
        String rHome = System.getenv("R_HOME") + "";
        if (rHome.isEmpty()) {
            throw new RuntimeException("R_HOME empty env variable");
        }

        String javaLibraryPath = System.getProperty("java.library.path");
        if (javaLibraryPath.isEmpty() || !javaLibraryPath.contains("jri")) {
            throw new RuntimeException("java.library.path does not contain -Djava.library.path=$R_HOME/site-library/rJava/jri");
        }

        if (!Rengine.versionCheck()) {
            throw new RuntimeException("Java version mismatch.");
        }

        String[] args = {"--vanilla"};
        Rengine re = new Rengine(args, false, null);
        if (!re.waitForR()) {
            throw new RuntimeException("Cannot load R");
        }

        return re;
    }

    public static REXP evaluate(String command) {
        Rengine rEngine = getInstance();
        REXP result = rEngine.eval(command);
        rEngine.end();
        return result;
    }

    public static List<REXP> evaluate(List<String> commands) {
        List<REXP> results = new ArrayList<>();
        commands.forEach( command -> results.add( evaluate(command)) );
        return results;
    }

    public static List<REXP> evaluate(InputStream inputStream) throws IOException {
        List<String> commands = toLines(inputStream);
        return evaluate(commands);
    }

    public static List<String> toLines(InputStream inputStream) throws IOException {
        List lines = new ArrayList();
        try (BufferedReader reader =  new BufferedReader(new InputStreamReader(inputStream))) {
            reader.lines().forEach( line -> lines.add(line) );
        }
        return lines;
    }

}
