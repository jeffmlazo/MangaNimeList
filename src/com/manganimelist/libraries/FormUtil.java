package com.manganimelist.libraries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;

/**
 *
 * @author jeprox
 */
public class FormUtil {

    public FormUtil() {

    }

    /**
     * TODO: Need to improve this validation character, etc... Validate form if
     * field values are pass.
     *
     * @param mapObjCtrls the key value pair of Field Name and its value control
     * @return validation error(s) or null
     */
    public static ArrayList<String> validateForm(LinkedHashMap<String, Object> mapObjCtrls) {
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

    /**
     * Clear the form fields to an empty values.
     *
     * @param fieldCtrls the field control types such as Textfield, ComboBox,
     * CheckBox etc..
     */
    public static void clearForm(ArrayList<Control> fieldCtrls) {
        
    }
}
