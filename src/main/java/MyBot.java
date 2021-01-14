import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBot extends TelegramLongPollingBot {
    String [] commands = new String[] {"/start","/date","/time"};
    @Override
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if (update.getMessage().getText().equals("/start")) {

                message_text = "Приветствую тебя " + update.getMessage().getChat().getUserName() + "!" + "\n" + "Пока что я знаю только эти функции: \n";
                for(int i=0; i<commands.length;i++){
                    message_text+=commands[i]+"\n";
                }
            }
            if (update.getMessage().getText().equals("/date")) {
                String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                message_text = "Текущая дата: " + date;
            }
            if (update.getMessage().getText().equals("/time")) {
                String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
                message_text = "Текущее время: " + time;
            }
            System.out.println("GOT message:"+chat_id+" "+update.getMessage());
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        // Return bot username
        return "SuperPuperMegapuperBot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "1580071678:AAEpakMf_yxctUtNSHUCarpNk7Hv4vGOYro";
    }
}