package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

public class Player {
    int x;
    int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void up() {
        this.y = this.y + 1;
    }

    public void down() {
        this.y = this.y - 1;
    }

    public void left() {
        this.x = this.x - 1;
    }

    public void right() {
        this.x = this.x + 1;
    }

    public static void processKey(TETile[][] world, Player player) {
        if (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            switch (key) {

                case 'w':
                    if (world[player.x][player.y + 1] != Tileset.WALL) {
                        player.up();
                    }
                    break;
                case 'a':
                    if (world[player.x - 1][player.y] != Tileset.WALL) {
                        player.left();
                    }
                    break;
                case 's':
                    if (world[player.x][player.y - 1] != Tileset.WALL) {
                        player.down();
                    }
                    break;

                case 'd':
                    if (world[player.x + 1][player.y] != Tileset.WALL) {
                        System.out.println("fuck luna hardly on the bed");
                        player.right();
                    }
                    break;

                default:
                    break;
            }

        }

    }


    public static void main(String[] args) {
        Player player = new Player(8, 10);
        TERenderer ter = new TERenderer();
        // initialize tiles
        TETile[][] world = new TETile[70][50];
        ter.initialize(70, 50);

        while (true) {
            StdDraw.clear();
            for (int i = 0; i < 70; i++) {
                for (int j = 0; j < 50; j++) {
                    world[i][j] = Tileset.FLOOR;
                }
            }
            for (int i = 0; i < 70; i++) {
                world[i][20] = Tileset.WALL;
            }
            world[player.x][player.y] = Tileset.PLAYER;
            ter.renderFrame(world);
            processKey(world, player);
        }
    }
}

//            if(StdDraw.hasNextKeyTyped()){
//                char key = StdDraw.nextKeyTyped();
//                switch (key){
//
//                    case 'w': //won't freeze after holding down and releasing 'w'
//                        if (world[player.x][player.y + 1] == Tileset.FLOOR){
//                            player.up();
//                        }
//                        break;
//                    case 'a': //programme will freeze after holding down and releasing 'a'
//                        if (world[player.x - 1][player.y] == Tileset.FLOOR){
//                            player.left();
//                        }
//
//                        break;
//                    case 's': //programme will freeze after holding down and releasing 's'
//                        if (world[player.x][player.y - 1] == Tileset.FLOOR) {
//                            player.down();
//                        }
//                        break;
//
//                    case 'd': //won't freeze after holding down and releasing 'd'
//                        if (world[player.x + 1][player.y] == Tileset.FLOOR){
//                            player.right();
//                        }
//                        break;
//                    /*
//                    case 't': //won't freeze after holding down and releasing 't'
//                        player.moveTop();
//                        break;
//                    case 'f': ////won't freeze after holding down and releasing 'f'
//                        player.moveLeft();
//                        break;
//                    case 'g': ////won't freeze after holding down and releasing 'g'
//                        player.moveBot();
//                        break;
//                    case 'h': ////won't freeze after holding down and releasing 'h'
//                        player.moveRight();
//                        break;
//                        */
//
//                    default: // however, if I rebind movement keys to "tfgh", programme won't freeze after holding down
//                        // (and releasing) any of them
//                        break;
//                }
//
//            }
//
//        }
//    }


//            if (StdDraw.hasNextKeyTyped()) {
//                char key = StdDraw.nextKeyTyped();
//                processInput(world, key);
//            }
//        }




//    public static Position startPos(TETile[][] world, Random RANDOM){
//        while (true){
//            int playerX = RANDOM.nextInt(world.length-2);
//            int playerY = RANDOM.nextInt(world[0].length-2);
//            if (world[playerX][playerY] == Tileset.FLOOR){
//                world[playerX][playerY] = Tileset.PLAYER;
//                return new Position(playerX, playerY);
//            }
//        }
//    }
//
//    public static Position up(TETile[][] world, Position currentPos){
//        if (world[currentPos.x][currentPos.y + 1] == Tileset.FLOOR){
//            Position newPos = new Position(currentPos.x, currentPos.y + 1);
//            world[currentPos.x][currentPos.y + 1] = Tileset.PLAYER;
//            world[currentPos.x][currentPos.y] = Tileset.FLOOR;
//            return newPos;
//        }else{
//            return currentPos;
//        }
//    }
//
//    public static Position down(TETile[][] world, Position currentPos){
//        if (world[currentPos.x][currentPos.y - 1] == Tileset.FLOOR){
//            Position newPos = new Position(currentPos.x, currentPos.y + 1);
//            world[currentPos.x][currentPos.y - 1] = Tileset.PLAYER;
//            world[currentPos.x][currentPos.y] = Tileset.FLOOR;
//            return newPos;
//        }else{
//            return currentPos;
//        }
//    }
//
//    public static Position left(TETile[][] world, Position currentPos){
//        if (world[currentPos.x - 1][currentPos.y] == Tileset.FLOOR){
//            Position newPos = new Position(currentPos.x, currentPos.y + 1);
//            world[currentPos.x - 1][currentPos.y] = Tileset.PLAYER;
//            world[currentPos.x][currentPos.y] = Tileset.FLOOR;
//            return newPos;
//        }else{
//            return currentPos;
//        }
//    }
//
//    public static Position right(TETile[][] world, Position currentPos){
//        if (world[currentPos.x + 1][currentPos.y] == Tileset.FLOOR){
//            Position newPos = new Position(currentPos.x, currentPos.y + 1);
//            world[currentPos.x + 1][currentPos.y] = Tileset.PLAYER;
//            world[currentPos.x][currentPos.y] = Tileset.FLOOR;
//            return newPos;
//        }else{
//            return currentPos;
//        }
//    }
//
//    public static Position move(TETile[][] world, Position currentPos){
//        char key = StdDraw.nextKeyTyped();
//        if (key == 'W'){
//            return up(world, currentPos);
//        }
//        if (key == 'A'){
//            return left(world, currentPos);
//        }
//        if (key == 'S'){
//            return down(world, currentPos);
//        }
//        else{
//            return right(world, currentPos);
//        }
//    }
//

//
//        Position start = startPos(world, new Random(666));
//        Position currentPos = start;
//        if ((StdDraw.isKeyPressed(87)) || (StdDraw.isKeyPressed(65)) ||
//                (StdDraw.isKeyPressed(83)) || StdDraw.isKeyPressed(68)){
//            Position newPos = move(world, currentPos);
//            currentPos = newPos;
//        }


//        ter.renderFrame(world);
//    }

