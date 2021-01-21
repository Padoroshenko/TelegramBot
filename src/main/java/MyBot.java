import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            if (update.getMessage().getText().equals("/buttons"))
            {
                try {
                    execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("GOT message:" + chat_id + " " + update.getMessage());
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

    public static SendMessage sendInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Тык");
        inlineKeyboardButton1.setCallbackData("Button \"Тык\" has been pressed");
        inlineKeyboardButton2.setText("Тык2");
        inlineKeyboardButton2.setCallbackData("Button \"Тык2\" has been pressed");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Fi4a").setCallbackData("CallFi4a"));
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Пример").setReplyMarkup(inlineKeyboardMarkup);
    }
}