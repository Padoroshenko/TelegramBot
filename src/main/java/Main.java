import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
public class Main {
    public static void main(String[] args) {

        /*
        Sedan sedan = new Sedan();//
        Sedan sedan2 = new Sedan("red",350,"Kia");

        sedan.setColor("green");
        sedan.setMaxSpeed(300);
        sedan.setModel("Jeep");

        System.out.println(sedan.getColor());
        System.out.println(sedan.getMaxSpeed());
        System.out.println(sedan.getModel());

        sedan.gas();

        System.out.println();

        System.out.println(sedan2.getColor());
        System.out.println(sedan2.getMaxSpeed());
        System.out.println(sedan2.getModel());

        sedan2.brake();*/

        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new MyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}