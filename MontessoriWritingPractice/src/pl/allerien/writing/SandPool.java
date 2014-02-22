package pl.allerien.writing;

import com.badlogic.gdx.utils.Pool;

public class SandPool extends Pool<Grain>
{
    @Override
    protected Grain newObject()
    {

        return new Grain();
    }

}