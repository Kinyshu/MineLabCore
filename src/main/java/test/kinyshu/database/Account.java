package test.kinyshu.database;

import com.craftmend.storm.api.StormModel;
import com.craftmend.storm.api.markers.Column;
import com.craftmend.storm.api.markers.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "account")
public class Account extends StormModel {

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;
}
