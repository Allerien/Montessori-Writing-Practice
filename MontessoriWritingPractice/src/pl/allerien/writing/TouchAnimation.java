package pl.allerien.writing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TouchAnimation{
	private Sprite frame1 = new Sprite(Assets.frame1);
	private Sprite frame2 = new Sprite(Assets.frame2);
	private Sprite frame3 = new Sprite(Assets.frame3);
	private Sprite frame4 = new Sprite(Assets.frame4);
	private Sprite frame5 = new Sprite(Assets.frame5);
	private Sprite frame6 = new Sprite(Assets.frame6);
	
	
	private final static float textureUpdateTime = 0.1f;
	private float playerTimeElapsed = 0;
	private int currentFrame = 1;
	
    public void update() {
    	
    	playerTimeElapsed += Gdx.graphics.getDeltaTime();

        if(playerTimeElapsed >= textureUpdateTime)
        {
        	
            if(currentFrame < 6)	currentFrame += 1;
            	
            else				  	currentFrame = 1;
     
        playerTimeElapsed = 0;
        }
    }
 
    public void render(SpriteBatch batch) {
    	update();
    	switch (currentFrame) {
        case 1:
            frame1.draw(batch);
            break;
     
        case 2:
            frame2.draw(batch);
            break;
            
        case 3:
        	frame3.draw(batch);
        	break;
        	
        case 4:
        	frame4.draw(batch);
        	break;
        	
        case 5:
        	frame5.draw(batch);
        	break;
        	
        case 6:
        	frame6.draw(batch);
        	break;
     
        default:
            break;
		}
		
    }   
	public void setPosition(float f, float g) {
       frame1.setPosition(f,g);
       frame2.setPosition(f,g);
       frame3.setPosition(f,g);
       frame4.setPosition(f,g);
       frame5.setPosition(f,g);
       frame6.setPosition(f,g);
	}
	
	public void setColor(int r, int g, int b, float s){
		frame1.setColor(r,g,b,s);
		frame2.setColor(r,g,b,s);
		frame3.setColor(r,g,b,s);
		frame4.setColor(r,g,b,s);
	}
}
    