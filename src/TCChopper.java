import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ScriptManifest(author = "TripleCheese", name = "TC Chopper", info = "Chop logs at Varrock west", version = 0, logo = "")
public final class TCChopper extends Script {
    private static final int LOG_ID = 1511;
    private static final int paintWidth = 200;
    private static final int paintHight = 130;
    private static final String TREE_NAME = "Tree";
    private static final String TREE_LOG = "Logs";
    private static final Skill[] skills = {Skill.WOODCUTTING};

    private long startTime;
    private String status = "Initializing Script";
    private int logPrice = 0;
    private int logsBanked = 0;


    @Override
    public final void onStart() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public final int onLoop() {

        return random(300, 700);
    }

    @Override
    public final void onExit() {
        log("Closing Script...");
    }

    @Override
    public void onPaint(final Graphics2D g) {
        final long runTime = System.currentTimeMillis() - startTime;


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
