package kfu.apriorialgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class JdbcComputerDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Computer computer) {
        String sql = "insert into  computers(computer_name, ip_addres, os_name, os_version, bit_os, cpu, rm, disk_space, freeDiskSpace, fillDiskSpace, screenQuality, video_card, cpu_id, ball) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, computer.getComputerName(), computer.getIpAddress(), computer.getOsName(), computer.getBitOS(), computer.getCpu(), computer.getRm(), computer.getDiskSpace(), computer.getFreeDiskSpace(), computer.getFillDiskSpace(), computer.getScreenQuality(), computer.getVideoCard(), computer.getCpuId(), computer.getBall());
    }

    public void setBall(Computer computer) {
        String sql = "update computers set ball = ? where computers.computer_name = ?;";
        jdbcTemplate.update(sql, computer.getBall(), computer.getComputerName());
    }

    //language=sql
    public List<Computer> getAllComputers() {
        String str = "select * from computers";
        List<Computer> computers = jdbcTemplate.query(str, (ResultSet rs, int rowNum) -> {
            return new Computer(
                    rs.getInt("id"),
                    rs.getString("computer_name"),
                    rs.getString("ip_addres"),
                    rs.getString("os_name"),
                    rs.getString("os_version"),
                    rs.getString("bit_os"),
                    rs.getString("cpu"),
                    rs.getInt("rm"),
                    rs.getInt("disk_space"),
                    rs.getInt("freediskspace"),
                    rs.getInt("filldiskspace"),
                    rs.getString("screenquality"),
                    rs.getString("video_card"),
                    rs.getString("cpu_id"),
                    rs.getInt("ball")
                    );
        });
        return computers;
    }
}
