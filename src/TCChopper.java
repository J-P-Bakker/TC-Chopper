import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

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


    @Override
    public final void onStart() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public final int onLoop() {
    }

    @Override
    public final void onExit() {
        log("Closing Script...");
    }

    @Override
    public void onPaint(final Graphics2D g) {
    }
}
