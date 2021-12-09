import java.util.ArrayList;
import java.util.Arrays;


class Game {
    private String[] players;
    private int size = 0;
    private int order = 0;
    private String word = null;
    private ArrayList<String> usedWords = new ArrayList<String>();

    public int getMaximum() {
        return players.length;
    }

    public Game(int size) {
        players = new String[size];
    }

    public void addPlayer(String name) {
        if (this.size <= players.length)
            players[size++] = name;
    }

    public String play(String name, String word) {
        if (!players[order % players.length].equals(name)) {
            return "님 턴아님";
        }

        if (word.length() <= 1) {
            return "한 글자 안됨";
        }
        if (order == 0) {
            this.word = word;
            usedWords.add(word);
            order++;
            return "pass";
        }
        if (usedWords.contains(word)) {
            return "중복 단어 안됨";
        }

        if (this.word.charAt(this.word.length() - 1) != word.charAt(0)) {
            return "앞 글자 끝과 다름";
        }
        this.word = word;
        usedWords.add(word);
        order++;
        return "pass";
    }

    @Override
    public String toString() {
        return "Game{" +
                "players=" + Arrays.toString(players) +
                ", size=" + size +
                ", order=" + order +
                ", word='" + word + '\'' +
                ", usedWords=" + usedWords +
                '}';
    }
    public boolean isPlayer(String name){
        for (int i=0; i<size; i++){
            if(players[i].equals(name)) return true;
        }
        return false;
    }
}
