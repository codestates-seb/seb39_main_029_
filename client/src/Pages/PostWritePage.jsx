import styled from "styled-components";
import Nav from "../Components/Nav";
import AskBody from "../Components/AskBody";
import ArcodianBox from "../Components/ArcodianBox";
import ColorButton from "../Assets/ColorBtn";
import { useState } from "react";

function PostWritePage() {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [tag, setTag] = useState([]);

  return (
    <Container>
      <Nav />
      <Title>Ask a Public question</Title>
      <Wrapper>
        <AskBody />
        <ArcodianBox />
      </Wrapper>
      <ButtonWrapper>
        <ColorButton mode="BLUE" text="Review your question"></ColorButton>
      </ButtonWrapper>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  background-color: var(--theme-selected-grey);
`;
const Title = styled.div`
  display: flex;
  margin-top: 30px;
  font-size: var(--header-font);
  font-weight: bold;
  padding: 0 0 0 40px;
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
