package javachallenge.client.teamcli;

import javachallenge.client.Client;
import javachallenge.exceptions.CellIsNullException;
import javachallenge.units.Unit;
import javachallenge.util.Cell;
import javachallenge.util.Direction;
import javachallenge.util.Edge;
import javachallenge.util.EdgeType;

public class TeamClient extends Client {

    private boolean isDFSArrive = false;
    boolean[][] flags;

    private Direction findPath(Cell source, Cell destination) {
        Direction[] dir = Direction.values();
        flags[source.getX()][source.getY()] = true;

        for (int i = 0; i < 6; i++) {
            try {
                Cell neighborCell = map.getNeighborCell(source, dir[i]);
                Edge neighborEdge = source.getEdge(dir[i]);
                if (neighborCell != null && !flags[neighborCell.getX()][neighborCell.getY()] &&
                        neighborEdge.getType() == EdgeType.OPEN && neighborCell.isGround()) {
                    if (neighborCell.equals(destination)) {
                        isDFSArrive = true;
                        return dir[i];
                    } else
                        findPath(neighborCell, destination);
                    if (isDFSArrive)
                        return dir[i];
                }
            } catch (CellIsNullException e) { }
        }
        System.out.println("Returning null");
        return null;
    }

    @Override
    public void step() {
//        for (int i = 0; i < Math.min(7, getMyUnits().size()); i++) {
//            flags = new boolean[map.getSizeX()][map.getSizeY()];
//            move(getMyUnits().get(i), findPath(getMyUnits().get(i).getCell(), getMap().getMines().get(0)));
//        }
        if (getTurn() > 2) {
            if (getTurn() % 2 == 0) {
                move(getMyUnits().get(0), Direction.SOUTHWEST);
                move(getMyUnits().get(0), Direction.SOUTHWEST);
            } else {
                move(getMyUnits().get(0), Direction.SOUTHEAST);
                move(getMyUnits().get(0), Direction.SOUTHEAST);
            }
            attack(getMyUnits().get(0), Direction.SOUTHEAST);
        }
    }
}
