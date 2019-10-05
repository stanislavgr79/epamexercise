package epam.parkvenicle;

public class Runner {

    public static void main(String[] args) {

        ParkVenicles parkVenicles = ParkVenicles.getInstance();
        DbInit.startUp();

        System.out.print("Стоимость всех машин:");
        System.out.println(parkVenicles.logicalMethods.resultOfCostAllCars(parkVenicles));

        System.out.println("\nСортировка машин по расходу топлива:");
        parkVenicles.logicalMethods.sortCarsByFuelSpend(parkVenicles)
                .forEach(System.out::println);

        System.out.println("\nПоиск машин старше введенной даты выпуска:");
        parkVenicles.logicalMethods.searchCarsByYearMadeUp(1,1,2014)
                .forEach(System.out::println);

        System.out.println("\nПоиск машин по указанной скорости в границах min и max:");
        parkVenicles.logicalMethods.searchCarsByRangeThemSpeed(120, 130)
                .forEach(System.out::println);
    }
}
