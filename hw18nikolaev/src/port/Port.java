package port;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Port {
    private final Random r = new Random();
    private final AtomicInteger countContainers;
    private final int MAX_CONTAINERS = 300;
    private final Semaphore docks;
    private final List<String> namesShip = new ArrayList<>();

    public Port(int countContainers, int countDocks) throws IllegalArgumentException {
        if (countContainers < 0) {
            throw new IllegalArgumentException("Количество контейнеров не может быть меньше 0");
        }
        if (countContainers > MAX_CONTAINERS) {
            throw new IllegalArgumentException("Количество контейнеров не может превышать максимальную +" +
                    "вместительность порта");
        }
        this.countContainers = new AtomicInteger(countContainers);
        docks = new Semaphore(countDocks);
    }

    public int getCountContainers() {
        return countContainers.intValue();
    }

    public List<String> getNamesShip() {
        return namesShip;
    }

    public void berth(Ship ship) {
        try {
            docks.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        namesShip.add(ship.getShipName());
    }

    public void sail(Ship ship) {
        docks.release();
        namesShip.remove(ship.getShipName());
    }

    public int loading(Ship ship) {
        int containers = ship.getCountContainers();

        while (containers < ship.getMAX_CONTAINERS() && countContainers.intValue() != 0) {

            countContainers.decrementAndGet();
            containers++;
        }

        if (countContainers.intValue() == 0) {
            System.out.println("В порту закончились контейнеры ");
        }

        if (containers == ship.getMAX_CONTAINERS()) {
            System.out.println("Корабль " + ship.getShipName() + " загружен на максимум");
        } else {
            System.out.println("Корабль " + ship.getShipName() + " загружен на " + containers + " контейнеров");
        }

        return containers;
    }

    public int unloading(Ship ship) {
        int containers = ship.getCountContainers();

        while (containers > 0 && countContainers.intValue() != MAX_CONTAINERS) {

            countContainers.incrementAndGet();
            containers--;
        }

        if (containers == 0) {
            System.out.println("Корабль " + ship.getShipName() + " полность разгружен");
        }

        if (containers != 0 && countContainers.intValue() == MAX_CONTAINERS) {
            System.out.println("Порт заполнен на максимум");
        }

        return containers;
    }


}
