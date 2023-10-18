package api.config;

import java.util.Random;

public class Configuration {
    public static String user="laguna@lagu.com";
    public static String password="12345678";
    private static Random rand = new Random();
    public static String userRand ="laguna"+ Integer.toString(rand.nextInt(10000)) +"@lagu.com";

    public static String host="https://todo.ly/";
}
