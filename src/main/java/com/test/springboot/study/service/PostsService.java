package com.test.springboot.study.service;

import com.test.springboot.study.domain.posts.Posts;
import com.test.springboot.study.domain.posts.PostsRepository;
import com.test.springboot.study.web.dto.PostsListResponseDto;
import com.test.springboot.study.web.dto.PostsResponseDto;
import com.test.springboot.study.web.dto.PostsSaveRequestDto;
import com.test.springboot.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/*
    2-2. PostService를 추가

    2-8.
    - 해야할 절차
        update  <------> insert
         id(O)            id(X)
        update 수행 전 id를 가지고 원본데이터를 찾는일이 선행되어야함.

 */

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // For Update
    // update posts set ... where id = '1';
    /*
        2-8. findById()는 아이디로 Repository를 찾아서 entity를 생성
        entity를 PostsResponseDto 객체를 반환

        save : http://localhost:8080/api/v1/posts
        update : http://localhost:8080/api/v1/posts/34  -> 34번 게시물을 찾아서 수정해라

        GetMapping, PostMapping
        Insert : PostMapping
        Update : PutMapping
        SELECT * : GetMapping

        TODO:
            service에 update 기능이 추가되었으니,
            단위 테스트에서 현재는 save()만 처리
            여기에 update()기능의 단위테스트도 추가되어야함.

    */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository
                        .findById(id)
                        .orElseThrow(()->new IllegalArgumentException("찾는 원본 게시글이 존재하지 않습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    /*
        3-9. delete 추가
        Posts에 delete가 없으므로 ApiController에 delete 등록
     */
    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException("찾는 원본 게시글이 존재하지 않습니다. id=" + id));

        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository
                            .findById(id)
                            .orElseThrow(()->new IllegalArgumentException("찾는 원본 게시글이 존재하지 않습니다. id=" +id));
        return new PostsResponseDto(entity);
    }

    /*
        3-6. List Collection
            findAllDesc()순으로 채운다.
            .map(PostsListResponseDto::new) 람다식 표현은
            .map(posts -> PorstsListResponse(posts)) 와 완전히 일치하는 문법

        import javax.Transaction 을
        import org.springframework.transaction.annotation.Transactional 로 바꿈
        javax의 Transaction은 Option 메소드를 허용하지 않음.
        조회기능만 수행하기 때문에 성능향상을 위해 변경해주는 것이 바람직하다.
     */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository
                .findAllDesc()
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
