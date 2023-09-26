package schule.bbs2.j2023.efi3b.computerroomreservation.util;

import java.util.HashMap;
import java.util.Map;

public class UserHelper {

    public static Map<String, String> getSplitNamesFromUsername(String username) {
        String[] usernameSplit = username.split("\\.");
        if (usernameSplit.length != 2) {
            throw new IllegalArgumentException("Username kann nur aus zwei drei durch '.' getrennten Teilen bestehen!");
        }
        Map<String, String> names = new HashMap<>();
        names.put("firstName", usernameSplit[0]);
        names.put("lastName", usernameSplit.length == 3 ? usernameSplit[2] : usernameSplit[1]);
        return names;
    }
}
