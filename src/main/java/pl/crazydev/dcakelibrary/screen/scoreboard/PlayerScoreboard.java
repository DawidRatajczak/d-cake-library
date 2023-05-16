package pl.crazydev.dcakelibrary.screen.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import pl.crazydev.dcakelibrary.text.TextFormatter;

public class PlayerScoreboard implements Scoreboard {
    private final org.bukkit.scoreboard.Scoreboard scoreboard;
    private final Objective objective;
    private final Player player;

    public PlayerScoreboard(Player player, String title, DisplaySlot displaySlot, String... lines) {
        this.player = player;

        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective(title, "dummy", TextFormatter.color(title));
        objective.setDisplaySlot(displaySlot);

        if(lines == null) {
            return;
        }

        int index = 0;

        for(String line : lines) {
            setLine(line, index);
            index++;
        }
    }

    @Override
    public void setLine(String line, int index) {
        Score score = objective.getScore(TextFormatter.color(line));

        score.setScore(index);
    }

    @Override
    public void hide() {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    @Override
    public void show() {
        player.setScoreboard(scoreboard);
    }
}
