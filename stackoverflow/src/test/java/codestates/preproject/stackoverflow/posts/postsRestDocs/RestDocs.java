package codestates.preproject.stackoverflow.posts.postsRestDocs;

import codestates.preproject.stackoverflow.post.controller.PostController;
import codestates.preproject.stackoverflow.post.dto.PostDto;
import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.post.mapper.PostMapper;
import codestates.preproject.stackoverflow.post.service.PostService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(PostController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class RestDocs {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private PostService postService;
    @MockBean
    private PostMapper mapper;

    @Test
    public void createPostsTest() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        PostDto.Post post = new PostDto.Post("question1",
                1L,"how to restDocs", list);

        String content = gson.toJson(post);
        List<String> list2 = new ArrayList<>();
        list2.add("JAVA");
        list2.add("REACT");
        PostDto.Response response1 = new PostDto.Response(1L,
                "question1", 1L,"how to restDocs", list2, 0);

        given(mapper.makePostsToPosts(Mockito.any(PostDto.Post.class))).willReturn(new Posts());
        given(postService.createPost(Mockito.any(Posts.class))).willReturn(new Posts());
        given(mapper.PostsToResponse(Mockito.any(Posts.class))).willReturn(response1);
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/v1/posts")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));
        actions
                .andExpect(status().isCreated())
                .andDo(document("create-post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("subject").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("tag").type(JsonFieldType.ARRAY).description("태그")
                                )
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터").optional(),
                                        fieldWithPath("data.postId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("data.subject").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.tag").type(JsonFieldType.ARRAY).description("태그"),
                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("투표 갯수")
                                )
                        )
                ));
    }

    @Test
    public void updatePostTest() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        PostDto.Patch post = new PostDto.Patch(1L,"question1",
                1L,"how to restDocs", list);

        String content = gson.toJson(post);
        List<String> list2 = new ArrayList<>();
        list2.add("JAVA");
        list2.add("REACT");
        PostDto.Response response1 = new PostDto.Response(1L,
                "question1", 1L,"how to restDocs", list2, 0);

        given(mapper.PatchPostsToPosts(Mockito.any(PostDto.Patch.class))).willReturn(new Posts());
        given(postService.updatePost(Mockito.any(Posts.class))).willReturn(new Posts());
        given(mapper.PostsToResponse(Mockito.any(Posts.class))).willReturn(response1);
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.patch("/v1/posts/{post-id}")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content));
        actions
                .andExpect(status().isOk())
                .andDo(document("patch-post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                Arrays.asList(parameterWithName("post-id").description("게시글 식별자 ID"))
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("postId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("subject").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("tag").type(JsonFieldType.ARRAY).description("태그")
                                )
                        ),
                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터").optional(),
                                        fieldWithPath("data.postId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("data.subject").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.tag").type(JsonFieldType.ARRAY).description("태그"),
                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("투표 갯수")
                                )
                        )
                ));
    }

    @Test
    public void getPostTest() throws Exception {
        long postId = 1L;

        List<String> list2 = new ArrayList<>();
        list2.add("JAVA");
        list2.add("REACT");
        PostDto.Response response1 = new PostDto.Response(1L,
                "question1", 1L,"how to restDocs", list2, 0);

        given(postService.findPost(Mockito.anyLong())).willReturn(new Posts());
        given(mapper.PostsToResponse(Mockito.any(Posts.class))).willReturn(response1);
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/v1/posts/{post-id}",postId)
                                .accept(MediaType.APPLICATION_JSON));


        actions
                .andExpect(status().isOk())
                .andDo(document("get-post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                Arrays.asList(parameterWithName("post-id").description("게시글 식별자 ID"))
                        ),

                        responseFields(
                                Arrays.asList(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터").optional(),
                                        fieldWithPath("data.postId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("data.subject").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("data.tag").type(JsonFieldType.ARRAY).description("태그"),
                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("투표 갯수")
                                )
                        )
                ));
    }


}
