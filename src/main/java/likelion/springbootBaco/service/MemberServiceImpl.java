package likelion.springbootBaco.service;

import likelion.springbootBaco.domain.Member;
import likelion.springbootBaco.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Profile("test")
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

//    @Autowired
//    public MemberServiceImpl(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


    @Override
    @Transactional
    public Long save(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Member findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return member;
        }
        throw new IllegalStateException("너가 찾는 멤버 없어.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional
    public void update(Long id, String name) {
        Optional<Member> findMember = memberRepository.findById(id);
        if (findMember.isPresent()) {
            Member member = findMember.get();
            member.setName(name);
        }
    }

}
