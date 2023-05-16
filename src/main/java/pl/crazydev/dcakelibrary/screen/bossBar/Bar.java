package pl.crazydev.dcakelibrary.screen.bossBar;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;

public interface Bar {
    void setColor(BarColor color);
    void setStyle(BarStyle style);
    void show();
    void hide();
    void setFill(double fill);
    boolean isVisible();
}
