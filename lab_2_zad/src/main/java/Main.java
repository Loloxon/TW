import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Race race = new Race();
        StringBuilder result = new StringBuilder();
        result.append(race.start(10000, 5, new SemaforBin(true)));
        System.out.println(result);
        sleep(1000);
        result.append(race.start(10000, 5, new SemaforGeneral(1)));
        System.out.println(result);
        sleep(1000);
        result.append(race.start(10000, 5, new SemaforIF(true)));
        System.out.println(result);
    }
}