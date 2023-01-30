package org.mega.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.mega.book.springboot.config.auth.LoginUser;
import org.mega.book.springboot.config.auth.dto.SessionUser;
import org.mega.book.springboot.service.PostsService;
import org.mega.book.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    @GetMapping("/") // localhost:8080/ 입력시
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAllDesc());
        //레포지토리(entity) -> 서비스(dto) -> index파일안에 "posts"부분으로 가서 dto리스트로 된 정보들 전달

        //SessionUser user = (SessionUser) httpSession.getAttribute("user"); - 직접만든 어노테이션 사용함으로 써 주석처리
        if(user != null){
            System.out.println(user.getName());
            model.addAttribute("userName", user.getName());
        }
        return "index"; // index파일명을 찾아서 그쪽으로 넘어간다.
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        //서비스 -> 레포지토리(entity) -> entity를 dto로 변형
        model.addAttribute("post",dto);
        //post부분으로 가서 dto정보 전달
        return "posts-update";
    }
}
