//package codestates.preproject.stackoverflow.posts.postsRestDocs;
//
//import codestates.preproject.stackoverflow.post.controller.PostController;
//import codestates.preproject.stackoverflow.post.dto.PostDto;
//import codestates.preproject.stackoverflow.post.entity.Posts;
//import codestates.preproject.stackoverflow.post.mapper.PostMapper;
//import codestates.preproject.stackoverflow.post.service.PostService;
//import codestates.preproject.stackoverflow.tags.entity.Tags;
//
//import com.google.gson.Gson;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///*
//@WebMvcTest(PostController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class RestDocs {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private Gson gson;
//
//    @MockBean
//    private PostService postService;
//    @MockBean
//    private PostMapper mapper;
//
//    @Test
//    public void createPostsTest() throws Exception {
//        List<Tags> list = new ArrayList<>();
//        list.add(new Tags("java"));
//        list.add(new Tags("Js"));
//        PostDto.Post post = new PostDto.Post("question1",
//                1L,"how to restDocs", list);
//
//        String content = gson.toJson(post);
//
//        LocalDateTime time = LocalDateTime.now();
//
//        PostDto.Response response1 = new PostDto.Response(1L,
//                "question1", 1L,"how to restDocs", list, 0,time);
//
//        given(mapper.makePostsToPosts(Mockito.any(PostDto.Post.class))).willReturn(new Posts());
//        given(postService.createPost(Mockito.any(Posts.class))).willReturn(new Posts());
//        given(mapper.PostsToResponse(Mockito.any(Posts.class))).willReturn(response1);
//        ResultActions actions =
//                mockMvc.perform(
//                        RestDocumentationRequestBuilders.post("/v1/posts")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content));
//        actions
//                .andExpect(status().isCreated())
//                .andDo(document("create-post",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("subject").type(JsonFieldType.STRING).description("??????"),
//                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("content").type(JsonFieldType.STRING).description("??????"),
//                                        fieldWithPath("tag").type(JsonFieldType.ARRAY).description("??????")
//                                )
//                        ),
//                        responseFields(
//                                Arrays.asList(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????").optional(),
//                                        fieldWithPath("data.postId").type(JsonFieldType.NUMBER).description("????????? ?????????"),
//                                        fieldWithPath("data.subject").type(JsonFieldType.STRING).description("??????"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("??????"),
//                                        fieldWithPath("data.tag").type(JsonFieldType.ARRAY).description("??????"),
//                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("?????? ??????"),
//                                        fieldWithPath("data.createAt").type(JsonFieldType.OBJECT).description("?????? ??????")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    public void updatePostTest() throws Exception {
//        List<Tags> list = new ArrayList<>();
//        list.add(new Tags("java"));
//        list.add(new Tags("Js"));
//        PostDto.Patch post = new PostDto.Patch(1L,"question1",
//                1L,"how to restDocs", list);
//
//        String content = gson.toJson(post);
//
//
//        LocalDateTime time = LocalDateTime.now();
//
//        PostDto.Response response1 = new PostDto.Response(1L,
//                "question1", 1L,"how to restDocs", list, 0,time);
//
//        given(mapper.PatchPostsToPosts(Mockito.any(PostDto.Patch.class))).willReturn(new Posts());
//        given(postService.updatePost(Mockito.any(Posts.class))).willReturn(new Posts());
//        given(mapper.PostsToResponse(Mockito.any(Posts.class))).willReturn(response1);
//        ResultActions actions =
//                mockMvc.perform(
//                        RestDocumentationRequestBuilders.patch("/v1/posts/{post-id}")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content));
//        actions
//                .andExpect(status().isOk())
//                .andDo(document("patch-post",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        pathParameters(
//                                Arrays.asList(parameterWithName("post-id").description("????????? ????????? ID"))
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("postId").type(JsonFieldType.NUMBER).description("????????? ?????????"),
//                                        fieldWithPath("subject").type(JsonFieldType.STRING).description("??????"),
//                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("content").type(JsonFieldType.STRING).description("??????"),
//                                        fieldWithPath("tag").type(JsonFieldType.ARRAY).description("??????")
//                                )
//                        ),
//                        responseFields(
//                                Arrays.asList(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????").optional(),
//                                        fieldWithPath("data.postId").type(JsonFieldType.NUMBER).description("????????? ?????????"),
//                                        fieldWithPath("data.subject").type(JsonFieldType.STRING).description("??????"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("??????"),
//                                        fieldWithPath("data.tag").type(JsonFieldType.ARRAY).description("??????"),
//                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("?????? ??????"),
//                                        fieldWithPath("data.createAt").type(JsonFieldType.OBJECT).description("?????? ??????")
//                                )
//                        )
//                ));
//    }
//
//    @Test
//    public void getPostTest() throws Exception {
//        long postId = 1L;
//
//        List<Tags> list = new ArrayList<>();
//        list.add(new Tags("java"));
//        list.add(new Tags("Js"));
//        LocalDateTime time = LocalDateTime.now();
//
//        PostDto.Response response1 = new PostDto.Response(1L,
//                "question1", 1L,"how to restDocs", list, 0,time);
//
//        given(postService.findPost(Mockito.anyLong())).willReturn(new Posts());
//        given(mapper.PostsToResponse(Mockito.any(Posts.class))).willReturn(response1);
//        ResultActions actions =
//                mockMvc.perform(
//                        RestDocumentationRequestBuilders.get("/v1/posts/{post-id}",postId)
//                                .accept(MediaType.APPLICATION_JSON));
//
//
//        actions
//                .andExpect(status().isOk())
//                .andDo(document("get-post",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        pathParameters(
//                                Arrays.asList(parameterWithName("post-id").description("????????? ????????? ID"))
//                        ),
//
//                        responseFields(
//                                Arrays.asList(
//                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("?????? ?????????").optional(),
//                                        fieldWithPath("data.postId").type(JsonFieldType.NUMBER).description("????????? ?????????"),
//                                        fieldWithPath("data.subject").type(JsonFieldType.STRING).description("??????"),
//                                        fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("?????? ?????????"),
//                                        fieldWithPath("data.content").type(JsonFieldType.STRING).description("??????"),
//                                        fieldWithPath("data.tag").type(JsonFieldType.ARRAY).description("??????"),
//                                        fieldWithPath("data.vote").type(JsonFieldType.NUMBER).description("?????? ??????"),
//                                        fieldWithPath("data.createAt").type(JsonFieldType.OBJECT).description("?????? ??????")
//                                )
//                        )
//                ));
//    }
//
//
//}
//*/
