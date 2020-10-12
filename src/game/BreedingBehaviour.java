package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class which allows Dinosaur to breed.
 */
public class BreedingBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(DinosaurCapability.HEALTHY)) {
            Dinosaur dino = (Dinosaur) actor;
            for (Exit exit : map.locationOf(actor).getExits()) {
                Dinosaur dino1;
                if(exit.getDestination().containsAnActor() && exit.getDestination().getActor().hasCapability(DinosaurCapability.HEALTHY)) {
                    Actor actor1 = exit.getDestination().getActor();
                    dino1 = (Dinosaur) actor1;
                    if (!dino.isPregnant() && (!dino1.isPregnant())) {
                        return new BreedingAction(actor, exit.getDestination());
                    }
                }
                for (Exit exit1 : exit.getDestination().getExits()) {
                    if (exit1.getDestination().containsAnActor() && exit1.getDestination().getActor().hasCapability(DinosaurCapability.HEALTHY)) {
                        Actor actor1 = exit1.getDestination().getActor();
                        dino1 = (Dinosaur) actor1;
                        if (!dino1.isPregnant()) {
                            if (new FollowBehaviour(exit1.getDestination().getActor()).getAction(actor, map) != null) {
                                return new FollowBehaviour(exit1.getDestination().getActor()).getAction(actor, map);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
