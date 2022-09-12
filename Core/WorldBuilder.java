package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;
import java.util.List;

public class WorldBuilder {
    private static boolean gameOver = false;
    private static boolean win = false;
    private static int life = 1;

    public static void buildWorld(TETile[][] world, Random RANDOM){
        Hallway.hallwayBuilder(world, new Random(123));
        Hallway.adjustWall(world);
        List<RoomBuilder> roomlist = RoomBuilder.roomsMaker();
        for (RoomBuilder r : roomlist){
            r.drawRoom(world);
        }
    }

    public static void addDoor(TETile[][] world, Random RANDOM){
        while (true){
            int x = 3 + RANDOM.nextInt(59);
            int y = 5 + RANDOM.nextInt(37);
            if (world[x][y] == Tileset.FLOOR){
                world[x][y] = Tileset.LOCKED_DOOR;
                return;
            }
        }
    }

    public static void addFlower(TETile[][] world, Random RANDOM) {
        for (int i = 0; i < 8; i++) {
            int x = 3 + RANDOM.nextInt(50);
            int y = 5 + RANDOM.nextInt(37);
            if (world[x][y] == Tileset.FLOOR) {
                world[x][y] = Tileset.FLOWER;
            }
        }
    }

    public static void addTREE(TETile[][] world, Random RANDOM) {
        for (int i = 0; i < 14; i++) {
            int x = 3 + RANDOM.nextInt(59);
            int y = 5 + RANDOM.nextInt(37);
            if (world[x][y] == Tileset.FLOOR) {
                world[x][y] = Tileset.TREE;
            }
        }
    }



    public static void drawFrame(String s) {
        int width = 70;
        int height = 50;
        int midWidth = width / 2;
        int midHeight = height / 2;

        // Draw the actual text
        if (gameOver){
            StdDraw.clear(Color.lightGray);
            Font bigFont = new Font("Monaco", Font.BOLD, 80);
            StdDraw.setFont(bigFont);
            StdDraw.setPenColor(Color.red);
            StdDraw.text(midWidth, midHeight, s);
            StdDraw.show();
        }

    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();

        // initialize tiles
        TETile[][] world = new TETile[70][50];
        ter.initialize(70, 50);
        Player player = new Player(8, 9);

        StdDraw.clear();
        buildWorld(world, new Random(345));
        addDoor(world, new Random(333));
        addFlower(world, new Random(45));
        addTREE(world, new Random(77));





        while (!gameOver){
            world[player.x][player.y] = Tileset.PLAYER;
            StdDraw.setPenColor(Color.yellow);
            StdDraw.textRight(68,48,"Life: " + life);
            StdDraw.show();
            StdDraw.pause(10);
            ter.renderFrame(world);
            world[player.x][player.y] = Tileset.FLOOR;
            Player.processKey(world, player);


            if (world[player.x][player.y] == Tileset.FLOWER){
                life += 1;
                world[player.x][player.y] = Tileset.FLOOR;

            }
            if (world[player.x][player.y] == Tileset.TREE) {
                life -= 1;
                world[player.x][player.y] = Tileset.FLOOR;
            }

            if (world[player.x][player.y] == Tileset.LOCKED_DOOR){
                gameOver = true;
                win = true;
            }
            if (life == 0){
                gameOver = true;
                win = false;
            }
        }
        if ((gameOver) && (win)){
            drawFrame("YOU WIN!");
        }
        if ((gameOver) && (!win)){
            drawFrame("YOU DIE!");
        }


    }
}
