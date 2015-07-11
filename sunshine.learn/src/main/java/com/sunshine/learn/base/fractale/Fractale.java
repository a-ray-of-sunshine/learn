package com.sunshine.learn.base.fractale;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class Fractale extends Applet
{
	public void paint(Graphics g)
	{
		g.setColor(Color.green);
		tree(200.0, 400.0, 200.0, 0.0, 8, g);
	}
	
	public void tree(double x1, double y1, double x2, double y2, int n,
			Graphics g)
	{
		if (n >= 1)
		{
			double x3, x4, y3, y4;
			g.drawLine((int) (x1), (int) (y1), (int) (x2), (int) (y2));
			x3 = (x1 + x2) / 2;
			y3 = (y1 + y2) / 2;
			tree(x1, y1, x3, y3, n - 1, g);
			tree(x3, y3, x2, y2, n - 1, g);

			x4 = (y2 - y3) * 0.7071 + x3; // 0.7071 就是 2分之根号2
			y4 = (x3 - x2) * 0.7071 + y3;
			tree(x3, y3, x4, y4, n - 1, g);
			
			x3 = (x1 * 3 + x2) / 4;
			y3 = (y1 * 3 + y2) / 4;

			x2 = (x2 * 3 + x1) / 4;
			y2 = (y2 * 3 + y1) / 4;
			x4 = (x2 * 1.732 - y2 + 2 * x3 - x3 * 1.732 + y3) / 2;
			y4 = (x2 + y2 * 1.732 - x3 + 2 * y3 - 1.732 * y3) / 2;
			tree(x3, y3, x4, y4, n - 1, g);

		}
	}

}
