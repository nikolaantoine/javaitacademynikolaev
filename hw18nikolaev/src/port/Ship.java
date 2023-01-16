package port;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Ship {
    private final int MAX_CONTAINERS = 100;
    private int countContainers;
    private final String shipName;
    private final Port port;
    private boolean flag = false;

    public Ship(String name, int countContainers, Port port) {
        this.shipName = name;
        if (countContainers < 0) {
            throw new IllegalArgumentException("Количество контейнеров не может быть отрицательным");
        }
        if (countContainers > MAX_CONTAINERS) {
            throw new IllegalArgumentException("Количество контейнеров не может превышать максимальную " +
                    "грузоподъемность корабля");
        }
        this.countContainers = countContainers;
        this.port = port;
    }

    public String getShipName() {
        return shipName;
    }

    public int getCountContainers() {
        return countContainers;
    }

    public int getMAX_CONTAINERS() {
        return MAX_CONTAINERS;
    }

    public void berth() {
        if (flag) {
            System.out.println(Thread.currentThread().getName() + " " + shipName + " уже причален");
            return;
        }
        System.out.println(Thread.currentThread().getName() + " " + shipName + " подплылывает к порту в "
                + LocalTime.now());
        port.berth(this);
        flag = true;
        System.out.println(Thread.currentThread().getName() + " " + shipName + " у причала в "
                + LocalTime.now());
    }

    public void sail() {
        if (!flag) {
            System.out.println(Thread.currentThread().getName() + " " + shipName + " уже отчалил из порта");
            return;
        }
        System.out.println(Thread.currentThread().getName() + " " + shipName + " отплылывает из порта в "
                + LocalTime.now());
        port.sail(this);
        flag = false;
        System.out.println(Thread.currentThread().getName() + " " + shipName + " в океане в " + LocalTime.now());

    }

    public void loading() {
        if (!flag) {
            System.out.println(Thread.currentThread().getName() + " " + shipName + " не причалил к порту");
            return;
        }
        System.out.println(Thread.currentThread().getName() + " " + shipName + " начал загрузку контейнеров в "
                + LocalTime.now());
        countContainers = port.loading(this);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(Thread.currentThread().getName() + " " + shipName + " закончил загрузку контейнеров в "
                + LocalTime.now());
    }


    public void unloading() {
        if (!flag) {
            System.out.println(Thread.currentThread().getName() + " " + shipName + " не причалил к порту");
            return;
        }
        System.out.println(Thread.currentThread().getName() + " " + shipName + " начал разгрузку в " + LocalTime.now());
        countContainers = port.unloading(this);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(Thread.currentThread().getName() + " " + shipName + " закончил разгрузку контейнеров в "
                + LocalTime.now());

    }


}
