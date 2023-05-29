package likelion.springbootBaco.api;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import likelion.springbootBaco.domain.Member;
import likelion.springbootBaco.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    // 엔티티가 직접 반환되는(노출되는) 위험성 있는 api 버전
    @GetMapping("/api/v1/members")
    public List<Member> memberListV1() {
        return memberService.findAll();
    }

    @GetMapping("/api/v2/members")
    public Result memberListV2() {
        List<MemberDto> collect = memberService.findAll().stream().
                map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.save(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = Member.createMemberNoAddress(request.getName());
        Long id = memberService.save(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());
        Member findMember = memberService.findById(id);
        /**
         * 위와 같이 서비스 로직 단에서는 craete의 경우 조회를 위한 Long id를 반환하더라도,
         * 조회는 조회답게 dto 등을 반환하게,
         * 수정은 void로 반환하여 수정 답게 활용하는게 좋다. 쿼리와 커맨드를 분리하는 습관을 들이자.(서비스 로직 구성시 해당되는 이야기)
         */
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    static class UpdateMemberResponse {
        private Long id;
        private String name;

        public UpdateMemberResponse(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }


}
