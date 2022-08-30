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
      <Title>Ask a Public question</Title>
      <Nav />
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
  align-items: center;
  background-color: var(--theme-selected-grey);
  position: absolute;
  top: 0;
  left: 0;
  width: 100vmax;
  height: 100vmax;
`;
const Title = styled.div`
  width: 1233px;
  display: flex;
  margin-top: 100px;
  font-size: var(--header-font);
`;

const Wrapper = styled.div`
  width: 1243px;
  margin-top: 45px;
  display: flex;
  justify-content: space-between;
`;

const ButtonWrapper = styled.div`
  margin-top: 40px;
  width: 1233px;
  display: flex;
`;
export default PostWritePage;
