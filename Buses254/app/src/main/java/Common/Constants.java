package Common;

import org.json.JSONArray;

/**
 * Created by USER on 12/5/2017.
 */

public class Constants {
    public static Constants constants=null;

    public static Constants shared(){
        if (constants==null){
            constants = new Constants();
        }
        return constants;
    }

    public static JSONArray endpoint;

    public static JSONArray getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(JSONArray endpoint) {
        Constants.endpoint = endpoint;
    }

}
