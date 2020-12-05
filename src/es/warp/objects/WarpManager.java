package es.warp.objects;

import es.warp.util.FilesSerialization;

import java.io.File;
import java.lang.management.OperatingSystemMXBean;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class WarpManager {
    private static Set<WarpPoint> warps = new HashSet<>();

    private WarpManager () {}

    public static void setUpWarps (File pluginFile) {
        try {
            warps.addAll((Collection<? extends WarpPoint>) FilesSerialization.desSerialize(pluginFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addWarp (WarpPoint warp) {
        warps.add(warp);
    }

    public static Set<WarpPoint> getCopyOfWarps () {
        return new HashSet<>(warps);
    }

    public static Optional<WarpPoint> getWarpByID (int id) {
        return warps.stream().filter(warp -> warp.id == id).findFirst();
    }

    public static void deleteWarpByID (int id) {
        warps.removeIf(warp -> warp.id == id);
    }
}
