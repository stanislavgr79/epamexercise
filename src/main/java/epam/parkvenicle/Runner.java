package epam.parkvenicle;

public class Runner {

    public static void main(String[] args) {

        ParkVehicles parkVehicles = ParkVehicles.getInstance();
        DbInit.startUp();

        System.out.print("Стоимость всех машин:");
        System.out.println(parkVehicles.logicalMethods.resultOfCostAllCars(parkVehicles));

        System.out.println("\nСортировка машин по расходу топлива:");
        parkVehicles.logicalMethods.sortCarsByFuelSpend(parkVehicles)
                .forEach(System.out::println);

        System.out.println("\nПоиск машин старше введенной даты выпуска:");
        parkVehicles.logicalMethods.searchCarsByYearMadeUp(1,1,2014)
                .forEach(System.out::println);

        System.out.println("\nПоиск машин по указанной скорости в границах min и max:");
        parkVehicles.logicalMethods.searchCarsByRangeThemSpeed(120, 130)
                .forEach(System.out::println);
    }
}
