package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(),new Water());

		List<String> map = Arrays.asList(
				"................................................................................",
				"................................................................................",
				".....#######....................................................................",
				".....#_____#....................................................................",
				".....#_____#....................................................................",
				".....###_###....................................................................",
				"................................................................................",
				"......................................+++.......................................",
				".......................................++++..........~~~~~~.....................",
				"...................................+++++.............~~~~~~.....................",
				".....................................++++++..........~~~~~~.....................",
				"......................................+++............~~~~~~.....................",
				".....................................+++........................................",
				"................................................................................",
				"............+++.................................................................",
				".............+++++..............................................................",
				"...............++........................................+++++..................",
				".............+++...............~~~~~~...............++++++++....................",
				"............+++................~~~~~~.................+++.......................",
				"...............................~~~~~~...........................................",
				"...............................~~~~~~....................................++.....",
				"........................................................................++.++...",
				".........................................................................++++...",
				"..........................................................................++....",
				"................................................................................");

		// New map for the game
		List<String> newMap = Arrays.asList(
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................");

		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		// Adding new map into the world
		GameMap newGameMap = new GameMap(groundFactory,newMap);
		world.addGameMap(newGameMap);

		Player player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));

		// Allows player to move from old map to new map
		int gameMapNorth = gameMap.getYRange().min();
		int newGameMapSouth = newGameMap.getYRange().max();
		boolean displayOnMenu;
		char someChar;
		for(int i=0; i<gameMap.getXRange().max()+1; i++){
			if ((i%3 == 0)) {
				displayOnMenu = true;
			} else{
				displayOnMenu = false;
			}
			// Old Map
			Border borderOld = new Border(newGameMap.at(i,newGameMapSouth-1),"to new map",displayOnMenu);
			gameMap.at(i,gameMapNorth).setGround(borderOld);

			// New Map
			Border borderNew = new Border(gameMap.at(i,gameMapNorth+1), "to old map", displayOnMenu);
			newGameMap.at(i,newGameMapSouth).setGround(borderNew);
		}
		// Allows player to move from new map to old map
		// TODO

		// Place a pair of stegosaurs in the middle of the map
		gameMap.at(30, 12).addActor(new Stegosaur("Male Stegosaur", true));
		gameMap.at(32, 12).addActor(new Stegosaur("Female Stegosaur", false));

		// Place a vending machine in the map
		gameMap.at(9,4).setGround(new VendingMachine());

		// Place grass initially
		Probability probability = new Probability();
		for(int i: gameMap.getYRange()){
			for(int j: gameMap.getXRange()){
				if(gameMap.at(j,i).getDisplayChar() == '.') {
					if(probability.calculateProbability(2)) {
						Grass grass = new Grass();
						gameMap.at(j,i).setGround(grass);
						grass.addEcoPoint();
					}
				}
			}
		}
		world.run();
	}
}
