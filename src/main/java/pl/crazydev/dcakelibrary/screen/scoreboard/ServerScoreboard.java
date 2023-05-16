package pl.crazydev.dcakelibrary.screen.scoreboard;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import pl.crazydev.dcakelibrary.text.TextFormatter;

public class ServerScoreboard implements Scoreboard {
    private final org.bukkit.scoreboard.Scoreboard scoreboard;
    private final Objective objective;

    public ServerScoreboard(String title, DisplaySlot displaySlot, String... lines) {
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
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
    }

    @Override
    public void show() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(scoreboard);
        }
    }
}
