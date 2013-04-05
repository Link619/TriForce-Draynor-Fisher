package TriForceDraynorFisher;

import java.awt.Graphics;
import java.util.ArrayList;

import org.powerbot.core.Bot;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.widget.WidgetCache;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.client.Client;

/**
 * @author DarkLink
 */
@Manifest(authors = { "DarkLink" }, name = "TriForce Draynor Fisher", version = 1.3, description = "Draynor Fisher For Noobs")
public class TriForceDraynorFisher extends ActiveScript implements
  	PaintListener, MessageListener {

	public static ArrayList<Node> nodeCollection = new ArrayList<>();
	private Client client = Bot.client();

	public void provide(Node node) {
		if (node != null) {
			nodeCollection.add(node);
		}
	}

	@Override
	public int loop() {
		if (Game.getClientState() != Game.INDEX_MAP_LOADED) {
			return 1000;
		}

		if (client != Bot.client()) {
			WidgetCache.purge();
			Bot.context().getEventManager().addListener(this);
			client = Bot.client();
		}

		if (Game.isLoggedIn()) {
			for (Node node : nodeCollection) {
				if (node != null && node.activate()) {
					node.execute();
				}
			}
		}
		return Random.nextInt(50, 100);
	}

	public void onStart() {
		provide(new Fish());
		provide(new Banking());
		provide(new Antiban());
	}

	public void onStop() {
	}

	@Override
	public void onRepaint(Graphics g) {
		Paint.evokeMouse(g);
		Paint.evokePaint(g);
	}

	@Override
	public void messageReceived(MessageEvent m) {
		String message = m.getMessage();
		if (message.contains("catch some shrimp")) {
			Fish.catched = true;
			Paint.shrimpCounter++;
		} else {
			Fish.catched = false;
		}
	}
}
