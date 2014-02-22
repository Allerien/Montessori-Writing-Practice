package pl.allerien.writing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

public static Texture grain;
public static Texture grainLarge;
public static Texture background1;
public static Texture background2;
public static Texture button1;
public static Texture button2;
public static Texture button3;
public static Texture frame1;
public static Texture frame2;
public static Texture frame3;
public static Texture frame4;
public static Texture frame5;
public static Texture frame6;
public static Texture letters;

	public static void load() {
		 grain = new Texture(Gdx.files.internal("gfx/grain3.png"));
		 grainLarge = new Texture(Gdx.files.internal("gfx/grain3.png"));
		 background1 = new Texture(Gdx.files.internal("gfx/a-background.png"));
		 background2 = new Texture(Gdx.files.internal("gfx/b-background.png"));
		 button1 = new Texture(Gdx.files.internal("gfx/button1.png"));
		 button2 = new Texture(Gdx.files.internal("gfx/button2.png"));
		 button3 = new Texture(Gdx.files.internal("gfx/button3.png"));
		 frame1 = new Texture(Gdx.files.internal("gfx/frame1.png"));
		 frame2 = new Texture(Gdx.files.internal("gfx/frame2.png"));
		 frame3 = new Texture(Gdx.files.internal("gfx/frame3.png"));
		 frame4 = new Texture(Gdx.files.internal("gfx/frame4.png"));
		 frame5 = new Texture(Gdx.files.internal("gfx/frame5.png"));
		 frame6 = new Texture(Gdx.files.internal("gfx/frame6.png"));
		 letters = new Texture(Gdx.files.internal("gfx/letters.png"));
    }
 
    
    public static void dispose() {
    	background1.dispose();
    	background2.dispose();
    	grain.dispose();
    	grainLarge.dispose();
    	button1.dispose();
      	button2.dispose();
      	button3.dispose();
    	frame1.dispose();
    	frame2.dispose();
    	frame3.dispose();
    	frame4.dispose();
    	frame5.dispose();
    	frame6.dispose();
    	letters.dispose();
    }
	
	
}
