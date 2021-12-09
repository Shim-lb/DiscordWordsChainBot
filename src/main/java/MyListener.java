import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.HashMap;

public class MyListener implements MessageCreateListener {
    private HashMap<TextChannel, Game> games = new HashMap<>();
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();
        if (message.getContent().equalsIgnoreCase("!ping")) {
            event.getChannel().sendMessage("Pong!");
        }

        if(message.getContent().startsWith("!게임시작")){
            int size= Integer.parseInt(message.getContent().substring(6));
            games.put(event.getChannel(), new Game(size));
            event.getChannel().sendMessage("참가하실 분들은 '!참가'를 전송해주시기 바랍니다.");
        }
        String nickname = event.getMessageAuthor().getDisplayName();
        if(message.getContent().equals("!참가")){
            if(games.containsKey(event.getChannel())){
                System.out.println(games.get(event.getChannel()));
                if(games.get(event.getChannel()).isPlayer(nickname)){
                    event.getChannel().sendMessage("이미 참가 하셨습니다.");
                }else{
                    games.get(event.getChannel()).addPlayer(nickname);
                }
            }

        }
        if(message.getContent().startsWith("!발화")){
            String word = message.getContent().substring(4);
            String result = games.get(event.getChannel()).play(nickname, word);
            event.getChannel().sendMessage(result);
            EmbedBuilder embed = new EmbedBuilder().setThumbnail(event.getMessageAuthor().getAvatar());
            embed.setTitle(nickname);
            embed.addField("발화 단어", word);
            event.getChannel().sendMessage(embed);
            message.delete();
        }
        if(message.getContent().startsWith("!게임 끝")){
            games.remove(event.getChannel());
            event.getChannel().sendMessage("게임이 종료되었습니다.");
        }

    }

}