package constants;

import org.osbot.rs07.api.map.Area;

public enum Location {
    VARROCK_WEST_TREE(new Area(new int[][]{
            {3172, 3422},
            {3156, 3417},
            {3158, 3396},
            {3172, 3396}
    }
    ));
    private Area area;

    Location(Area area) {
        this.area = area;
    }

    public Area getArea() {
        return area;
    }
}