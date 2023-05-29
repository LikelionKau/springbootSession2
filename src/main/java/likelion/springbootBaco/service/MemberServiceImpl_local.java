package likelion.springbootBaco.service;

import likelion.springbootBaco.domain.Member;
import likelion.springbootBaco.repository.MemberRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("local")
@Service
public class MemberServiceImpl_local implements MemberService {
    private MemberRepository memberRepository;


    @Override
    public Long save(Member member) {
        return null;
    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    @Override
    public void update(Long id, String name) {

    }
}
