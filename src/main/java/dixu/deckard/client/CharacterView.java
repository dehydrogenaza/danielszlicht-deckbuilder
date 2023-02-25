package dixu.deckard.client;

import dixu.deckard.server.Minion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterView {

    private Minion minion;
    private CardView cardView;
    private HandView handView;
    private List<CounterView> counters = new ArrayList<>();

    public CharacterView(Minion minion) {
        this.minion = minion;
        this.handView = new HandView(minion.getHand());
        cardView = new CardView(  0,new ArrayList<>(List.of(minion.getMinionCard())));
        CounterView healthCounter = new CounterView(Direction.BOTTOM, Direction.BOTTOM, minion::getHealth);
        healthCounter.setDescription("♥: ");
        cardView.addCounter(healthCounter);
        CounterView drawCounter = new CounterView(Direction.BOTTOM, Direction.LEFT, () -> minion.getDraw().size(), Color.GRAY);
        drawCounter.setDescription("\uD83C\uDCA0: ");
        counters.add(drawCounter);
        CounterView discardCounter = new CounterView(Direction.BOTTOM, Direction.RIGHT, () -> minion.getDiscard().size(), Color.GRAY);
        discardCounter.setBlinking(false);
        discardCounter.setDescription("\uD83C\uDCC1: ");
        counters.add(discardCounter);
    }

    public void render(Graphics g) {
        cardView.render(g);
        g.translate(-CardView.CARD_WIDTH,-CardView.CARD_HEIGHT -20);
        handView.render(g);
        g.translate(CardView.CARD_WIDTH,CardView.CARD_HEIGHT +20);
        Rectangle r = new Rectangle(0,CardView.CARD_HEIGHT/2,CardView.CARD_WIDTH,CardView.CARD_HEIGHT);
        for (CounterView counter : counters) {
            counter.render(g, r);
        }
    }


    public Minion getCharacter() {
        return minion;
    }
}
