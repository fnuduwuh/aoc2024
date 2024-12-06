package day6;

import tools.InputHelper;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Day6 {
    final String north = "^";
    final String south = "v";
    final String east = ">";
    final String west = "<";
    String[][] map;
    String currentDir;
    Point currentPosition;
    List<Point2D> visited = new ArrayList<>();
    List<Point2D> obstacles = new ArrayList<>();

    public Day6() {
        this.map = new InputHelper(6).get2dArray();
    }

    public static void main(String... args) {
        Day6 solver = new Day6();
        solver.partOne();
    }

    private void partOne() {
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                if (List.of(north, south, east, west).contains(this.map[i][j])) {
                    this.currentDir = this.map[i][j];
                    this.currentPosition = new Point(j, i);
                    visited.add(this.currentPosition);
                }
                if (this.map[j][i].equalsIgnoreCase("#")) {
                    obstacles.add(new Point(i, j));
                }
            }
        }
        patrol();
    }

    private void patrol() {
        switch (this.currentDir) {
            case north:
                while (canMove(new Point(this.currentPosition.x, this.currentPosition.y-1))) {
                    move(new Point(this.currentPosition.x, this.currentPosition.y-1));
                }
                break;
            case south:
                while (canMove(new Point(this.currentPosition.x, this.currentPosition.y+1))) {
                    move(new Point(this.currentPosition.x, this.currentPosition.y+1));
                }
                break;
            case east:
                while (canMove(new Point(this.currentPosition.x+1, this.currentPosition.y))) {
                    move(new Point(this.currentPosition.x+1, this.currentPosition.y));
                }
                break;
            case west:
                while (canMove(new Point(this.currentPosition.x-1, this.currentPosition.y))) {
                    move(new Point(this.currentPosition.x-1, this.currentPosition.y));
                }
                break;
        }
        patrol();
    }

    private void move(Point moveTo) {
        if (!this.visited.contains(moveTo)) this.visited.add(moveTo);
        this.currentPosition = moveTo;
    }

    private boolean canMove(Point newPoint) {
        if (this.obstacles.contains(newPoint)) {
            turn();
            return false;
        }
        try {
            String s = this.map[(int) newPoint.getX()][(int) newPoint.getY()];
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Left the map. Points visited: " + this.visited.size());
            System.exit(0);
        }
        return false;
    }

    private void turn() {
        switch (this.currentDir) {
            case north:
                this.currentDir = east;
                break;
            case south:
                this.currentDir = west;
                break;
            case east:
                this.currentDir = south;
                break;
            case west:
                this.currentDir = north;
                break;
        }
    }
}
