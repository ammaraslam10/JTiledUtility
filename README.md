# JTiledUtility
A utility for [JGameEgnine](https://github.com/ammaraslam10/JGameEngine) that can be used to easily import and add maps created using the [Tiled Map Editor](https://www.mapeditor.org/) (version 1.0 and 1.2 tested).
To develop NES/SNES styled games like Mario and The Legend of Zelda, maps made using tiled may be used to simplify the game creation process. The Utility requires JGameEngine and the Gson library to work.
## Usage
The following code adds a map sets `untitled.json` as the game space. This will create and display the tiles starting at 0, 0. See [Detailed Tutorial](https://github.com/ammaraslam10/JTiledUtility/wiki/Detailed-Tutorial)
```java
public class Driver {
    public static void main(String[] args) {
	MapTest d = new MapTest();
    }
}
class MapTest extends JGameEngine {
    MapTest() {
	this.setWindow("Test Tiled Map");
	JTiledUtility u = new JTiledUtility(this);
	u.setGameSpace("untitled.json");
    }
}
```
## Limitations 
The implementation is quite bare-bone and implements features selectively. Most maps made in tiled will not be compatible.

 1. Only Orthogonal maps with render the right down render order are supported.
 2. Infinite maps not supported.
 3. Map needs to be saved as an uncompressed CSV JSON file (when a new map is being created the settings can be changed to this).
 4. A tileset must not have margins, and should be kept in the same drive as the map.
 5. Only tile layers and object layers are supported.
 6. In objects, only rectangle and ellipse are supported (ellipse is rounded to a circle). They are taken as collisions and they will get the class `JTiledObject` which is a basic Game Object that has a collision mask.
### Things that are currently supported
Basic orthogonal maps with collisions that can be added from the tiled editor itself. The collisions can be given a custom class for advanced usage.
## Adding a custom class for collisions
All such classes must have a constructor that accepts an object that is or extends from `JGameEngine`. Each object layer will be assigned a class that resides on each index of the object layer. `JTiledObject.class` may be used for object layers with no extra properties.
```java
class MapTest extends JGameEngine {
    MapTest() {
	this.setWindow("Test Tiled Map");

	JTiledUtility u = new JTiledUtility(this);
	ArrayList<Class> list = new ArrayList<>();
	list.add(Player.class); // object layer 1 needs to be a player class object.
	list.add(JTiledObject.class); // don't care about object layer 2.
	u.setGameSpace("untitled.json", list);
    }
}
class Player extends JGameEngine.Object implements JGameEngine.Collision {
    JGameEngine e;
    public Player(JGameEngine e) {
	this.e = e;
	name = "Player";
    }
	... Code Omitted ...
```
## License
This project is licensed under the MIT License - see the LICENSE.md file for details

## Contributors
Ammar Aslam 

## Acknowledgments
The image tilemap.png is produced by [RedVoxel](https://red-voxel.itch.io/), it requires an attribution to be used.
