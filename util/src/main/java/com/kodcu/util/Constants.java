package com.kodcu.util;

/*
 * Created by hakdogan on 27.07.2018
 */

public class Constants {
    public static final int DEFAULT_HTTP_PORT = 8080;
    public static final int DEFAULT_WORKER_POOL_SIZE = 100;
    public static final int HTTP_STATUS_CODE_OK = 200;
    public static final int HTTP_TOO_MANY_REQUESTS = 429;
    public static final int INTERNEL_SERVER_ERROR_CODE = 500;
    public static final int HTTP_SERVICE_UNAVAILABLE = 503;
    public static final String DEFAULT_HOSTNAME = "localhost";
    public static final String PATH_PARAM_TO_RECEIVE_MESSAGE = "message";
    public static final String PATH_PARAM_TO_SAVE_WORD = "word";
    public static final String ADDRESS = "kodcu.com";
    public static final String DEFAULT_REPLY_MESSAGE = "message received";
    public static final String FILE_NAME = "src/main/resources/word.txt";
    public static final String DEFAULT_LOCAL_MAP_NAME = "myLocalMap";
    public static final String DEFAULT_ASYNC_MAP_NAME = "myAsyncMap";
    public static final String DEFAULT_ASYNC_MAP_KEY = "actualData";
    public static final String DEFAULT_TEMPLATES_DIRECTORY = "templates";
    public static final String CONTENT_TYPE = "content-type";
    public static final String JSON_PRODUCE = "application/json; charset=utf-8";
    public static final String HTML_PRODUCE = "text/html; charset=utf-8";
    public static final String KODCU_STOCK_NAME = "kodcu.com";
    public static final String JUG_IST_STOCK_NAME = "JUG Istanbul";
    public static final String SOAPUI_PROJECT_FILE = "../soapui-project.xml";

    private Constants() {}
}
