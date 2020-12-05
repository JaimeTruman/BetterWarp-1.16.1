package es.warp.objects;

import org.bukkit.Location;

import java.io.Serializable;

public class WarpPoint implements Serializable {
    private static final long serialVersionUID = 1L;

    public final String itemName;
    public final String name;
    public final int x;
    public final int y;
    public final int z;
    public final float yaw;
    public final float pitch;
    public final String wordlName;
    public final int id;

    public WarpPoint(Location location, String itemName, String name) {
        this.itemName = itemName;
        this.name = name;
        this.x = (int) location.getX();
        this.y = (int) location.getY();
        this.z = (int) location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
        this.wordlName = location.getWorld().getName();

        this.id = WarpManager.getCopyOfWarps().size() + 1;
    }

    @Override
    public String toString() {
        return "WarpPoint{" +
                "itemName='" + itemName + '\'' +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", wordlName='" + wordlName + '\'' +
                ", id=" + id +
                '}';
    }
}