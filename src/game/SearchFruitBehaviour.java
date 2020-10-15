package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class to allow Player to have a behaviour of searching fruits from trees.
 */
public class SearchFruitBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.locationOf(actor).getGround().toString() == new Tree().toString()) {
            return new SearchFruitAction();
        }

        return null;
    }
}


