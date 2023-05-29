package likelion.springbootBaco.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {
    @Id @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    @Embedded
    private Address address;

    public static Member createMember(String name, Address address) {
        Member member = new Member();
        member.name = name;
        member.address = address;
        return member;
    }

    public static Member createMemberNoAddress(String name) {
        Member member = new Member();
        member.name = name;
        return member;
    }

    public void setName(String name) {
        this.name = name;
    }
}
