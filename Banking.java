package TriForceDraynorFisher;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;

public class Banking extends Node {

  public static final Area FISH_SPOT = new Area(new Tile[] {
			new Tile(3098, 3247, 0), new Tile(3101, 3225, 0),
			new Tile(3087, 3223, 0), new Tile(3081, 3247, 0) });

	private static Tile[] toBank2 = new Tile[] { new Tile(3086, 3230, 0),
			new Tile(3086, 3239, 0), new Tile(3087, 3248, 0),
			new Tile(3092, 3244, 0) };

	@Override
	public boolean activate() {
		return Inventory.isFull()
				&& Players.getLocal().getInteracting() == null;
	}

	@Override
	public void execute() {
		Paint.status = "Walking to bank";
		walk(toBank2);
		while (!Bank.isOpen()) {
			if (!Players.getLocal().isMoving()) {
				Paint.status = "Opening Bank";
				Bank.open();
				Task.sleep(500);
			}
		}
		Paint.status = "Depositing Items";
		Bank.depositInventory();
		Paint.status = "Closing Bank";
		Bank.close();
		Paint.status = "Walking to fishing spot";
		walkReverse(toBank2);
		while (!Fish.FISH_SPOT.contains(Players.getLocal())) {
			Task.sleep(500);
		}
	}

	public static void walk(Tile[] t) {
		TilePath x = Walking.newTilePath(t);
		while (Calculations.distance(Walking.getDestination(), x.getEnd()) >= 3) {
			if (Calculations.distanceTo(Walking.getDestination()) <= 4
					|| !Players.getLocal().isMoving()) {
				Walking.walk(x.getNext());
			}

		}
	}

	public static void walkReverse(Tile[] t) {
		TilePath x = Walking.newTilePath(t).reverse();
		while (Calculations.distance(Walking.getDestination(), x.getEnd()) >= 3) {
			if (Calculations.distanceTo(Walking.getDestination()) <= 4
					|| !Players.getLocal().isMoving()) {
				Walking.walk(x.getNext());
			}

		}
	}

}
