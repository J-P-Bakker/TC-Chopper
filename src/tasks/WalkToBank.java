package tasks;

import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.MethodProvider;

public class WalkToBank extends Task{
    public WalkToBank(MethodProvider ctx, String name) {
        super(ctx, name);
    }

    @Override
    public boolean activate() throws InterruptedException {
        return ctx.getInventory().contains("Logs")
                && ctx.getInventory().isFull()
                && !Banks.VARROCK_WEST.contains(ctx.myPosition());
    }

    @Override
    public void execute() throws InterruptedException {
        setStatus("Walking to bank");
        ctx.getWalking().webWalk(Banks.VARROCK_WEST);

    }
}
