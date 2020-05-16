package tasks;


import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.MethodProvider;
import util.Sleep;

public class Bank extends Task {


    public Bank(MethodProvider ctx, String name) {
        super(ctx, name);
    }

    public boolean activate() {
        return Banks.VARROCK_WEST.contains(ctx.myPosition())
                && ctx.getInventory().isFull();
    }

    @Override
    public void execute() throws InterruptedException {
        setStatus("Banking");

        if (ctx.bank.isOpen()) {
            ctx.bank.depositAll("Logs");
        } else {
            if (ctx.bank.open()) {
                Sleep.sleepUntil(() -> ctx.bank.isOpen(), 10000, 500);
            }
        }
    }
}

