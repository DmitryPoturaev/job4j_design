package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added;
        int changed = 0;
        int deleted = 0;
        Map<Integer, User> map = new HashMap<>();
        for (var user : current) {
            map.put(user.getId(), user);
        }
        for (var prevUser : previous) {
            User curUser = map.remove(prevUser.getId());
            if (curUser == null) {
                deleted++;
            } else if (!curUser.equals(prevUser)) {
                changed++;
            }
        }
        added = map.size();
        return new Info(added, changed, deleted);
    }

}
