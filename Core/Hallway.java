package byog.Core;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Hallway extends Shape{
    
    /*initialize */
    public static void initialize(TETile[][] world){
        for (int i = 0; i < world.length; i++){
            for (int j = 0; j < world[0].length; j++){
                world[i][j] = Tileset.NOTHING;
            }
        }

        for (int i = 2; i < world.length-3; i++){
            for (int j = 2; j < world[0].length-3; j++){
                world[i][j] = Tileset.WALL;
            }
        }
    
    
        for (int i = 3; i < world.length-4; i += 2){
            for (int j = 3; j < world[0].length-4; j+=2){
                world[i][j]=Tileset.NOTHING;
            }   
        }    
    }

    /* Randomly choose a starting point */
    public static Position rdStart(TETile[][] world, Random RANDOM){
        while (true){
            int startX = RANDOM.nextInt(world.length-2);
            int startY = RANDOM.nextInt(world[0].length-2);
            if (world[startX][startY] == Tileset.NOTHING){
                world[startX][startY] = Tileset.FLOOR;
                return new Position(startX,startY);
            }     
        } 
    }

    /* Create a list of potential candidates for the next move */
    public static List<Position> possibleCells(TETile[][] world, Position currentPos){
        List<Position> possibleCells = new ArrayList<>();
        Position[] adjacentCells = adjacentCells(currentPos);
        Position[] nearCells = nearCells(currentPos);
        // if (isSide(world, currentPos)){
        //     System.out.println(adjacentCells.length);
        //     System.out.println(nearCells.length);
        // }
        for (int i = 0; i < 4; i++){
            if (isSide(world, currentPos)){
                continue;
            }
            if ((world[adjacentCells[i].x][adjacentCells[i].y] == Tileset.WALL) && 
            (world[nearCells[i].x][nearCells[i].y] == Tileset.NOTHING)){
                possibleCells.add(nearCells[i]);
            }
        }
        return possibleCells;
    }

    /* Create a path through breaking walls */
    public static void carveCells(TETile[][] world, Random RANDOM, List<Position> possibleCells, Position currentPos){
        int rdDirection = RANDOM.nextInt(possibleCells.size());
        Position nextMoveNear = possibleCells.get(rdDirection);
        Position nextMoveAdja = new Position((int)((possibleCells.get(rdDirection).x + currentPos.x)*0.5), (int)((possibleCells.get(rdDirection).y + currentPos.y)*0.5));
        world[nextMoveNear.x][nextMoveNear.y] = Tileset.FLOOR;
        world[nextMoveAdja.x][nextMoveAdja.y] = Tileset.FLOOR;
        // currentPos = nextMoveNear;
        posList.add(nextMoveNear);

    }
    private static List<Position> posList = new ArrayList<>();

    public static void hallwayBuilder(TETile[][] world, Random RANDOM){
        initialize(world);
        Position start = rdStart(world, RANDOM);
        
        posList.add(start);
        while (!posList.isEmpty()){
            Position currentPos = posList.get(posList.size()-1);
            // System.out.println("haha");
            // System.out.println(posList.size());
            List<Position> possibleCellList = possibleCells(world, currentPos);
            // System.out.println(possibleCellList.size());
            if (!possibleCellList.isEmpty()){
                carveCells(world, RANDOM, possibleCellList, currentPos);
                // posList.add(currentPos);
            }else{
                posList.remove(currentPos);
            }
        }
    }

    public static void adjustWall(TETile[][] world){
        for (int i = 2; i < world.length-3; i++){
            world[i][4] = Tileset.WALL;
            world[i][46] = Tileset.WALL;
        }  
        
        for (int j = 2; j < world[0].length-3; j++){
            world[2][j] = Tileset.WALL;
            world[66][j] = Tileset.WALL;
        }   
        
        for (int i = 1; i < world.length-2; i++){
            world[i][2] = Tileset.NOTHING;
            world[i][3] = Tileset.NOTHING;
            world[i][47] = Tileset.NOTHING;
        }

        for (int j = 1; j < world[0].length-2; j++){
            world[1][j] = Tileset.NOTHING;
            world[67][j] = Tileset.NOTHING;
        }  
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        
        // initialize tiles
        TETile[][] world = new TETile[70][50];
        ter.initialize(70, 50);
        // initialize(world);
        
        hallwayBuilder(world, new Random(123));
        adjustWall(world);
    
        ter.renderFrame(world);
        
    }
}

