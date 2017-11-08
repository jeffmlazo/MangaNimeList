package com.manganimelist.libraries;

import java.util.ArrayList;
import java.util.ListIterator;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author jeprox
 */
public class MsgBox {

    /**
     * Create a message box that it is either error or success.
     *
     * @param arrMsg the array list of message(s)
     * @param status the status of the message either error or success
     */
    public static void showModal(ArrayList<String> arrMsg, String status) {
        // Create new formatted for each msg
        StringBuilder strBuildMsg = new StringBuilder();
        int msgCtr = arrMsg.size();
        ListIterator<String> iteratorMsg = arrMsg.listIterator();
        while (iteratorMsg.hasNext()) {
            String msg = iteratorMsg.next();
            strBuildMsg.append(msg);

            // Check if the msg > 1 if true add a newline
            if (msgCtr > 1) {

                // Check if the last message already if true break the loop to not append a newline
                if (iteratorMsg.nextIndex() == msgCtr) {
                    break;
                }

                strBuildMsg.append('\n');
            }
        }

        String alertHeader = "Please fix the error(s) below.";
        Alert alert = new Alert(AlertType.ERROR);
        if (status.equals("success")) {
            alertHeader = "Success";
            alert = new Alert(AlertType.INFORMATION);
        }

        /**
         * Set general alert properties
         *
         * @Note: Need to show the alert first before width and centering stage
         * take effect setting.
         */
        alert.setHeaderText(alertHeader);
        alert.setContentText(strBuildMsg.toString());
        alert.show();
        alert.setWidth(400);
        alert.getDialogPane().getScene().getWindow().centerOnScreen();
    }
}
