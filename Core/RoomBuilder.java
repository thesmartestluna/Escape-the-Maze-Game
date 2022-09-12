package byog.Core;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class RoomBuilder {
    private Position p;
    private int w;
    private int h;

    
    /* build a rectangle room, whose position is at the bottom left corner */
    public RoomBuilder(Position p, int w, int h){
        this.p = p;
        this.w = w;
        this.h = h;
    }

    public void drawRoom(TETile[][] world){
        for (int i = 0; i < w; i++){
            for (int j = 0; j < h; j++){
                world[p.x + i][p.y + j] = Tileset.FLOOR;
            }
        }
    
        // /*draw walls on the outside of room */
        // for (int i = 0; i < w; i++){
        //     world[p.x + i][p.y] = Tileset.WALL;
        //     world[p.x + i][p.y + h - 1] = Tileset.WALL;
        // }
        // for (int j = 0; j < h; j++){
        //     world[p.x][p.y + j] = Tileset.WALL;
        //     world[p.x + w - 1][p.y + j] = Tileset.WALL;
        // }
    }

    /* Generate random room */
    public static RoomBuilder randomRoom(TETile[][] world, Random RANDOM){
        int rdX = 3 + RANDOM.nextInt(59);
        int rdY = 5+ RANDOM.nextInt(37);
        Position rdP = new Position(rdX, rdY);
        /*Room size: 3 - 5 */
        int rdw = 3 + RANDOM.nextInt(3);
        int rdh = 3 + RANDOM.nextInt(3);
        RoomBuilder room = new RoomBuilder(rdP, rdw, rdh);
        return room;
    }

    /*Check Overlap */
    public static boolean isRectangleOverlap(RoomBuilder room1, RoomBuilder room2){
        boolean xOverlap = !((room1.p.x + room1.w < room2.p.x ) || (room2.p.x + room2.w < room1.p.x));
        boolean yOverlap = !((room1.p.y + room1.h < room2.p.y ) || (room2.p.y + room2.h < room1.p.y));
        return xOverlap && yOverlap;
    }

    /*Create a list of separated rooms */
    public static List<RoomBuilder> roomsMaker(){
        List<RoomBuilder> rooms = new ArrayList<>();
        // initialize tiles
        TETile[][] world = new TETile[70][50];

        Random RANDOM = new Random(123);
        RoomBuilder newRoom1 = randomRoom(world, RANDOM);
        rooms.add(newRoom1);

        int i = 0;
        while (i < 30){
            boolean flag = true;
            RoomBuilder newRoom = randomRoom(world, RANDOM);
            i++;
            // System.out.println(i + "th time");
            // System.out.println(newRoom.p.x);
            // System.out.println(newRoom.p.y);
            // System.out.println(newRoom.w);
            // System.out.println(newRoom.h);

            for (RoomBuilder r: rooms){
                if (isRectangleOverlap(r, newRoom)){
                    flag = false;
                    break;
                }
            }
            if (flag == true){
                rooms.add(newRoom);
            }
        }
        return rooms;
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(70, 50);

        // initialize tiles
        TETile[][] world = new TETile[70][50];
        for (int x = 0; x < 70; x += 1) {
            for (int y = 0; y < 50; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        List<RoomBuilder> roomlist = roomsMaker();
        for (RoomBuilder r : roomlist){
            r.drawRoom(world);
        }
        ter.renderFrame(world);
        // System.out.println(world.length);
        // System.out.println(world[0].length);
        // System.out.println(world[1].length);
}
}
   


