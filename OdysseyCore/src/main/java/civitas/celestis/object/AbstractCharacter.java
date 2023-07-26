package civitas.celestis.object;

import civitas.celestis.geometry.relative.Geometry;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import civitas.celestis.player.Player;
import civitas.celestis.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * <h2>AbstractCharacter</h2>
 * <p>An adapter class for {@link Character}.</p>
 */
public abstract class AbstractCharacter extends AbstractObject implements Character {
    /**
     * All-args constructor.
     *
     * @param uniqueId       Unique identifier of this character
     * @param world          World of this character
     * @param geometry       Geometric profile of this character
     * @param location       Location of this character
     * @param acceleration   Acceleration of this character
     * @param rotation       Rotation of this character
     * @param rotationRate   Rate of rotation of this character
     * @param player         Player controlling this character
     * @param viewportOffset Viewport offset of this character
     */
    public AbstractCharacter(
            @Nonnull UUID uniqueId,
            @Nullable World world,
            @Nonnull Geometry geometry,
            @Nonnull Vector location,
            @Nonnull Vector acceleration,
            @Nonnull Quaternion rotation,
            @Nonnull Quaternion rotationRate,
            @Nullable Player player,
            @Nonnull Vector viewportOffset
    ) {
        super(uniqueId, world, geometry, location, acceleration, rotation, rotationRate);
        this.player = player;
        this.viewportOffset = viewportOffset;
    }

    @Nullable
    private Player player;
    @Nonnull
    private Vector viewportOffset;

    @Override
    @Nullable
    public Player getPlayer() {
        return player;
    }

    @Override
    @Nonnull
    public Vector getViewportOffset() {
        return viewportOffset;
    }

    @Override
    public void setPlayer(@Nullable Player player) {
        this.player = player;
    }

    @Override
    public void setViewportOffset(@Nonnull Vector viewportOffset) {
        this.viewportOffset = viewportOffset;
    }
}
