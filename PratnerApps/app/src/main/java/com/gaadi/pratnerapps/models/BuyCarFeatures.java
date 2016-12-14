
package com.gaadi.pratnerapps.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BuyCarFeatures implements Serializable {

    @SerializedName("cup_holders")
    @Expose
    private String cupHolders;
    @SerializedName("folding_rear_seat")
    @Expose
    private String foldingRearSeat;
    @SerializedName("tachometer")
    @Expose
    private String tachometer;
    @SerializedName("leather_seats")
    @Expose
    private String leatherSeats;
    @SerializedName("tubeless_tyres")
    @Expose
    private String tubelessTyres;
    @SerializedName("sun_roof")
    @Expose
    private String sunRoof;
    @SerializedName("fog_lights")
    @Expose
    private String fogLights;
    @SerializedName("wash_wiper")
    @Expose
    private String washWiper;
    @SerializedName("defogger")
    @Expose
    private String defogger;
    @SerializedName("anti_lock_braking_system")
    @Expose
    private String antiLockBrakingSystem;
    @SerializedName("driver_air_bags")
    @Expose
    private String driverAirBags;
    @SerializedName("pssenger_air_bags")
    @Expose
    private String pssengerAirBags;
    @SerializedName("immobilizer")
    @Expose
    private String immobilizer;
    @SerializedName("traction_control")
    @Expose
    private String tractionControl;
    @SerializedName("child_safety_locks")
    @Expose
    private String childSafetyLocks;
    @SerializedName("central_locking")
    @Expose
    private String centralLocking;
    @SerializedName("remote_boot_fuel_lid")
    @Expose
    private String remoteBootFuelLid;
    @SerializedName("power_windows")
    @Expose
    private String powerWindows;
    @SerializedName("power_steering")
    @Expose
    private String powerSteering;
    @SerializedName("power_door_locks")
    @Expose
    private String powerDoorLocks;
    @SerializedName("power_seats")
    @Expose
    private String powerSeats;
    @SerializedName("steering_adjustment")
    @Expose
    private String steeringAdjustment;
    @SerializedName("car_stereo")
    @Expose
    private String carStereo;
    @SerializedName("display_screen")
    @Expose
    private String displayScreen;

    /**
     * @return The cupHolders
     */
    public String getCupHolders() {
        return cupHolders;
    }

    /**
     * @param cupHolders The cup_holders
     */
    public void setCupHolders(String cupHolders) {
        this.cupHolders = cupHolders;
    }

    /**
     * @return The foldingRearSeat
     */
    public String getFoldingRearSeat() {
        return foldingRearSeat;
    }

    /**
     * @param foldingRearSeat The folding_rear_seat
     */
    public void setFoldingRearSeat(String foldingRearSeat) {
        this.foldingRearSeat = foldingRearSeat;
    }

    /**
     * @return The tachometer
     */
    public String getTachometer() {
        return tachometer;
    }

    /**
     * @param tachometer The tachometer
     */
    public void setTachometer(String tachometer) {
        this.tachometer = tachometer;
    }

    /**
     * @return The leatherSeats
     */
    public String getLeatherSeats() {
        return leatherSeats;
    }

    /**
     * @param leatherSeats The leather_seats
     */
    public void setLeatherSeats(String leatherSeats) {
        this.leatherSeats = leatherSeats;
    }

    /**
     * @return The tubelessTyres
     */
    public String getTubelessTyres() {
        return tubelessTyres;
    }

    /**
     * @param tubelessTyres The tubeless_tyres
     */
    public void setTubelessTyres(String tubelessTyres) {
        this.tubelessTyres = tubelessTyres;
    }

    /**
     * @return The sunRoof
     */
    public String getSunRoof() {
        return sunRoof;
    }

    /**
     * @param sunRoof The sun_roof
     */
    public void setSunRoof(String sunRoof) {
        this.sunRoof = sunRoof;
    }

    /**
     * @return The fogLights
     */
    public String getFogLights() {
        return fogLights;
    }

    /**
     * @param fogLights The fog_lights
     */
    public void setFogLights(String fogLights) {
        this.fogLights = fogLights;
    }

    /**
     * @return The washWiper
     */
    public String getWashWiper() {
        return washWiper;
    }

    /**
     * @param washWiper The wash_wiper
     */
    public void setWashWiper(String washWiper) {
        this.washWiper = washWiper;
    }

    /**
     * @return The defogger
     */
    public String getDefogger() {
        return defogger;
    }

    /**
     * @param defogger The defogger
     */
    public void setDefogger(String defogger) {
        this.defogger = defogger;
    }

    /**
     * @return The antiLockBrakingSystem
     */
    public String getAntiLockBrakingSystem() {
        return antiLockBrakingSystem;
    }

    /**
     * @param antiLockBrakingSystem The anti_lock_braking_system
     */
    public void setAntiLockBrakingSystem(String antiLockBrakingSystem) {
        this.antiLockBrakingSystem = antiLockBrakingSystem;
    }

    /**
     * @return The driverAirBags
     */
    public String getDriverAirBags() {
        return driverAirBags;
    }

    /**
     * @param driverAirBags The driver_air_bags
     */
    public void setDriverAirBags(String driverAirBags) {
        this.driverAirBags = driverAirBags;
    }

    /**
     * @return The pssengerAirBags
     */
    public String getPssengerAirBags() {
        return pssengerAirBags;
    }

    /**
     * @param pssengerAirBags The pssenger_air_bags
     */
    public void setPssengerAirBags(String pssengerAirBags) {
        this.pssengerAirBags = pssengerAirBags;
    }

    /**
     * @return The immobilizer
     */
    public String getImmobilizer() {
        return immobilizer;
    }

    /**
     * @param immobilizer The immobilizer
     */
    public void setImmobilizer(String immobilizer) {
        this.immobilizer = immobilizer;
    }

    /**
     * @return The tractionControl
     */
    public String getTractionControl() {
        return tractionControl;
    }

    /**
     * @param tractionControl The traction_control
     */
    public void setTractionControl(String tractionControl) {
        this.tractionControl = tractionControl;
    }


    public String getChildSafetyLocks() {
        return childSafetyLocks;
    }


    public void setChildSafetyLocks(String childSafetyLocks) {
        this.childSafetyLocks = childSafetyLocks;
    }


    public String getCentralLocking() {
        return centralLocking;
    }


    public void setCentralLocking(String centralLocking) {
        this.centralLocking = centralLocking;
    }

    public String getRemoteBootFuelLid() {
        return remoteBootFuelLid;
    }


    public void setRemoteBootFuelLid(String remoteBootFuelLid) {
        this.remoteBootFuelLid = remoteBootFuelLid;
    }


    public String getPowerWindows() {
        return powerWindows;
    }


    public void setPowerWindows(String powerWindows) {
        this.powerWindows = powerWindows;
    }


    public String getPowerSteering() {
        return powerSteering;
    }


    public void setPowerSteering(String powerSteering) {
        this.powerSteering = powerSteering;
    }


    public String getPowerDoorLocks() {
        return powerDoorLocks;
    }


    public void setPowerDoorLocks(String powerDoorLocks) {
        this.powerDoorLocks = powerDoorLocks;
    }


    public String getPowerSeats() {
        return powerSeats;
    }


    public void setPowerSeats(String powerSeats) {
        this.powerSeats = powerSeats;
    }


    public String getSteeringAdjustment() {
        return steeringAdjustment;
    }


    public void setSteeringAdjustment(String steeringAdjustment) {
        this.steeringAdjustment = steeringAdjustment;
    }

    public String getCarStereo() {
        return carStereo;
    }


    public void setCarStereo(String carStereo) {
        this.carStereo = carStereo;
    }


    public String getDisplayScreen() {
        return displayScreen;
    }


    public void setDisplayScreen(String displayScreen) {
        this.displayScreen = displayScreen;
    }

  /*  @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
*/
}
