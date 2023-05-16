package pl.crazydev.dcakelibrary.screen.bossBar;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class PlayerBossBar implements Bar {
    private BossBar bar;
    private Player player;

    public PlayerBossBar(Player player, String title) {
        this.player = player;
        bar = Bukkit.createBossBar(title, BarColor.WHITE, BarStyle.SOLID);
    }

    public PlayerBossBar(Player player, String title, BarColor color) {
        this.player = player;
        bar = Bukkit.createBossBar(title, color, BarStyle.SOLID);
    }

    public PlayerBossBar(Player player, String title, BarStyle style) {
        this.player = player;
        bar = Bukkit.createBossBar(title, BarColor.WHITE, style);
    }

    public PlayerBossBar(Player player, String title, BarColor color, BarStyle style) {
        this.player = player;
        bar = Bukkit.createBossBar(title, color, style);
    }
    @Override
    public void setColor(BarColor color) {
        bar.setColor(color);
    }

    @Override
    public void setStyle(BarStyle style) {
        bar.setStyle(style);
    }

    @Override
    public void show() {
        bar.setVisible(true);
    }

    @Override
    public void hide() {
        bar.setVisible(false);
    }

    @Override
    public void setFill(double fill) {
        bar.setProgress(fill);
    }

    @Override
    public boolean isVisible() {
        return bar.isVisible();
    }
}
