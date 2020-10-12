package game;

import edu.monash.fit2099.engine.Location;

/**
 * Child class of FoodItem which represents dinosaur egg which is the breeding result of Dinosaur.
 */
public class DinosaurEgg extends PortableItem implements EcoPointInterface, FoodInterface{

    private int incubationPeriod = 0;
    private String species;
    private int hatchesPoints;

    /**
     * Constructor.
     *
     * @param species
     */
    public DinosaurEgg(String species) {
        super(species + " Egg", '0');
        this.species = species;
        food.addFood(this.displayChar, 10);
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        Probability probability =new Probability();
        incubationPeriod ++;
        if (incubationPeriod == 5) {
            calculateHatchesPoints();
            ecoPoint.addEcoPoint(getHatchesPoints());
            if(probability.calculateProbability(50)) {
                currentLocation.addActor(new BabyDinosaur("Baby " + species, false, species));
            } else {
                currentLocation.addActor(new BabyDinosaur("Baby " + species, true, species));
            }
            currentLocation.removeItem(this);
        }
    }

    /**
     * Method to calculate Eco Points
     */
    public void calculateHatchesPoints(){
        if (species == "Stegosaur"){
            hatchesPoints = 100;
        }
        else{
            hatchesPoints = 1000;
        }
    }

    /**
     * Getter method to retrieve hatches points.
     *
     * @return hatches points
     */
    public int getHatchesPoints(){
        return hatchesPoints;
    }
}
