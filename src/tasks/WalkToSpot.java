package tasks;

import constants.Location;
import org.osbot.rs07.script.MethodProvider;

public class WalkToSpot extends Task {

    private final Location location;

    public WalkToSpot(MethodProvider ctx, String name, Location location) {
        super(ctx, name);
        this.location = location;
    }

    @Override
    public boolean activate() throws InterruptedException {
        return !location.getArea().contains(ctx.myPosition())
                && !ctx.inventory.contains("Logs")
                && !ctx.myPlayer().isMoving();
    }

    @Override
    public void execute() throws InterruptedException {
        setStatus("Walking to tree location...");
        ctx.getWalking().webWalk(location.getArea());
    }
}
