
import java.io.File;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
	MapTest d = new MapTest();
    }
}
class MapTest extends JGameEngine {
    MapTest() {
	this.setWindow("Test Tiled Map");
	this.cameraDistance(0.25);

	ArrayList<Class> list = new ArrayList<>();
	list.add(Player.class);
	JTiledUtility u = new JTiledUtility(this);
	u.setGameSpace("src\\untitled.json", list);

	Player p1 = new Player(this, 0, 0);
	this.addObject(p1);
	this.cameraFollow(p1);
    }
}
class Player extends JGameEngine.Object implements JGameEngine.Collision {
    JGameEngine e;
    public Player(JGameEngine e) {
	this.e = e;
	name = "Player";
    }
    public Player(JGameEngine e, int x, int y) {
	this.x = x; this.y = y;
	this.e = e;
	name = "Player";
    }
    @Override public void start() { 
	e.collisionMaskAdd(this, e.cameraWidth()/2 - 12.5, e.cameraHeight()/2 - 12.5, 25, 25); 
    }
    @Override public void update() {
	if(e.keyPressing("up")) y = y - 4 * e.deltaTime;
	if(e.keyPressing("down")) y = y + 4 * e.deltaTime;
	if(e.keyPressing("left")) x = x - 4 * e.deltaTime;
	if(e.keyPressing("right")) x = x + 4 * e.deltaTime;

	e.drawRect(x + e.cameraWidth()/2 - 12.5, y + e.cameraHeight()/2 - 12.5, 25, 25);
	e.collisionMaskDebug();
	e.drawText(String.valueOf(e.fps()), e.cameraX() + 10, e.cameraY() + 20, e.color(0, 0, 0));
    }
    @Override public void collision(JGameEngine.Object with) {
	System.out.println("I'm touching " + with.name + "!");
    }
}