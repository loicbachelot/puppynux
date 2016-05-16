package config;

/**
 * Created by niamor972 on 15/03/16.
 * Parts of config.
 * >
 */

import log.LoggerUtility;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This singleton represents the configuration file. This is a JSON file.
 * For more information about keys, please see the wiki.
 */
public class Config {

    private final static Logger logger = LoggerUtility.getLogger(Config.class);
    private static final Config INSTANCE = new Config();
    private JSONObject data;

    /**
     *
     * @return The Config unique instance
     */
    public static Config getInstance() {
        return INSTANCE;
    }

    private Config() {
        logger.info("Config initialization");
    }

    /**
     *
     * @param configFileName The file name (or file path) of the configuration file you want to load
     * @throws ParseException If the file has an invalid format
     * @throws IOException
     */
    public void load(String configFileName) throws ParseException, IOException {
        load(new FileReader(configFileName));
    }

    /**
     *
     * @param configFile The File object representing the config file
     * @throws ParseException If the file has an invalid format
     * @throws IOException
     */
    public void load(File configFile) throws ParseException, IOException {
        load(new FileReader(configFile));
    }

    /**
     *
     * @param fileReader The FileReader object for reading the configuration file
     * @throws ParseException If the file has an invalid format
     * @throws IOException
     */
    public void load(FileReader fileReader) throws ParseException, IOException {
        JSONParser jsonParser = new JSONParser();
        data = (JSONObject) jsonParser.parse(fileReader);
        logger.info("[CONFIG] loaded");
    }

    private void load (InputStream stream) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        data = (JSONObject) jsonParser.parse(new InputStreamReader(stream));
        logger.info("[CONFIG] loaded");
    }

    public void load () throws IOException, ParseException {
        try {
            load(Config.class.getClassLoader().getResource("config/config.json").getFile());
        } catch (Exception e) {
            load(getClass().getClassLoader().getResourceAsStream("config/config.json"));
        }
    }

    public void save () throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("config/").getFile());
        file.mkdirs();

        FileWriter fileWriter = new FileWriter(file + "/" + "config.json");//"src/config/config.json");
        fileWriter.write(data.toJSONString());
        fileWriter.flush();
        fileWriter.close();
        logger.info("[CONFIG] saved");
    }

    /**
     *
     * @param key The name of the key
     * @param value The value to associate to the key
     */
    public void put(String key, Object value) {
        data.put(key, value);
    }

    /**
     *
     * @param key The name of the key
     * @return An object representing the value associated to key or null if the key doesn't exists
     */
    public Object get(String key) {
        return data.get(key);
    }

    /**
     *
     * @param key The name of the key
     * @return A string representing the value associated to key or an empty String if the key doesn't exists
     */
    public String getString(String key) {
        Object value = get(key);

        if(value != null) {
            return (String) value;
        }

        return "";
    }

    public int getInt(String key) {
        Object value = get(key);
        if (value != null) {
            return (int)(long) value;
        }

        return 0;
    }

    /**
     *
     * @param key The name of the key
     * @return A long representing the value associated to key or 0 if the key doesn't exists
     */
    public long getLong(String key) {
        Object value = get(key);

        if(value != null) {
            return (long) value;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Config{" +
                "data=" + data +
                '}';
    }
}