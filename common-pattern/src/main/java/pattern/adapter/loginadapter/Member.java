package pattern.adapter.loginadapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private String username;
    private String password;
    private String mid;
    private String info;
    private int age;
    private String addr;

}
