package it.lorciv.lexi;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import it.lorciv.lexi.geom.Point;
import it.lorciv.lexi.geom.Rect;

public class Row implements Glyph {
	
	private List<Glyph> children;
	
	public Row() {
		children = new ArrayList<Glyph>();
	}

	@Override
	public void draw(Graphics g, Point loc) {
		Rect bounds = bounds(loc);
		g.setColor(Color.GREEN);
		g.drawRect(bounds.getOrigin().getX(), bounds.getOrigin().getY(), bounds.getWidth(), bounds.getHeight());
		
		for (Glyph child : children) {
			child.draw(g, loc);
			Rect b = child.bounds(loc);
			loc = new Point(loc.getX()+b.getWidth(), loc.getY());
		}
	}

	@Override
	public Rect bounds(Point loc) {
		int width = 0, height = 0;
		for (Glyph child : children) {
			Rect bounds = child.bounds(loc);
			int w = bounds.getWidth();
			int h = bounds.getHeight();
			width += w;
			if (h > height) {
				height = h;
			}
		}
		return new Rect(loc, new Point(loc.getX()+width, loc.getY()+height));
	}

	@Override
	public void add(Glyph child) {
		children.add(child);
	}

}