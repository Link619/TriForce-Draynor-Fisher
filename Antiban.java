package TriForceDraynorFisher;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Random;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * @author DarkLink
 * */
public class Antiban extends Node {

  @Override
	public boolean activate() {
		NPC target = NPCs.getNearest(Fish.spotFilter);
		return target == null && !Inventory.isFull();
	}

	@Override
	public void execute() {

		int antiban = Random.nextInt(1, 6);
		Paint.status = "Antiban";

		switch (antiban) {
		case 1:
			Camera.setPitch(Random.nextInt(10, 300));
			Task.sleep(2500, 4500);
		case 2:
			Camera.setAngle(Random.nextInt(10, 360));
			Task.sleep(1500, 3500);
		case 3:
			Mouse.move(Random.nextInt(1, 200), Random.nextInt(1, 200));
			Task.sleep(500, 2500);
		case 4:
			Tabs.ABILITY_BOOK.open();
			Task.sleep(1500, 2500);

		case 5:
			Tabs.EQUIPMENT.open();
			Task.sleep(1500, 2500);
		case 6:

		default:
			break;
		}

	}

}
