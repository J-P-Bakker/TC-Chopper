package tasks;

import org.osbot.rs07.script.MethodProvider;

public class Drop extends Task {
    public Drop(MethodProvider ctx, String name) {
        super(ctx, name);
    }

    @Override
    public boolean activate() throws InterruptedException {
        return ctx.inventory.isFull();
    }

    @Override
    public void execute() throws InterruptedException {
        ctx.inventory.dropAll("Logs");
    }
}
