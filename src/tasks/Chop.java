package tasks;

import constants.Location;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.script.MethodProvider;
import util.Sleep;

public class Chop extends Task {
    private final Location location;
    private static final String TREE_NAME = "Tree";

    public Chop(MethodProvider ctx, String name, Location location) {
        super(ctx, name);
        this.location = location;
    }

    @Override
    public boolean activate() throws InterruptedException {
        return !ctx.myPlayer().isAnimating() && !ctx.inventory.isFull();
    }

    @Override
    public void execute() throws InterruptedException {
        Entity treeToChop = ctx.getObjects().closest(location.getArea(), TREE_NAME);
        if (treeToChop != null) {
            if (treeToChop.isVisible()) {
                if (!ctx.myPlayer().isAnimating() && !ctx.myPlayer().isMoving()) {
                    treeToChop.interact("Chop down");
                    setStatus("Chopping tree...");
                    Sleep.sleepUntil(() -> !ctx.myPlayer().isAnimating() && !treeToChop.exists(), 10000, 500);
                }
            } else {
                ctx.camera.toEntity(treeToChop);
            }
        }
    }
}
