package zoo_project;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.google.gson.Gson;

public class router {
    static Map<String, Function<Map, Object>> map = new HashMap<>();

    public static void register(String request, Function<Map, Object> operator) {
        map.put(request, operator);
    }

    public static String dispatch(String request) {
        Mail mail = new Mail();
        mail.status = "501 Not Implemented";
        mail.body = "";
        try {
            String k = map.keySet().stream().filter(key -> request.startsWith(key, 0)).findAny().orElse(null);
            if (k != null) {
                Map parse = new Gson().fromJson(request.substring(k.length()), Map.class);
                Object response = map.get(k).apply(parse);
                if (response == null) {
                    mail.status = "400 Bad Request";
                } else {
                    mail.status = "200 OK";
                    mail.body = response;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return mail.toJson();
    }
}
