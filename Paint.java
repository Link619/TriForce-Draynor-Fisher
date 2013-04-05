package TriForceDraynorFisher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Timer;

/**
 * @author DarkLink
 */
public class Paint {

  	public static int shrimpCounter;
  	public static int anchovyCounter;
	public static Timer runTime = new Timer(0);
	public static String status;

	public static void evokePaint(Graphics g1) {

		int sperhour = (int) Math.floor((shrimpCounter * 3600000D / runTime
				.getElapsed()));
		int aperhour = (int) Math.floor((anchovyCounter * 3600000D / runTime
				.getElapsed()));		

		Graphics2D g = (Graphics2D) g1;
		g.translate(0, 50);

		g.setColor(Color.GREEN);
		g.drawString("TriForce Fisher", 15, 29);
		g.drawString("Run Time: " + runTime.toElapsedString(), 15, 49);
		g.drawString("Shrimp Caught: " + Integer.toString(shrimpCounter), 15, 69);
		g.drawString("Shrimps P/h: " + sperhour, 15, 89);
		g.drawString("Anchovy Caught: " + Integer.toString(anchovyCounter), 15, 109);
		g.drawString("Anchovy P/h: " + aperhour, 15, 129);
		g.drawString("Status: " + status, 15, 149);
	}

	public static void evokeMouse(Graphics g) {
		g.setColor(Color.GREEN);
		Point p = Mouse.getLocation();
		g.drawLine(p.x - 6, p.y, p.x + 6, p.y);
		g.drawLine(p.x, p.y - 6, p.x, p.y + 6);
	}
}
