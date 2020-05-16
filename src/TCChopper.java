import constants.Location;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import tasks.*;
import util.Util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ScriptManifest(author = "TripleCheese", name = "TC Chopper", info = "Chop logs near Varrock west", version = 0, logo = "")
public final class TCChopper extends Script {
    private static final Skill[] skills = {Skill.WOODCUTTING};
    private static final int LOG_ID = 1511;

    private List<Task> taskList = new ArrayList<Task>();
    private String status = "Initializing Script";
    private long startTime;
    private int logPrice = 0;
    private int logsBanked = 0;


    @Override
    public final void onStart() {
        startTime = System.currentTimeMillis();
        for (final Skill skill : skills) {
            getExperienceTracker().start(skill);
        }

        Optional<Integer> price = Util.getPrice(LOG_ID);
        price.ifPresent(integer -> logPrice = integer);

        taskList.add(new Bank(this, "Banking logs"));
        taskList.add(new WalkToBank(this, "Walking to bank"));
        taskList.add(new WalkToSpot(this, "Walking to spot", Location.VARROCK_WEST_TREE));
//        taskList.add(new Drop(this, "Dropping logs"));
        taskList.add(new Chop(this, "Chopping tree", Location.VARROCK_WEST_TREE));

    }

    @Override
    public final int onLoop() {
        try {
            for (Task task : taskList) {
                if (task.activate()) {
                    status = task.getStatus();
                    if (task.getName().equalsIgnoreCase("Banking logs")) {
                        logsBanked += 28 - getInventory().getEmptySlots();
                    }
                    task.execute();
                    break; // Enable break to give priority to top task
                }
            }
        } catch (InterruptedException e) {
            log("There has been an error!");
            e.printStackTrace();
        }
        status = "Waiting...";
        return random(500, 800);
    }

    @Override
    public final void onExit() {
        log("Closing Script...");
    }

    @Override
    public void onPaint(final Graphics2D g) {
        final long runTime = System.currentTimeMillis() - startTime;
        final int paintWidth = 200;
        final int paintHight = 130;


        // Fill the background color rectangle
        g.setColor(new Color(120, 111, 100, 120));
        g.fillRect(0, 0, paintWidth, paintHight);
        g.setColor(Color.GRAY);

        // Create a border
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, paintWidth, paintHight);


        // Draw the main text and space is out bc its blurry
        g.setFont(g.getFont().deriveFont(14.0f));
        g.drawString("T C  C h o p p e r", 10, 20);

        g.setFont(g.getFont().deriveFont(12.0f));
        g.setColor(Color.WHITE);
        g.drawString("Status: " + status, 10, 40);
        g.drawString("Runtime: " + Util.formatTime(runTime), 10, 60);
        g.drawString("Logs Banked: " + logsBanked, 10, 80);
        g.drawString("Gold Earned: " + Util.formatValue(logPrice * logsBanked), 10, 100);
        g.drawString("TTL: " + Util.formatTime(getExperienceTracker().getTimeToLevel(Skill.WOODCUTTING)), 10, 120);
//        g.drawString("Gold/Hour: " + Util.goldPerHour(logPrice * logsBanked, runTime), 10, 120);

        // Draw the mouse cursor
        Point pos = getMouse().getPosition();

        g.drawLine(pos.x - 5, pos.y + 5, pos.x + 5, pos.y - 5);
        g.drawLine(pos.x + 5, pos.y + 5, pos.x - 5, pos.y - 5);
    }
}
