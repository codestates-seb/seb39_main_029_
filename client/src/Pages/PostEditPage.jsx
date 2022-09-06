import styled from "styled-components";
import Nav from "../Components/Nav";
import AskBody from "../Components/AskBody";
import Sidebar from "../Components/Sidebar";
import ColorButton from "../Assets/ColorBtn";
import TagModal from "../Components/TagModal";
import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams, useLocation } from "react-router-dom";

function PostEditPage() {
  const navigate = useNavigate();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [selectedTag, setSelectedTag] = useState([]);
  const [autoSeleted, setAutoSelected] = useState([]);
  const user = localStorage.getItem("memberId");
  const token = localStorage.getItem("accessToken");
  const [totalTags, setTotalTags] = useState([]);
  const [tagsView, setTagsView] = useState([]);
  const location = useLocation();
  let params = useParams();

  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_STACKOVERFLOW}/v1/tags/list`, {
        headers: {
          Authorization: token,
        },
      })
      .then((res) => {
        setTotalTags(res.data);
      });
  }, []);

  const data = location.state.data;
  console.log(data);

  const editForm = {
    subject: title,
    memberId: user,
    content: content,
    postTag: selectedTag,
  };

  const Edit = () => {
    axios
      .patch(
        `${process.env.REACT_APP_STACKOVERFLOW}/v1/posts/${params.pid}`,
        editForm,
        {
          headers: { Authorization: token },
        }
      )
      .then((res) => {
        navigate("/home");
        console.log(res);
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
          data={data}
        />
      </Wrapper>

      <TagModal
        autoSeleted={autoSeleted}
        totalTags={totalTags}
        selectedTag={selectedTag}
        setSelectedTag={setSelectedTag}
        setTagsView={setTagsView}
        tagsView={tagsView}
      />
      <ButtonWrapper>
        <ColorButton
          mode="BLUE"
          text="Review your question"
          onClick={Edit}
        ></ColorButton>
      </ButtonWrapper>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  height: 100vmax;
  background-color: var(--theme-selected-grey);
`;
const Title = styled.div`
  font-weight: bold;
  padding: 0px 540px 0px 0px;
  display: flex;
  margin-top: 30px;
  font-size: var(--header-font);
`;

const Wrapper = styled.div`
  width: 100%;

  margin-top: 30px;
  display: flex;
  justify-content: center;
  padding: 0 0 0 20px;
`;

const ButtonWrapper = styled.div`
  padding: 0px 660px 0px 0px;
  margin-top: 10px;
  display: flex;
`;
export default PostEditPage;
