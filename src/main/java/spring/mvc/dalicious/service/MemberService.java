package spring.mvc.dalicious.service;

import org.springframework.transaction.annotation.Transactional;
import spring.mvc.dalicious.domain.Member;
import spring.mvc.dalicious.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    /**
     * 회원가입
     */
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        long start = System.currentTimeMillis();

        try {
            // 같은 이름이 있는 중복 회원은 받지 않음.
            // find 후, 중복된 ID가 있으면 에러처리.
            validateDuplicateName(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join " + timeMs + "ms");
        }
    }

    private void validateDuplicateName(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
