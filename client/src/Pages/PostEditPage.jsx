import styled from "styled-components";
import Nav from "../Components/Nav";
import AskBody from "../Components/AskBody";
import ColorButton from "../Assets/ColorBtn";
import Sidebar from "../Components/Sidebar";
import axios from "axios";

function PostEditPage() {
  const token = localStorage.getItem("accessToken");
  console.log(token);

  const editForm = {
    subject: "How I send img to server?",
    memberId: 2,
    content:
      "send img to server? I have two method but i don't know what is best. First, i can use base 64 or formData. What is best?",
    postTag: [{ tagId: 1 }, { tagId: 2 }],
  };

  const Edit = () => {
    axios
      .patch("http://seb039pre029.ga:8080/v1/posts/1", editForm, {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res);
      });
  };

  return (
    <Container>
      <Nav />
      <Wrapper>
        <Sidebar />
        <AskBody />
      </Wrapper>
      <ButtonWrapper>
        <ColorButton mode="BLUE" text="Save Edit" onClick={Edit}></ColorButton>
      </ButtonWrapper>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: var(--theme-white);
`;

const Wrapper = styled.div`
  display: flex;
  height: 100vmax;
`;

const ButtonWrapper = styled.div`
  margin-top: 40px;
  width: 910px;
  display: flex;
`;
export default PostEditPage;
