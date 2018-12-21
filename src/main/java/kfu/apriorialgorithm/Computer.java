package kfu.apriorialgorithm;

import java.lang.reflect.Field;

public class Computer {
    private int id;
    private String computerName;
    private String ipAddress;
    private String osName;
    private String osVersion;
    private String bitOS;
    private String cpu;
    private int rm;
    private int diskSpace;
    private int freeDiskSpace;
    private int fillDiskSpace;
    private String screenQuality;
    private String videoCard;
    private String cpuId;
    private int ball;

    public Computer(int id, String computerName, String ipAddress, String osName, String osVersion, String bitOS, String cpu, int rm, int diskSpace, int freeDiskSpace, int fillDiskSpace, String screenQuality, String videoCard, String cpuId, int ball) {
        this.id = id;
        this.computerName = computerName;
        this.ipAddress = ipAddress;
        this.osName = osName;
        this.osVersion = osVersion;
        this.bitOS = bitOS;
        this.cpu = cpu;
        this.rm = rm;
        this.diskSpace = diskSpace;
        this.freeDiskSpace = freeDiskSpace;
        this.fillDiskSpace = fillDiskSpace;
        this.screenQuality = screenQuality;
        this.videoCard = videoCard;
        this.cpuId = cpuId;
        this.ball = ball;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBitOS() {
        return bitOS;
    }

    public void setBitOS(String bitOS) {
        this.bitOS = bitOS;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getRm() {
        return rm;
    }

    public void setRm(int rm) {
        this.rm = rm;
    }

    public int getDiskSpace() {
        return diskSpace;
    }

    public void setDiskSpace(int diskSpace) {
        this.diskSpace = diskSpace;
    }

    public int getFreeDiskSpace() {
        return freeDiskSpace;
    }

    public void setFreeDiskSpace(int freeDiskSpace) {
        this.freeDiskSpace = freeDiskSpace;
    }

    public int getFillDiskSpace() {
        return fillDiskSpace;
    }

    public void setFillDiskSpace(int fillDiskSpace) {
        this.fillDiskSpace = fillDiskSpace;
    }

    public String getScreenQuality() {
        return screenQuality;
    }

    public void setScreenQuality(String screenQuality) {
        this.screenQuality = screenQuality;
    }

    public String getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(String videoCard) {
        this.videoCard = videoCard;
    }

    public String getCpuId() {
        return cpuId;
    }

    public void setCpuId(String cpuId) {
        this.cpuId = cpuId;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id='" + id + '\'' +
                ", computerName='" + computerName + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", bitOS='" + bitOS + '\'' +
                ", cpu='" + cpu + '\'' +
                ", rm=" + rm +
                ", diskSpace=" + diskSpace +
                ", freeDiskSpace=" + freeDiskSpace +
                ", fillDiskSpace=" + fillDiskSpace +
                ", screenQuality='" + screenQuality + '\'' +
                ", videoCard='" + videoCard + '\'' +
                ", cpuId='" + cpuId + '\'' +
                ", ball=" + ball +
                '}';
    }

    public boolean myequals(Object object){
        Computer other = (Computer) object;
        boolean result = true;
        for(Field field: Computer.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if(!field.get(other).equals(field.get(this)))
                    result = false;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
