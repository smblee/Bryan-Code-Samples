package hw.hw6;

import java.awt.Color;

public class ComponentStyleFactory {
	private static ComponentStyleFactory csf = new ComponentStyleFactory();
	
	
	private ComponentStyleFactory() {}
	
	public static ComponentStyleFactory instance() {
		return csf;
	}
	
	public ComponentStyler create(Color c1, Color c2) {
		//return ComponentStyler.create(c1, c2);
		return new ComponentStyler(c1, c2);
	}
	
	public ComponentStyler createMonochrome(Color c1) {
		Color c2 = createSecondColor(c1);
		return new ComponentStyler(c1, c2);
	}
	
	private Color createSecondColor(Color c) {
		return brighter(c);
	}
	
	public Color brighter(Color c) {
		float hsbVals[] = Color.RGBtoHSB(c.getRed(), 
				c.getGreen(), 
				c.getBlue(),
				null);
		Color lighter = Color.getHSBColor( hsbVals[0], hsbVals[1] * 0.3f, 0.5f * ( 1f + hsbVals[2] ));
		return lighter;
	}

}

