package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class userInfo {

    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String id;
    private String created_at;
    private String modified_at;
    private String type;
    private String active;
}

