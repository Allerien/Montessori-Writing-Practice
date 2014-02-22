package pl.allerien.writing;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Grain {

	private Sprite grain = new Sprite(Assets.grain);

public void render(SpriteBatch batch){
	grain.draw(batch);
}

public void setPosition(float newX, float newY){
    grain.setPosition(newX, newY);
}

public float getX(){
    return grain.getX();
}

public float getY(){
    return grain.getY();
}

public void update(){
	
}

public Sprite getGrain(){
	return grain;
}


}
