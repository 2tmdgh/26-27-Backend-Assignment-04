package com.example.security.domain.post.service;

import com.example.security.domain.post.dto.PostCreateRequest;
import com.example.security.domain.post.dto.PostResponse;
import com.example.security.domain.post.entity.Post;
import com.example.security.domain.post.repository.PostRepository;
import com.example.security.global.exception.ErrorCode;
import com.example.security.global.exception.ForbiddenException;
import com.example.security.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponse createPost(Long userId, PostCreateRequest request) {
        Post post = new Post(request.title(), request.content(), userId);
        return PostResponse.from(postRepository.save(post));
    }

    public List<PostResponse> getPosts() {
        return postRepository.findAll().stream()
                .map(PostResponse::from)
                .toList();
    }

    @Transactional
    public void deletePost(Long postId, Long userId, boolean isAdmin) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));

        // 인가: 로그인한 사람이 게시글 작성자인지 확인
        if (!isAdmin && !post.isWrittenBy(userId)) {
            throw new ForbiddenException(ErrorCode.POST_FORBIDDEN);
        }

        postRepository.delete(post);
    }
}
