package PongGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public double x, y;
	public int width, height;

	public double dx, dy, speed = 1.7;

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;

		int angle = new Random().nextInt(120 - 45) + 46;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}

	public void tick() {
		if (x + (dx * speed) + width >= Game.WIDTH) {
			dx *= -1;
		} else if (x + (dx * speed) < 0) {
			dx *= -1;
		}

		if (y >= Game.HEIGHT) {
			// ponto do inimigo.
			System.out.println("ponto do inimigo");
			new Game();
			return;
		} else if (y < 0) {
			// ponto do jogador
			System.out.println("ponto nosso");
			new Game();
			return;
		}

		Rectangle bounds = new Rectangle((int) (x + (dx * speed)), (int) (y + (dy * speed)), width, height);

		Rectangle boudsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boudsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.width,
				Game.enemy.height);

		if (bounds.intersects(boudsPlayer)) {
			int angle = new Random().nextInt(120 - 45) + 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if (dy > 0)
				dy *= -1;

		} else if (bounds.intersects(boudsEnemy)) {
			int angle = new Random().nextInt(120 - 45) + 46;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if (dy < 0)
				dy *= -1;
		}

		x += dx * speed;
		y += dy * speed;

	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval((int) x, (int) y - 10, width, height);
	}

}
