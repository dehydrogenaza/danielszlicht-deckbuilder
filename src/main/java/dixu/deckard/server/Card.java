package dixu.deckard.server;

public class Card {
    private final String name;
    private  int value;
    private CardType type;
    private static int nextNr = 1;

    public Card(CardType type) {
        this.type = type;
        value = 1;
        if (type == CardType.ATTACK) {
            value = 2;
        } else if (type == CardType.MINION) {
            value = nextNr;
        }
        nextNr++;

        name = type.name()+ " "+ value;
    }

    public Card() {
        this(CardType.BLOCK);
    }

    public void play(Team team, Minion minion,Game game) {
        if (type == CardType.BLOCK) {
            minion.remove(this);
            team.addBlock(value);
        }
        if (type == CardType.ATTACK) {
            game.getEnemyTeamFor(team).applyRandomDmg(value);
            minion.remove(this);
        }
    }

    public String getName() {
        return name;
    }

    public CardType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", type=" + type +
                '}';
    }
}
