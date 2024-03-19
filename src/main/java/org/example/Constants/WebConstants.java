package org.example.Constants;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;
import java.util.StringTokenizer;

public class WebConstants {
    static final public String prefix = "/api";
    static final public String cookie = "au";
//    public static String Prefix() {
//        try {
//            return prefix.clone();
//        }
//        catch (CloneNotSupportedException excp) {
//
//        }
//    }
    static public String getUriId(String url) {
        StringTokenizer tokenizer = new StringTokenizer(url, "/");
        String id = "";
        for(int i = tokenizer.countTokens(); i > 0; i--) {
            id = tokenizer.nextToken();
        }
        return id;
    }
    static public int getUserIdFromCookies(Cookie[] cookies) {
        Optional<Cookie> cookie = Arrays.stream(cookies).filter(x -> x.getName().equals(WebConstants.cookie))
                .findAny();
        return Integer.parseInt(cookie.get().getValue());
    }
}
