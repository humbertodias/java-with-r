package com.r;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * R Utility class.
 */
public final class RUtil {

    private static final String R_HOME_EMPTY_ENV_VARIABLE = "R_HOME empty env variable";
    private static final String JAVA_LIBRARY_PATH_WITHOUT_JRI = "java.library.path does not contain -Djava.library.path=$R_HOME/site-library/rJava/jri";
    private static final String JAVA_VERSION_MISMATCH = "Java version mismatch.";

    /**
     * Singleton.
     */
    private static Rengine instance;

    private RUtil() {
    }

    public static Rengine getInstance() {
        if (instance == null) {
            instance = createREngine();
        }
        return instance;
    }

    public static void checkDependencies() {
        String rHome = System.getenv("R_HOME") + "";
        if (rHome.isEmpty()) {
            throw new RuntimeException(R_HOME_EMPTY_ENV_VARIABLE);
        }

        String envLDLibraryPath = System.getenv("LD_LIBRARY_PATH");
        String javaLibraryPath = System.getProperty("java.library.path");
        javaLibraryPath += File.pathSeparatorChar + envLDLibraryPath;
        if (javaLibraryPath.isEmpty() || !javaLibraryPath.contains("jri")) {
            throw new RuntimeException(JAVA_LIBRARY_PATH_WITHOUT_JRI);
        }

        if (!Rengine.versionCheck()) {
            throw new RuntimeException(JAVA_VERSION_MISMATCH);
        }
    }

    /**
     * Create instance of R Engine.
     * @return Instance
     */
    private static Rengine createREngine() {
        checkDependencies();

        String[] args = {"--vanilla"};
        Rengine re = new Rengine(args, false, null);
        if (!re.waitForR()) {
            throw new RuntimeException("Cannot load R");
        }

        return re;
    }

    public static REXP evaluate(final String command) {
        Rengine rEngine = getInstance();
        REXP result = rEngine.eval(command);
        rEngine.end();
        return result;
    }

    public static List<REXP> evaluate(final List<String> commands) {
        List<REXP> results = new ArrayList<>();
        commands.forEach(command -> results.add(evaluate(command)));
        return results;
    }

    public static List<REXP> evaluate(final InputStream inputStream) throws IOException {
        List<String> commands = toLines(inputStream);
        return evaluate(commands);
    }

    /**
     * InputStrem to List of Lines.
     * @param inputStream Input
     * @return List of lines
     * @throws IOException IO
     */
    private static List<String> toLines(final InputStream inputStream) throws IOException {
        List<String> lines = new ArrayList();
        try (BufferedReader reader =  new BufferedReader(new InputStreamReader(inputStream))) {
            reader.lines().forEach(line -> lines.add(line));
        }
        return lines;
    }

}
