package civitas.celestis.object;

import civitas.celestis.geometry.discrete.Solid;
import civitas.celestis.geometry.relative.Geometry;
import civitas.celestis.io.Deserializer;
import civitas.celestis.io.Serializer;
import civitas.celestis.number.Quaternion;
import civitas.celestis.number.Vector;
import civitas.celestis.util.Identifiable;
import civitas.celestis.world.World;
import org.joda.time.Duration;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * <h2>OdysseyObject</h2>
 * <p>An object can be placed in worlds and interact with other objects.</p>
 */
public interface OdysseyObject extends Identifiable, Serializable {
    /**
     * Called every tick.
     *
     * @param delta Duration between the last tick and now
     */
    void tick(@Nonnull Duration delta);

    //
    // World
    //

    /**
     * Gets the world of this object.
     *
     * @return World
     */
    @Nullable
    World getWorld();

    /**
     * Sets the world of this object.
     *
     * @param world World
     */
    void setWorld(@Nullable World world);

    //
    // Physics
    //

    /**
     * Gets the geometric profile of this object.
     *
     * @return Geometry
     */
    @Nonnull
    Geometry getGeometry();

    /**
     * Gets the current discrete solid of this object.
     *
     * @return Discrete solid
     */
    @Nonnull
    Solid getSolid();

    /**
     * Gets the current location of this object.
     *
     * @return Location
     */
    @Nonnull
    Vector getLocation();

    /**
     * Gets the acceleration of this object.
     *
     * @return Acceleration
     */
    @Nonnull
    Vector getAcceleration();

    /**
     * Gets the rotation of this object.
     *
     * @return Rotation
     */
    @Nonnull
    Quaternion getRotation();

    /**
     * Gets the rate of rotation of this object.
     *
     * @return Rate of rotation
     */
    @Nonnull
    Quaternion getRotationRate();

    /**
     * Sets the geometric profile of this object.
     *
     * @param geometry Geometric profile
     */
    void setGeometry(@Nonnull Geometry geometry);

    /**
     * Sets the location of this object.
     *
     * @param location Location
     */
    void setLocation(@Nonnull Vector location);

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration Acceleration
     */
    void setAcceleration(@Nonnull Vector acceleration);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation Rotation
     */
    void setRotation(@Nonnull Quaternion rotation);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rate Rate of rotation
     */
    void setRotationRate(@Nonnull Quaternion rate);

    //
    // Physics Delegates
    //

    /**
     * Gets the current velocity of this object.
     *
     * @return Velocity
     */
    @Nonnegative
    double getVelocity();

    //
    // Physics Utility Functions
    //

    /**
     * Moves this object by given amount.
     *
     * @param amount Amount to move
     */
    void move(@Nonnull Vector amount);

    /**
     * Accelerates this object by given amount.
     *
     * @param amount Acceleration to apply
     */
    void accelerate(@Nonnull Vector amount);

    /**
     * Rotates this object by given amount.
     *
     * @param rotation Rotation to apply
     */
    void rotate(@Nonnull Quaternion rotation);

    //
    // Geometry Delegates
    //

    /**
     * Gets the mass of this object.
     *
     * @return Mass
     */
    @Nonnegative
    double getMass();

    /**
     * Gets the volume of this object.
     *
     * @return Volume
     */
    @Nonnegative
    double getVolume();

    /**
     * Gets the density of this object.
     *
     * @return Density
     */
    @Nonnegative
    double getDensity();

    //
    // Solid delegates
    //

    /**
     * Gets the current coefficient of drag of this object.
     *
     * @return Coefficient of drag
     */
    @Nonnegative
    double getDragCoefficient();

    /**
     * Gets the current cross-section of this object.
     *
     * @return Cross-section
     */
    @Nonnegative
    double getCrossSection();

    /**
     * Checks if this object overlaps given solid.
     *
     * @param solid Solid to check
     * @return {@code true} if this object overlaps with the solid
     */
    boolean overlaps(@Nonnull Solid solid);

    /**
     * Checks if this object overlaps another object.
     *
     * @param other Object to check
     * @return {@code true} if this object overlaps the other
     */
    boolean overlaps(@Nonnull OdysseyObject other);

    //
    // IO
    //

    /**
     * Gets a deserializer instance of {@code this}.
     *
     * @return Deserializer instance
     */
    @Nonnull
    Deserializer<? extends OdysseyObject> deserializer();

    /**
     * Gets a serializer instance of {@code this}.
     *
     * @return Serializer instance
     */
    @Nonnull
    Serializer<? extends OdysseyObject> serializer();
}
