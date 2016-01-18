package main.java.libraries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 *
 * @author jeprox
 */
public class FormValidation {

    public FormValidation() {

    }

    public static ArrayList<String> ValidateForm(LinkedHashMap<String, Object> mapObjCtrls) {
        ArrayList<String> arrError = new ArrayList<>();
        Set<String> keys = mapObjCtrls.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();

            if (mapObjCtrls.get(key) == null || mapObjCtrls.get(key).toString().isEmpty()) {
                arrError.add(String.format("The %s is required.", key));
            } else if (mapObjCtrls.get(key).equals(false)) {
                arrError.add(String.format("The %s is required. Please choose at lease 1 %s.", key, key.toLowerCase()));
            }
        }
        return arrError;
    }
}
