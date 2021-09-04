package com.test.springboot.study.web;

import com.test.springboot.study.config.auth.dto.SessionUser;
import com.test.springboot.study.service.PostsService;
import com.test.springboot.study.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/*
    3-1.IndexController
        http://localhost:8080/index

    @Controller를 붙이면 해당 클래스를 웹 요청 처리하는 컨트롤러로 사용하겠다.

    TODO
        Controller -> ControllerTest 단위테스트를 만든다.
        디렉토리구조가 같아야함.

    3-3. posts/save 링크(get방식)를 만났을 때 처리할 매핑정보를 추가해줘야한다.

    3-4. 글쓰기 action 등을 세팅하지 않고 JS로 처리 할 것.
        JS, stylesheet등을 폴더 포함시키기위해서는 디렉토리를 만들어야한다.
        JS : resources/static/js/app/index.js
        stylesheet : resources/static/css/style.css

    3-8. postsUpdate() 기능 추가

    3-15. google Login 기능 추가
        로그인 기능을 추가하기위해서는 Session을 처리해줘야함.
 */
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) { // Login 성공
            model.addAttribute("userName", user.getName());
            model.addAttribute("email", user.getEmail());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        // model.addAttribute("posts", postsService.findById(id));

        return "posts-update";
    }
}
