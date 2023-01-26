package org.mega.book.springboot.web;


import lombok.RequiredArgsConstructor;
import org.mega.book.springboot.service.PostsService;
import org.mega.book.springboot.web.dto.PostsResponseDto;
import org.mega.book.springboot.web.dto.PostsSaveRequestDto;
import org.mega.book.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // final필드 or @NonNull이 붙은 필드에 대해 생성자 생성
@RestController
public class PostsApiController {

    private final PostsService postsService;

    //일반적인 형태의 CRUD
    @PostMapping("/api/v1/posts") // URI경로가 /api/v1/posts 일 경우에
    //GetMapping은 사용자에게 노출시키는 처리 담당, PostMapping은 DB에 저장하는 역할
    public Long save(@RequestBody PostsSaveRequestDto requestDto){ // 저장
        return postsService.save(requestDto);
    }
    //비동기통신을 하기 위해서는 클라이언트에서 서버로 요청메세지를 보낼때, 서버에서 클라이언트로 응답메세지를 보낼때
    //본문에 데이터를 담아서 보내야하는데, 본문이 바로 body이며, 요청본문:requestBody, 응답본문:responseBody 이다.
    //위 함수를 실행시 -> PostsSaveRequestDto -> Service -> Repository(Dto -> Entity)

    @PutMapping("/api/v1/posts/{id}") // 데이터를 update할때 사용하는 매핑이다.
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        //@PathVariable은 {변수명}과 동일하게 설정하면 된다.
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }


}
