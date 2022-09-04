import styled from "styled-components";
import Nav from "../Components/Nav";
import AskBody from "../Components/AskBody";
import ArcodianBox from "../Components/ArcodianBox";
import ColorButton from "../Assets/ColorBtn";
import TagModal from "../Components/TagModal";
import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function PostWritePage() {
  const navigate = useNavigate();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [selectedTag, setSelectedTag] = useState([]);
  const [autoSeleted, setAutoSelected] = useState([]);
  const user = localStorage.getItem("memberId");
  const token = localStorage.getItem("accessToken");
  const [totalTags, setTotalTags] = useState([]);
  const [tagsView, setTagsView] = useState([]);
  console.log(selectedTag);
  console.log(tagsView);
  useEffect(() => {
    axios
      .get("http://seb039pre029.ga:8080/v1/tags/list", {
        headers: {
          Authorization: token,
        },
      })
      .then((res) => {
        setTotalTags(res.data);
      });
  }, []);

  const Postform = {
    subject: title,
    memberId: user,
    content: content,
    postTag: selectedTag,
  };

  const Post = () => {
    axios
      .post("http://seb039pre029.ga:8080/v1/posts", Postform, {
        headers: {
          Authorization: token,
        },
      })
      .then((res) => {
        console.log(res.data);
        navigate("/home");
      });
  };

  return (
    <Container>
      <Nav />
      <Title>Ask a Public question</Title>
      <Wrapper>
        <AskBody
          setTitle={setTitle}
          setContent={setContent}
          totalTags={totalTags}
          setAutoSelected={setAutoSelected}
          tagsView={tagsView}
          setTagsView={setTagsView}
          selectedTag={selectedTag}
          setSelectedTag={setSelectedTag}
        />
        <TagModal
          autoSeleted={autoSeleted}
          totalTags={totalTags}
          selectedTag={selectedTag}
          setSelectedTag={setSelectedTag}
          setTagsView={setTagsView}
          tagsView={tagsView}
        />
        <ArcodianBox />
      </Wrapper>
      <ButtonWrapper>
        <ColorButton
          mode="BLUE"
          text="Review your question"
          onClick={Post}
        ></ColorButton>
      </ButtonWrapper>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;

  background-color: var(--theme-selected-grey);
`;
const Title = styled.div`
  font-weight: bold;
  padding: 0 0 0 40px;
  display: flex;
  margin-top: 30px;
  font-size: var(--header-font);
`;

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
  margin-top: 30px;
  display: flex;
  padding: 0 0 0 20px;
`;

const ButtonWrapper = styled.div`
  padding: 0 0 20px 20px;
  margin-top: 10px;
  display: flex;
`;
export default PostWritePage;
