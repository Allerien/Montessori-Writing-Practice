package pl.allerien.writing;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class WritingGame implements ApplicationListener, InputProcessor {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private SandPool sandPool = new SandPool();
	private ArrayList<Grain> sand = new ArrayList<Grain>();
	private TouchAnimation anim;
	private int spread = 7;
	private int backgroundSwitch = 1;
	private int rounded;
	private Texture background;
	private float button1X;
	private float button2X;
	private float menuX;
	private float menuY;
	private float menuSize;
	private float margin;
	boolean isTouched;
	boolean buttonTouched;
	public float touchX;
	public float touchY;
	public float h;
	public float w;
	@Override
	public void create() {		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		Assets.load();
		camera = new OrthographicCamera(w,h);
		camera.position.x = w/2;
		camera.position.y = h/2;
		batch = new SpriteBatch();
		anim = new TouchAnimation();
		anim.setPosition(w+10, 0);
		margin = 90;
		button1X = w - margin;
		button2X = w - margin;
		menuX = w - margin;
		menuY = 156;
		menuSize = 64;
		background = Assets.background1;
		rounded = (int)w/10;
		Gdx.input.setInputProcessor(this);
		for (int i=(int)w/16; i <= 15*(w/16); i += spread){
			for (int j=(int)h/15+rounded; j <= 14*(h/15)-rounded; j += spread)
			{Grain grain = sandPool.obtain();
			grain.setPosition((float)i+rBit()-margin/2,(float)j+rBit());
			sand.add(grain);
			}
			if (i<=2*w/10) rounded-=rounded/10;
			if (i>=8*w/10) rounded +=rounded/10;
		}

	}

	@Override
	public void dispose() {
		batch.dispose();
		Assets.dispose();
	}

	@Override
	public void render() {
	    Gdx.gl.glClearColor(0, 0, 0, 1); //1
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			batch.draw(background, 0, 0, w, h);
			batch.draw(Assets.button1, button1X, 10, 64, 64);
			batch.draw(Assets.button2, button2X, 82, 64, 64);
			batch.draw(Assets.button3, w-margin, 154, 64, 64);
		
			Iterator<Grain> it = sand.iterator();
	        while (it.hasNext()) {
	            Grain a = it.next();
	            a.render(batch);
	           }
	        anim.render(batch);
	        update();
		batch.end();

	}
	
	public void update(){
		touchX = touchX(Gdx.input.getX());
		touchY = touchY(Gdx.input.getY());
		camera.update();
		
		if (isTouched && touchX>button1X-margin/2 && touchY<74){
			buttonTouched = true;
			button1X = touchX-20;
			if (button1X > w - margin) button1X = w - margin; 
			anim.setPosition(button1X-margin/2, 10);
			if (button1X<w-margin*1.8) {
				reshuffle();
				button1X = w - margin;
			}
		}
		
		if (isTouched && touchX>button2X-margin/2 && touchY<146 && touchY>82){
			buttonTouched = true;
			button2X = touchX-20;
			if (button2X > w - margin) button2X = w - margin;
			anim.setPosition(button2X-margin/2, 74);
			if (button2X<w-margin*1.8){
				button2X = w - margin;
				backgroundSwitch++;
				if (backgroundSwitch>2) backgroundSwitch=1;			
				changeBackground(backgroundSwitch);
				reshuffle();
			}
			
		}
		
		if (isTouched && touchX>menuX && touchY<menuY+menuSize && touchY>menuY){
			buttonTouched = true;
			menuX = w - margin - 256;
			menuSize = 256;
			batch.draw(Assets.letters, w-margin-256, 154, 256, 256);
		}
		
		if (isTouched && !buttonTouched){
			Iterator<Grain> it = sand.iterator();
	        while (it.hasNext()) {
	            Grain a = it.next();
	            float x = a.getX();
	            float y = a.getY();
	            float newX;
	            float newY;
	            if (touchX>=x-8 && touchX<=x+12 && touchY>=y-8 && touchY<=y+12){
	            	if(touchX<x) newX = x+8+rBit();
	            	else newX = x-8-rBit();
	            	if(touchY<y) newY = y+8+rBit();
	            	else newY = y-8-rBit();
	            	a.setPosition(newX, newY);
	            }
	        }
		}
		//Gdx.app.log("Is touched ", "YES "+touchX+" "+touchY);
		Gdx.app.log("FPS", "" + Gdx.graphics.getFramesPerSecond());
  
	}
	
	private Texture changeBackground(int i){
		if (i==1) background=Assets.background1; 
		else if (i==2) background=Assets.background2;
		return background;
	}
	
	private void reshuffle(){
		int i = (int)w/16, j = (int)h/15+rounded, rounded = (int)w/10;
		Iterator<Grain> it = sand.iterator();
		while (it.hasNext()) {
			Grain a = it.next();
            j += spread;
            a.setPosition((float)i+rBit()-margin/2,(float)j+rBit());
            if (j > 14*(h/15)-rounded){
            	i += spread;
    			if (i<=2*w/10) rounded-=rounded/10;
    			if (i>=8*w/10) rounded +=rounded/10;
            	j = (int)h/15+rounded;
            }
            
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	private int rBit() {
		return MathUtils.random(0,3);
	}
	
	private float touchX(int anX){
		return anX;
	}
	private float touchY(int aY){
		return Math.abs(aY-h);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		isTouched = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		isTouched = false;
		buttonTouched = false;
		anim.setPosition(w+10, 0);
		button1X = w - margin;
		button2X = w - margin;
		menuX = w - margin;
		menuSize = 64;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
