package life.majiang.community.dto;


import lombok.Data;

@Data
public class GitHubUserDto {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
