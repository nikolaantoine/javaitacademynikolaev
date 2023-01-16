package port;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {

        try {
            Port port = new Port(200, 2);

            Ship ship1 = new Ship("A", 30, port);
            Ship ship2 = new Ship("B", 90, port);
            Ship ship3 = new Ship("C", 0, port);

            var t1 = CompletableFuture.runAsync(ship1::berth)
                    .thenRun(ship1::unloading)
                    .thenRun(ship1::loading)
                    .thenRun(ship1::sail);
            var t2 = CompletableFuture.runAsync(ship2::berth)
                    .thenRun(ship2::loading)
                    .thenRun(ship2::sail);
            var t3 = CompletableFuture.runAsync(ship3::berth)
                    .thenRun(ship3::loading)
                    .thenRun(ship3::sail);

            t1.get();
            t2.get();
            t3.get();

            System.out.println("port " + port.getCountContainers());
            System.out.println(ship1.getShipName() + " " + ship1.getCountContainers());
            System.out.println(ship2.getShipName() + " " + ship2.getCountContainers());
            System.out.println(ship3.getShipName() + " " + ship3.getCountContainers());

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (ExecutionException e) {
            System.out.println(e.getCause().getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
