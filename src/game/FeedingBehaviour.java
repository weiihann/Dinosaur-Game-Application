package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

public class FeedingBehaviour implements Behaviour {
    private FoodItem food;

    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Exit> exits = map.locationOf(actor).getExits();
        for (Exit exit : exits) {
            if(exit.getDestination().containsAnActor()) {
                for (Item item : actor.getInventory()) {
                    if (item instanceof FoodItem) {
                        return new FeedingAction(food);
                    }
                }
            }
        }
        return null;
    }
}
