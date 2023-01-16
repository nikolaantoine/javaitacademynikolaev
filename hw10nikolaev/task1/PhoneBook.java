package hw10nikolaev.task1;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    public PhoneBook() {
    }

    Map<String, Integer> info = new HashMap<>();

    public void addPhone(String name, Integer number) {
        info.put(name, number);
    }

    public Integer getPhoneByName(String name) {
        return info.get(name);
    }
}

