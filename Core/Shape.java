package byog.Core;

import byog.TileEngine.TETile;

public class Shape {
    
    public static Position[] adjacentCells(Position pos){
        Position[] adjacentPos = new Position[4];
        adjacentPos[0] = new Position(pos.x, pos.y + 1); /*up */
        adjacentPos[1] = new Position(pos.x - 1, pos.y); /*left */
        adjacentPos[2] = new Position(pos.x, pos.y - 1); /*down */
        adjacentPos[3] = new Position(pos.x + 1, pos.y); /*right */
        return adjacentPos;
    }


    public static Position[] nearCells(Position pos){
        Position[] nearPos = new Position[4];
        nearPos[0] = new Position(pos.x, pos.y + 2); /*up */
        nearPos[1] = new Position(pos.x - 2, pos.y); /*left */
        nearPos[2] = new Position(pos.x, pos.y - 2); /*down */
        nearPos[3] = new Position(pos.x + 2, pos.y); /*right */
        return nearPos;
    }

    public static boolean isSide(TETile[][] world, Position pos) {
        return pos.x == 2 || pos.y == 2 || pos.x == world.length - 3 || pos.y == world[0].length - 3;
    }

//    public static boolean isInnerSide(TETile[][] world, Position pos) {
//        return pos.x == 1 || pos.y == 1 || pos.x == world.length - 2 || pos.y == world[0].length - 2;
//    }


}

