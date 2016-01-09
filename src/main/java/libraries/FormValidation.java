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

    public void FormValidation() {

    }

    public static ArrayList<String> ValidateForm(LinkedHashMap<String, Object> mapObjCtrls) {
        ArrayList<String> arrError = new ArrayList<>();
        Set<String> keys = mapObjCtrls.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();

            if (mapObjCtrls.get(key) == null || mapObjCtrls.get(key).toString().isEmpty()) {
                arrError.add(String.format("The %s is required.", key));
            }
        }
        return arrError;
    }
}
