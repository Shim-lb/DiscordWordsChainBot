import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class TestCBot {

    public static void main(String[] args) {
        // Insert your bot's token here
        String token = "ChatBot Token";

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        // Add a listener which answers with "Pong!" if someone writes "!ping"
        api.addMessageCreateListener(new MyListener());

        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }

}