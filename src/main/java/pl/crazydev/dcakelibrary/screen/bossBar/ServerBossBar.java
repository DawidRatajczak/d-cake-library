package pl.crazydev.dcakelibrary.screen.bossBar;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

public class ServerBossBar implements Bar {
    private BossBar bar;

    public ServerBossBar(String title) {
        bar = Bukkit.createBossBar(title, BarColor.WHITE, BarStyle.SOLID);
    }

    public ServerBossBar(String title, BarColor color) {
        bar = Bukkit.createBossBar(title, color, BarStyle.SOLID);
    }

    public ServerBossBar(String title, BarColor color, BarStyle style) {
        bar = Bukkit.createBossBar(title, color, style);
    }

    public ServerBossBar(String title, BarStyle style) {
        bar = Bukkit.createBossBar(title, BarColor.WHITE, style);
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
