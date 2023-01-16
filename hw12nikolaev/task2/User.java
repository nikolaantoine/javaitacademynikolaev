package hw12nikolaev.task2;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 34;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }


}
