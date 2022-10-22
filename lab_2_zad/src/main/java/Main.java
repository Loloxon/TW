import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Race race = new Race();
        StringBuilder result = new StringBuilder();
        result.append(race.start(10000, new SemaforBin()));
        sleep(1000);
        result.append(race.start(10000, new SemaforIF()));
        sleep(1000);
        result.append(race.start(10000, new SemaforGeneral(1)));
        System.out.println(result);
    }
}