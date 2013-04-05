/**
 * 
 */
package TriForceDraynorFisher;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

import F2PSheeps.Paint;

/**
 * @author Sk1tzN4sty
 * 
 */
public class Fish extends Node {

  private static int[] SPOT_ID = { 327 };
	public static Filter<NPC> spotFilter = new Filter<NPC>() {

		@Override
		public boolean accept(NPC npc) {
			for (int x : SPOT_ID) {
				if (FISH_SPOT.contains(npc) && npc.getId() == x
						&& npc.getInteracting() == null) {
					return true;
				}
			}
			return false;
		}
	};

	public static Area FISH_SPOT = new Area(new Tile[] {
			new Tile(3098, 3247, 0), new Tile(3101, 3225, 0),
			new Tile(3087, 3223, 0), new Tile(3081, 3247, 0) });

	public static boolean catched = true;
	private static NPC target = null;

	@Override
	public boolean activate() {
		target = NPCs.getNearest(spotFilter);
		return !Inventory.isFull()
				&& target != null
				&& (catched == true || Players.getLocal().getInteracting() == null);
	}

	@Override
	public void execute() {
		target = NPCs.getNearest(spotFilter);
		catched = false;
		if (target != null) {
			if (!target.isOnScreen()) {
				Camera.turnTo(target);
				Task.sleep(500);
				if (Calculations.distanceTo(target) >= 7) {
					Walking.walk(target);
				}
			}
			Paint.status = "Fishing";
			target.interact("Net");
			Timer t = new Timer(3000);
			while (t.isRunning() && !catched) {
				Task.sleep(500);
			}
		}
	}

}
