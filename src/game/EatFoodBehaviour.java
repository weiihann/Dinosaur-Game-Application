package game;

import edu.monash.fit2099.engine.*;

/**
 * A class to allow Actor to have eating food behaviour.
 */
public class EatFoodBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {

        //If actor is standing on grass or a fruit, actor will eat it
        if (actor.hasCapability(DinosaurCapability.HERBIVORE)) {
            if (map.locationOf(actor).getGround().getDisplayChar() == '^') {
                return new EatFoodAction(actor, new Grass());
            }
            for (Item item : map.locationOf(actor).getItems()) {
                if (item.toString() == "Fruit") {
                    return new EatFoodAction(actor, item);
                } else if (item.toString() == "Hay") {
                    return new EatFoodAction(actor, item);
                }
            }
        } else if (actor.hasCapability(DinosaurCapability.CARNIVORE)) {
            for (Item item : map.locationOf(actor).getItems()){
                if (item.getDisplayChar() == '%') {
                    return new EatFoodAction(actor, item);
                }
            }
        }
        return null;
    }
}
