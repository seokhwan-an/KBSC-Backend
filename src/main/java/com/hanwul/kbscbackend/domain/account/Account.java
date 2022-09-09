package com.hanwul.kbscbackend.domain.account;

import com.hanwul.kbscbackend.domain.answer.Answer;
import com.hanwul.kbscbackend.domain.chatting_room.ChattingRoom;
import com.hanwul.kbscbackend.domain.emotion.Emotion;
import com.hanwul.kbscbackend.domain.emotion.EmotionLike;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Table(name = "account")
@Entity
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, unique = true)
    private String username;

    private String password;

    @Column(length = 45, unique = true)
    private String nickname;

    @OneToMany(mappedBy = "constructor", cascade = CascadeType.ALL)
    private List<ChattingRoom> chattingRoomList = new ArrayList<>();

//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
//    private List<EmotionLike> emotionLikeList = new ArrayList<>();

//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
//    private List<Emotion> emotionList = new ArrayList<>();

//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
//    private List<Answer> answerList = new ArrayList<>() ;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public Account() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Builder
    public Account(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
